package com.cvccorp.store.model.responses.ingredient;

import java.math.BigDecimal;

public record IngredientResponse(String id, String name, BigDecimal price, Boolean isAvailable) {
}
