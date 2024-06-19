package com.cvccorp.store.adapter.datasource.services;

import com.cvccorp.store.model.entities.Ingredient;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class IngredientMongoRepository  implements ReactivePanacheMongoRepository<Ingredient> {

    public Uni<Ingredient> save(Ingredient ingredient) {
        return persist(ingredient);
    }

    public Uni<Ingredient> updateIngredient(Ingredient ingredient) {
        return update(ingredient);
    }

    public Uni<Ingredient> findByName(String name) {
        return find("name", name).firstResult();
    }

    public Uni<Ingredient> findById(String id) {
        return findById(new ObjectId(id));
    }

    public Uni<List<Ingredient>> listIngredients(Integer page, Integer pageSize) {
        return findAll().page(page, pageSize).list();
    }

    public Uni<Long> countIngredients() {
        return count();
    }
}
