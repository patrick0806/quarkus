package com.cvccorp.store.usecase.ingredient;

import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.mapper.IngredientMapper;
import com.cvccorp.store.model.repositories.IngredientRepository;
import com.cvccorp.store.model.responses.ingredient.PageIngredientResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListIngredientsUseCase {
    private final IngredientRepository ingredientRepository;

    public ListIngredientsUseCase(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Uni<PageIngredientResponse> execute(Integer page, Integer pageSize) {
        if(page == null || page < 0) {
            throw new InvalidParamException("Page is required and must be greater than 0");
        }

        if(pageSize == null || pageSize < 0) {
            throw new InvalidParamException("PageSize is required and must be greater than 0");
        }

        return ingredientRepository.listIngredients(page, pageSize)
                .map(IngredientMapper.INSTANCE::map);
    }
}
