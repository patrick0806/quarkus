package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.NotFoundException;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.requests.waiter.UpdateWaiterRequest;
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

class UpdateWaiterUseCaseTest {

    @Mock
    private WaiterRepository waiterRepository;

    @InjectMocks
    private UpdateWaiterUseCase updateWaiterUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateWaiterWhenIdExistsAndValidRequest() {
        final var waiter = new Waiter()
                .setId(new ObjectId("6667999f9afce14dad542614"))
                .setName("John")
                .setPhone("890")
                .setPassword("password")
                .setIsActive(true);
        UpdateWaiterRequest request = new UpdateWaiterRequest("John Doe", "1234567890", true);
        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().item(waiter));
        when(waiterRepository.save(any(Waiter.class)))
                .thenReturn(Uni.createFrom().item(waiter.setName("John Doe").setPhone("1234567890").setIsActive(true)));

        Uni<WaiterResponse> response = updateWaiterUseCase.execute("123", request);

        response.onItem().ifNotNull().invoke(waiterResponse -> {
            assertEquals("John Doe", waiterResponse.name());
            assertEquals("1234567890", waiterResponse.phone());
            assertTrue(waiterResponse.isActive());
        });
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        UpdateWaiterRequest request = new UpdateWaiterRequest("John Doe", "1234567890", true);
        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().nullItem());

        assertThrows(NotFoundException.class, () -> updateWaiterUseCase.execute("123", request).await().indefinitely());
    }

    @Test
    void shouldNotUpdateWaiterWhenNameIsNull() {
        UpdateWaiterRequest request = new UpdateWaiterRequest(null, "1234567890", true);
        Waiter existingWaiter = new Waiter().setId(ObjectId.get());
        existingWaiter.setName("Existing Name");
        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().item(existingWaiter));
        when(waiterRepository.save(any(Waiter.class))).thenReturn(Uni.createFrom().item(new Waiter()));

        Uni<WaiterResponse> response = updateWaiterUseCase.execute("123", request);

        response.onItem()
                .ifNotNull()
                .invoke(waiterResponse ->{
                    assertNotNull(waiterResponse);
                    assertEquals("Existing Name", waiterResponse.name());
                    verify(waiterRepository, times(1)).findById(anyString());
                    verify(waiterRepository, times(1)).save(any(Waiter.class));
                });
    }

    @Test
    void shouldNotUpdateWaiterWhenPhoneIsNull() {
        UpdateWaiterRequest request = new UpdateWaiterRequest("John Doe", null, true);
        Waiter existingWaiter = new Waiter().setId(ObjectId.get());
        existingWaiter.setPhone("Existing Phone");
        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().item(existingWaiter));
        when(waiterRepository.save(any(Waiter.class))).thenReturn(Uni.createFrom().item(new Waiter()));

        Uni<WaiterResponse> response = updateWaiterUseCase.execute("123", request);

        response.onItem()
                .ifNotNull()
                .invoke(waiterResponse ->{
                    assertNotNull(waiterResponse);
                    assertEquals("Existing Phone", waiterResponse.phone());
                    verify(waiterRepository, times(1)).findById(anyString());
                    verify(waiterRepository, times(1)).save(any(Waiter.class));
                });
    }

    @Test
    void shouldNotUpdateWaiterWhenIsActiveIsNull() {
        UpdateWaiterRequest request = new UpdateWaiterRequest("John Doe", "1234567890", null);
        Waiter existingWaiter = new Waiter().setId(ObjectId.get());
        existingWaiter.setIsActive(true);
        when(waiterRepository.findById(anyString())).thenReturn(Uni.createFrom().item(existingWaiter));
        when(waiterRepository.save(any(Waiter.class))).thenReturn(Uni.createFrom().item(new Waiter()));

        Uni<WaiterResponse> response = updateWaiterUseCase.execute("123", request);

        response.onItem()
                .ifNotNull()
                .invoke(waiterResponse ->{
                    assertNotNull(waiterResponse);
                    assertTrue(waiterResponse.isActive());
                    verify(waiterRepository, times(1)).findById(anyString());
                    verify(waiterRepository, times(1)).save(any(Waiter.class));
                });
    }
}
