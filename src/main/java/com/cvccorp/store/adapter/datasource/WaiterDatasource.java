package com.cvccorp.store.adapter.datasource;

import com.cvccorp.store.adapter.datasource.services.WaiterMongoRepository;
import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.DatabaseException;
import com.cvccorp.store.model.repositories.WaiterRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class WaiterDatasource implements WaiterRepository {

    @Inject
    WaiterMongoRepository waiterMongoRepository;

    @Override
    public Uni<Waiter> save(Waiter waiter) {
        return waiterMongoRepository.save(waiter);
    }

    @Override
    public Uni<Waiter> update(Waiter waiter) {
        return waiterMongoRepository.updateWaiter(waiter);
    }

    @Override
    public Uni<Waiter> findByPhone(String phone) {
        return waiterMongoRepository.findByPhone(phone);
    }

    @Override
    public Uni<Waiter> findById(String id) {
        return waiterMongoRepository.findById(id);
    }

    @Override
    public Uni<Page<Waiter>> listWaiters(Integer page, Integer pageSize) {

        return Uni.combine()
                .all()
                .unis(waiterMongoRepository.listWaiters(page, pageSize), waiterMongoRepository.countWaiters())
                .asTuple()
                .onItem().transform(tuple -> {
                    final var waitersList = tuple.getItem1();
                    final var totalElements = tuple.getItem2();
                    final var totalPages = Math.ceil(totalElements.doubleValue() / pageSize.doubleValue());

                    return new Page<Waiter>()
                            .setPage(page)
                            .setPageSize(pageSize)
                            .setTotalElements(totalElements.intValue())
                            .setTotalPages((int) totalPages)
                            .setContent(waitersList);

                }).onFailure().recoverWithItem(() ->{
                    throw new DatabaseException("Error while listing waiters");
                });
    }
}
