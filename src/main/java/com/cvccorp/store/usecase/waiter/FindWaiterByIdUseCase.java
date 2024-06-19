package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.exceptions.NotFoundException;
import com.cvccorp.store.model.mapper.WaiterMapper;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FindWaiterByIdUseCase {

    private  final WaiterRepository waiterRepository;

    public FindWaiterByIdUseCase(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    public Uni<WaiterResponse> execute(String id) {
        return waiterRepository.findById(id)
                .onItem().ifNull().failWith(()-> {
                    String exceptionMessage = String.format("Waiter with id %s not found", id);
                    throw new NotFoundException(exceptionMessage);
                })
                .onItem().ifNotNull().transform(WaiterMapper.INSTANCE::map);
    }
}
