package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.responses.waiter.PageWaitersResponse;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListWaitersUseCaseTest {
    @Mock
    private WaiterRepository waiterRepository;

    @InjectMocks
    private ListWaitersUseCase listWaitersUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPageWhenValidRequest() {
        final var waiterPage = new Page<Waiter>()
                .setPage(0)
                .setPageSize(10)
                .setTotalPages(1)
                .setTotalElements(1)
                .setContent(Collections.singletonList(new Waiter()
                        .setId(ObjectId.get())
                        .setName("John Doe")
                        .setPhone("1234567890")
                        .setPassword("password")
                        .setIsActive(true)));

        when(waiterRepository.listWaiters(anyInt(), anyInt())).thenReturn(Uni.createFrom().item(waiterPage));

        Uni<PageWaitersResponse> response = listWaitersUseCase.execute(1, 10);

        assertNotNull(response.await().indefinitely());
        verify(waiterRepository, times(1)).listWaiters(anyInt(), anyInt());
    }

    @Test
    void shouldThrowExceptionWhenPageIsNegative() {
        assertThrows(InvalidParamException.class, () -> listWaitersUseCase.execute(-1, 10).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPageSizeIsNegative() {
        assertThrows(InvalidParamException.class, () -> listWaitersUseCase.execute(1, -10).await().indefinitely());
    }
}
