package com.cvccorp.store.model.repositories;

import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Ingredient;
import io.smallrye.mutiny.Uni;

public interface IngredientRepository {
    Uni<Ingredient> save(Ingredient waiter);

    Uni<Ingredient> update(Ingredient waiter);

    Uni<Ingredient> findByName(String name);

    Uni<Ingredient> findById(String id);

    Uni<Page<Ingredient>> listIngredients(Integer page, Integer pageSize);
}
