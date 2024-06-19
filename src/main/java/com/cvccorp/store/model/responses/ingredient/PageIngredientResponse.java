package com.cvccorp.store.model.responses.ingredient;

import java.util.List;

public record PageIngredientResponse (int page, int pageSize, int totalElements, int totalPages, List<IngredientResponse> content) {
}