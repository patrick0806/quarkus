package com.cvccorp.store.model.mapper;

import com.cvccorp.store.model.entities.Ingredient;
import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.requests.ingredient.CreateIngredientRequest;
import com.cvccorp.store.model.responses.ingredient.IngredientResponse;
import com.cvccorp.store.model.responses.ingredient.PageIngredientResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    Ingredient map (CreateIngredientRequest createIngredientRequest);

    IngredientResponse map (Ingredient ingredient);

    PageIngredientResponse map (Page<Ingredient> page);

    default ObjectId parseStringToObjectId(String id) {
        return new ObjectId(id);
    }

    default String parseObjectIdToString(ObjectId id) {
        return id.toString();
    }
}
