package com.cvccorp.store.model.mapper;

import com.cvccorp.store.model.entities.Ingredient;
import com.cvccorp.store.model.requests.ingredient.CreateIngredientRequest;
import com.cvccorp.store.model.responses.ingredient.IngredientResponse;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientMapperTest {

    @Test
    void shouldMapCreateIngredientRequestToIngredient() {
        CreateIngredientRequest request = new CreateIngredientRequest("Tomato", BigDecimal.valueOf(10));
        Ingredient ingredient = IngredientMapper.INSTANCE.map(request);

        assertAll(
                () -> assertNotNull(ingredient),
                () -> assertNull(ingredient.getId()),
                () -> assertEquals("Tomato", ingredient.getName()),
                () -> assertEquals(BigDecimal.valueOf(10), ingredient.getPrice())
        );
    }

    @Test
    void shouldMapIngredientToIngredientResponse() {
        Ingredient ingredient = new Ingredient()
                .setId(ObjectId.get())
                .setName("Tomato")
                .setPrice(BigDecimal.valueOf(10));
        IngredientResponse response = IngredientMapper.INSTANCE.map(ingredient);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.id()),
                () -> assertEquals("Tomato", response.name()),
                () -> assertEquals(BigDecimal.valueOf(10), response.price())
        );
    }

    @Test
    void shouldReturnNullWhenMappingNullCreateIngredientRequest() {
        Ingredient ingredient = IngredientMapper.INSTANCE.map((CreateIngredientRequest) null);
        assertNull(ingredient);
    }

    @Test
    void shouldReturnNullWhenMappingNullIngredient() {
        IngredientResponse response = IngredientMapper.INSTANCE.map((Ingredient) null);
        assertNull(response);
    }
}