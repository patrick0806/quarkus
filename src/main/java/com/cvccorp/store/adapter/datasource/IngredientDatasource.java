package com.cvccorp.store.adapter.datasource;

import com.cvccorp.store.adapter.datasource.services.IngredientMongoRepository;
import com.cvccorp.store.model.entities.Ingredient;
import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.exceptions.DatabaseException;
import com.cvccorp.store.model.repositories.IngredientRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IngredientDatasource implements IngredientRepository {

    private final IngredientMongoRepository ingredientMongoRepository;

    public IngredientDatasource(IngredientMongoRepository ingredientMongoRepository) {
        this.ingredientMongoRepository = ingredientMongoRepository;
    }

    @Override
    public Uni<Ingredient> save(Ingredient ingredient) {
        return ingredientMongoRepository.save(ingredient);
    }

    @Override
    public Uni<Ingredient> update(Ingredient waiter) {
        return ingredientMongoRepository.updateIngredient(waiter);
    }

    @Override
    public Uni<Ingredient> findByName(String name) {
        return ingredientMongoRepository.findByName(name);
    }

    @Override
    public Uni<Ingredient> findById(String id) {
        return ingredientMongoRepository.findById(id);
    }

    @Override
    public Uni<Page<Ingredient>> listIngredients(Integer page, Integer pageSize) {
        return Uni.combine()
                .all()
                .unis(ingredientMongoRepository.listIngredients(page, pageSize), ingredientMongoRepository.countIngredients())
                .asTuple()
                .onItem().transform(tuple -> {
                    final var ingredientsList = tuple.getItem1();
                    final var totalElements = tuple.getItem2();
                    final var totalPages = Math.ceil(totalElements.doubleValue() / pageSize.doubleValue());

                    return new Page<Ingredient>()
                            .setPage(page)
                            .setPageSize(pageSize)
                            .setTotalElements(totalElements.intValue())
                            .setTotalPages((int) totalPages)
                            .setContent(ingredientsList);

                }).onFailure().recoverWithItem(() ->{
                    throw new DatabaseException("Error while listing waiters");
                });
    }
}
