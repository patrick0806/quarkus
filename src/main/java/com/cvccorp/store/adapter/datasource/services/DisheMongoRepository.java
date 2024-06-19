package com.cvccorp.store.adapter.datasource.services;

import com.cvccorp.store.model.entities.Dishe;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class DisheMongoRepository implements ReactivePanacheMongoRepository<Dishe> {

        public Uni<Dishe> save(Dishe dishe) {
            return persist(dishe);
        }

        public Uni<Dishe> updateDishe(Dishe dishe) {
            return update(dishe);
        }

        public Uni<Dishe> findByName(String name) {
            return find("name", name).firstResult();
        }

        public Uni<Dishe> findById(String id) {
            return findById(new ObjectId(id));
        }
}
