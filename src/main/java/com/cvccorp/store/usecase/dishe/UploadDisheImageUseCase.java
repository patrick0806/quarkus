package com.cvccorp.store.usecase.dishe;

import com.cvccorp.store.model.entities.FoodImage;
import com.cvccorp.store.model.mapper.DisheMapper;
import com.cvccorp.store.model.repositories.DisheRepository;
import com.cvccorp.store.model.requests.dishe.UploadDishImageRequest;
import com.cvccorp.store.model.responses.dishe.DisheResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.InputStream;

@ApplicationScoped
public class UploadDisheImageUseCase {

    private final DisheRepository disheRepository;

    public UploadDisheImageUseCase(DisheRepository disheRepository) {
        this.disheRepository = disheRepository;
    }

    public Uni<DisheResponse> execute(UploadDishImageRequest uploadDishImageRequest) {
        return Uni.createFrom()
                .item(uploadDishImageRequest)
                .onItem()
                .transformToUni(request -> disheRepository.findById(request.getDishId()))
                .onItem()
                .transformToUni(dishe -> {
                    final var imageURL = uploadImage(uploadDishImageRequest.getFile());
                    dishe.getImages().add(
                            new FoodImage()
                                    .setUrl(imageURL)
                                    .setIsThumbnail(uploadDishImageRequest.getThumbnail())
                                    .setDescription(uploadDishImageRequest.getDescription())
                    );
                    return disheRepository.updateDishe(dishe);
                })
                .map(DisheMapper.INSTANCE::map);
    }

    private String uploadImage(InputStream image) {
        return "Image uploaded";
    }
}
