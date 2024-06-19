package com.cvccorp.store.adapter.rest;

import com.cvccorp.store.model.requests.dishe.CreateDisheRequest;
import com.cvccorp.store.model.requests.dishe.UploadDishImageRequest;
import com.cvccorp.store.model.responses.dishe.DisheResponse;
import com.cvccorp.store.usecase.dishe.CreateDisheUseCase;
import com.cvccorp.store.usecase.dishe.UploadDisheImageUseCase;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/dishes")
public class DishController {

    private final CreateDisheUseCase createDisheUseCase;
    private final UploadDisheImageUseCase uploadDisheImageUseCase;

    public DishController(CreateDisheUseCase createDisheUseCase, UploadDisheImageUseCase uploadDisheImageUseCase) {
        this.createDisheUseCase = createDisheUseCase;
        this.uploadDisheImageUseCase = uploadDisheImageUseCase;
    }

    @POST
    public Uni<DisheResponse> createDishe(CreateDisheRequest createDisheRequest){
        return this.createDisheUseCase.execute(createDisheRequest);
    }

    @POST
    @Path("/images")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DisheResponse> addImage(UploadDishImageRequest disheImage){
        return uploadDisheImageUseCase.execute(disheImage);
    }
}
