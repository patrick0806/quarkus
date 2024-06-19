package com.cvccorp.store.model.repositories;

import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Waiter;
import io.smallrye.mutiny.Uni;

public interface WaiterRepository {
    Uni<Waiter> save(Waiter waiter);

    Uni<Waiter> update(Waiter waiter);

    Uni<Waiter> findByPhone(String phone);

    Uni<Waiter> findById(String id);

    Uni<Page<Waiter>> listWaiters(Integer page, Integer pageSize);
}
