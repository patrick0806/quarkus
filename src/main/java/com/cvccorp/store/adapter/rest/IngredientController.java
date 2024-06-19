package com.cvccorp.store.adapter.rest;

import com.cvccorp.store.model.requests.ingredient.CreateIngredientRequest;
import com.cvccorp.store.model.responses.ingredient.IngredientResponse;
import com.cvccorp.store.model.responses.ingredient.PageIngredientResponse;
import com.cvccorp.store.usecase.ingredient.CreateIngredientUseCase;
import com.cvccorp.store.usecase.ingredient.ListIngredientsUseCase;
import io.smallrye.mutiny.Uni;

import jakarta.ws.rs.*;

@Path("/ingredients")
public class IngredientController {

    private final CreateIngredientUseCase createIngredientUseCase;
    private final ListIngredientsUseCase listIngredientsUseCase;

    public IngredientController(CreateIngredientUseCase createIngredientUseCase, ListIngredientsUseCase listIngredientsUseCase) {
        this.createIngredientUseCase = createIngredientUseCase;
        this.listIngredientsUseCase = listIngredientsUseCase;
    }

    @GET
    public Uni<PageIngredientResponse> listIngredients(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return listIngredientsUseCase.execute(page, pageSize);
    }

    @POST
    public Uni<IngredientResponse> createIngredient(CreateIngredientRequest createIngredientRequest){
        return createIngredientUseCase.execute(createIngredientRequest);
    }
}
