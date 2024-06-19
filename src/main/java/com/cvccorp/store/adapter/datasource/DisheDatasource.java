package com.cvccorp.store.adapter.datasource;

import com.cvccorp.store.adapter.datasource.services.DisheMongoRepository;
import com.cvccorp.store.model.entities.Dishe;
import com.cvccorp.store.model.repositories.DisheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisheDatasource implements DisheRepository {

    private  final DisheMongoRepository disheMongoRepository;

    public DisheDatasource(DisheMongoRepository disheMongoRepository) {
        this.disheMongoRepository = disheMongoRepository;
    }

    @Override
    public Uni<Dishe> save(Dishe dishe) {
        return disheMongoRepository.save(dishe);
    }

    @Override
    public Uni<Dishe> updateDishe(Dishe dishe) {
        return disheMongoRepository.updateDishe(dishe);
    }

    @Override
    public Uni<Dishe> findByName(String name) {
        return disheMongoRepository.findByName(name);
    }

    @Override
    public Uni<Dishe> findById(String id) {
        return disheMongoRepository.findById(id);
    }
}
