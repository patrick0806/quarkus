package com.cvccorp.store.usecase.ingredient;

import com.cvccorp.store.model.entities.Ingredient;
import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.repositories.IngredientRepository;
import com.cvccorp.store.model.requests.ingredient.CreateIngredientRequest;
import com.cvccorp.store.model.responses.ingredient.IngredientResponse;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateIngredientUseCaseTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private CreateIngredientUseCase createIngredientUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateIngredientWhenValidRequest() {
        final var createdIngredient = new Ingredient()
                .setId(ObjectId.get())
                .setName("Tomato")
                .setPrice(BigDecimal.valueOf(10))
                .setIsAvailable(true);
        CreateIngredientRequest request = new CreateIngredientRequest("Tomato", BigDecimal.valueOf(10));
        when(ingredientRepository.save(any())).thenReturn(Uni.createFrom().item(createdIngredient));

        Uni<IngredientResponse> response = createIngredientUseCase.execute(request);

        assertNotNull(response.await().indefinitely());
        verify(ingredientRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        CreateIngredientRequest request = new CreateIngredientRequest(null, BigDecimal.valueOf(10));
        assertThrows(InvalidParamException.class, () -> createIngredientUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        CreateIngredientRequest request = new CreateIngredientRequest("", BigDecimal.valueOf(10));
        assertThrows(InvalidParamException.class, () -> createIngredientUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        CreateIngredientRequest request = new CreateIngredientRequest("Tomato", null);
        assertThrows(InvalidParamException.class, () -> createIngredientUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        CreateIngredientRequest request = new CreateIngredientRequest("Tomato", BigDecimal.ZERO);
        assertThrows(InvalidParamException.class, () -> createIngredientUseCase.execute(request).await().indefinitely());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        CreateIngredientRequest request = new CreateIngredientRequest("Tomato", BigDecimal.valueOf(-10));
        assertThrows(InvalidParamException.class, () -> createIngredientUseCase.execute(request).await().indefinitely());
    }
}