package com.cvccorp.store.usecase.ingredient;

import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.mapper.IngredientMapper;
import com.cvccorp.store.model.repositories.IngredientRepository;
import com.cvccorp.store.model.requests.ingredient.CreateIngredientRequest;
import com.cvccorp.store.model.responses.ingredient.IngredientResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateIngredientUseCase {

    private final IngredientRepository ingredientRepository;

    public CreateIngredientUseCase(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Uni<IngredientResponse> execute(CreateIngredientRequest ingredientRequest) {
        return Uni.createFrom().item(ingredientRequest)
                .chain(ingredient -> {
                    if (ingredient.name() == null || ingredient.name().isBlank()) {
                        throw new InvalidParamException("Name is required");
                    }
                    if (ingredient.price() == null || ingredient.price().intValue() <= 0) {
                        throw new InvalidParamException("Price is required and must be greater than 0");
                    }
                    return Uni.createFrom().item(ingredientRequest);

                })
                .onItem().transform(ingredient ->{
                    final var ingredientEntity = IngredientMapper.INSTANCE.map(ingredient);
                    ingredientEntity.setIsAvailable(true);
                    return ingredientEntity;
                })
                .onItem().transformToUni(ingredientRepository::save)
                .onItem().transform(IngredientMapper.INSTANCE::map);
    }
}
