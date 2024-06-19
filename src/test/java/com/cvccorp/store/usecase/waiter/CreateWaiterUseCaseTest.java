package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.AlreadyExistsException;
import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.mapper.WaiterMapper;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.requests.waiter.CreateWaiterRequest;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateWaiterUseCaseTest {

    @Mock
    private WaiterRepository waiterRepository;

    @InjectMocks
    private CreateWaiterUseCase createWaiterUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateWaiterWhenValidRequest() {
        CreateWaiterRequest request = new CreateWaiterRequest("John Doe", "1234567890", "password");
        Waiter waiter = WaiterMapper.INSTANCE.map(request);
        waiter.setIsActive(true);
        waiter.setId(ObjectId.get());

        when(waiterRepository.findByPhone(anyString())).thenReturn(Uni.createFrom().nullItem());
        when(waiterRepository.save(any(Waiter.class))).thenReturn(Uni.createFrom().item(waiter));

        Uni<WaiterResponse> response = createWaiterUseCase.execute(request);

        assertNotNull(response.await().indefinitely());
        verify(waiterRepository, times(1)).findByPhone(anyString());
        verify(waiterRepository, times(1)).save(any(Waiter.class));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        CreateWaiterRequest request = new CreateWaiterRequest("", "1234567890", "password");

        assertThrows(InvalidParamException.class, () -> createWaiterUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPhoneIsEmpty() {
        CreateWaiterRequest request = new CreateWaiterRequest("John Doe", "", "password");

        assertThrows(InvalidParamException.class, () -> createWaiterUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmpty() {
        CreateWaiterRequest request = new CreateWaiterRequest("John Doe", "1234567890", "");

        assertThrows(InvalidParamException.class, () -> createWaiterUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenWaiterWithPhoneAlreadyExists() {
        CreateWaiterRequest request = new CreateWaiterRequest("John Doe", "1234567890", "password");
        when(waiterRepository.findByPhone(anyString())).thenReturn(Uni.createFrom().item(new Waiter()));

        assertThrows(AlreadyExistsException.class, () -> createWaiterUseCase.execute(request).await().indefinitely());
    }
}
