package com.cvccorp.store.model.responses.dishe;

import com.cvccorp.store.model.entities.FoodImage;
import com.cvccorp.store.model.responses.ingredient.IngredientResponse;

import java.util.List;

public record DisheResponse(
        String id,
        String name,
        String description,
        String type,
        Boolean isAvailable,
        List<IngredientResponse> ingredients,
        List<FoodImage> images
) {
}
