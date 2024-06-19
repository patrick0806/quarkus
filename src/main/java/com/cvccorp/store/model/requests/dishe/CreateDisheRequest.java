package com.cvccorp.store.model.requests.dishe;

import com.cvccorp.store.model.entities.Ingredient;
import com.cvccorp.store.model.enums.FoodTypes;

import java.math.BigDecimal;
import java.util.List;

public record CreateDisheRequest(
        String name,
        String description,
        BigDecimal price,
        List<Ingredient> ingredients,
        FoodTypes type,
        Boolean isAvailable
) {
}
