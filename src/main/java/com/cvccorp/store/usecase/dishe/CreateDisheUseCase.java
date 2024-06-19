package com.cvccorp.store.usecase.dishe;

import com.cvccorp.store.model.mapper.DisheMapper;
import com.cvccorp.store.model.repositories.DisheRepository;
import com.cvccorp.store.model.requests.dishe.CreateDisheRequest;
import com.cvccorp.store.model.responses.dishe.DisheResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CreateDisheUseCase {

    private DisheRepository disheRepository;

    public CreateDisheUseCase(DisheRepository disheRepository) {
        this.disheRepository = disheRepository;
    }

    public Uni<DisheResponse> execute(CreateDisheRequest createDisheRequest) {
        return Uni.createFrom()
                .item(createDisheRequest)
                .chain(dishRequest-> {
                    if(dishRequest.name() == null || dishRequest.name().isEmpty()){
                        return Uni.createFrom().failure(new IllegalArgumentException("Name is required"));
                    }

                    if(dishRequest.price() == null || dishRequest.price().doubleValue() <= 0){
                        return Uni.createFrom().failure(new IllegalArgumentException("Price is required"));
                    }

                    if(dishRequest.ingredients() == null || dishRequest.ingredients().isEmpty()){
                        return Uni.createFrom().failure(new IllegalArgumentException("Ingredients is required"));
                    }
                    return Uni.createFrom().item(dishRequest);
                })
                .map(disheRequest->{
                    final var dishe = DisheMapper.INSTANCE.map(disheRequest);
                    dishe.setImages(List.of());
                    return dishe;
                })
                .onItem()
                .transformToUni(disheRepository::save)
                .map(DisheMapper.INSTANCE::map);
    }
}
