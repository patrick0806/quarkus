package com.cvccorp.store.model.requests.ingredient;

import java.math.BigDecimal;

public record CreateIngredientRequest(String name, BigDecimal price) {
}
