package com.cvccorp.store.model.repositories;

import com.cvccorp.store.model.entities.Dishe;
import io.smallrye.mutiny.Uni;

public interface DisheRepository {
    Uni<Dishe> save(Dishe dishe);

    Uni<Dishe> updateDishe(Dishe dishe);

    Uni<Dishe> findByName(String name);

    Uni<Dishe> findById(String id);
}
