package com.cvccorp.store.adapter.datasource.services;

import com.cvccorp.store.model.entities.Waiter;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class WaiterMongoRepository implements ReactivePanacheMongoRepository<Waiter> {

        public Uni<Waiter> save(Waiter waiter) {
          return persist(waiter);
        }

        public Uni<Waiter> updateWaiter(Waiter waiter) {
          return update(waiter);
        }

        public Uni<Waiter> findByPhone(String phone) {
          return find("phone", phone).firstResult();
        }

        public Uni<Waiter> findById(String id) {
            return findById(new ObjectId(id));
        }

        public Uni<List<Waiter>> listWaiters(Integer page, Integer pageSize) {
          return findAll().page(page, pageSize).list();
        }

        public Uni<Long> countWaiters() {
          return count();
        }
}
