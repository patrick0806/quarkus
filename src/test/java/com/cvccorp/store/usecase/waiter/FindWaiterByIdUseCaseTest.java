package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.NotFoundException;
import com.cvccorp.store.model.repositories.WaiterRepository;
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

class FindWaiterByIdUseCaseTest {

    @Mock
    private WaiterRepository waiterRepository;

    @InjectMocks
    private FindWaiterByIdUseCase findWaiterByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnWaiterWhenIdExists() {
        final var waiter = new Waiter()
                .setId(new ObjectId("6667999f9afce14dad542614"))
                .setName("John Doe")
                .setPhone("1234567890")
                .setPassword("password")
                .setIsActive(true);

        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().item(waiter));

        Uni<WaiterResponse> response = findWaiterByIdUseCase.execute("123");

        assertNotNull(response.await().indefinitely());
        verify(waiterRepository, times(1)).findById(anyString());
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().nullItem());

        assertThrows(NotFoundException.class, () -> findWaiterByIdUseCase.execute("123").await().indefinitely());
    }
}