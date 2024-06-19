package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.exceptions.NotFoundException;
import com.cvccorp.store.model.mapper.WaiterMapper;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.requests.waiter.UpdateWaiterRequest;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UpdateWaiterUseCase {

    private final WaiterRepository waiterRepository;

    public UpdateWaiterUseCase(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    public Uni<WaiterResponse> execute(String id,UpdateWaiterRequest updateWaiterRequest){
        return waiterRepository.findById(id)
                .onItem().ifNull().failWith(()-> {
                    String exceptionMessage = String.format("Waiter with id %s not found", id);
                    throw new NotFoundException(exceptionMessage);
                })
                .onItem().ifNotNull().transformToUni(waiter -> {
                    Optional.ofNullable(updateWaiterRequest.name()).ifPresent(waiter::setName);
                    Optional.ofNullable(updateWaiterRequest.phone()).ifPresent(waiter::setPhone);
                    Optional.ofNullable(updateWaiterRequest.isActive()).ifPresent(waiter::setIsActive);
                    return waiterRepository.update(waiter);
                })
                .onItem().transform(WaiterMapper.INSTANCE::map);
    }
}
