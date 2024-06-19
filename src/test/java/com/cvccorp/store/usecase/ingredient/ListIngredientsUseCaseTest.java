package com.cvccorp.store.usecase.ingredient;

import com.cvccorp.store.model.entities.Ingredient;
import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.repositories.IngredientRepository;
import com.cvccorp.store.model.responses.ingredient.PageIngredientResponse;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListIngredientsUseCaseTest {
    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private ListIngredientsUseCase listIngredientsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPageWhenValidRequest() {
        final var ingredientPage = new Page<Ingredient>()
                .setPage(0)
                .setPageSize(10)
                .setTotalPages(1)
                .setTotalElements(1)
                .setContent(Collections.singletonList(new Ingredient()
                        .setId(ObjectId.get())
                        .setName("Tomato")
                        .setPrice(BigDecimal.TEN)
                        .setIsAvailable(true)));

        when(ingredientRepository.listIngredients(anyInt(), anyInt())).thenReturn(Uni.createFrom().item(ingredientPage));

        Uni<PageIngredientResponse> response = listIngredientsUseCase.execute(1, 10);

        assertNotNull(response.await().indefinitely());
        verify(ingredientRepository, times(1)).listIngredients(anyInt(), anyInt());
    }

    @Test
    void shouldThrowExceptionWhenPageIsNegative() {
        assertThrows(InvalidParamException.class, () -> listIngredientsUseCase.execute(-1, 10).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPageSizeIsNegative() {
        assertThrows(InvalidParamException.class, () -> listIngredientsUseCase.execute(1, -10).await().indefinitely());
    }
}