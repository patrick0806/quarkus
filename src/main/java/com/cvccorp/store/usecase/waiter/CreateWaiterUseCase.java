package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.AlreadyExistsException;
import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.mapper.WaiterMapper;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.requests.waiter.CreateWaiterRequest;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateWaiterUseCase {

    @Inject
    WaiterRepository waiterRepository;

    public Uni<WaiterResponse> execute(CreateWaiterRequest waiterRequest) {
        return Uni.createFrom().item(waiterRequest)
                .onItem().transform(request -> {
                    if (request.name() == null || request.name().isEmpty()) {
                        throw new InvalidParamException("Name is required");
                    }

                    if (request.phone() == null || request.phone().isEmpty()) {
                        throw new InvalidParamException("Phone is required");
                    }

                    if (request.password() == null || request.password().isEmpty()) {
                        throw new InvalidParamException("Password is required");
                    }
                   return request;
                })
                .chain(request -> {
                    final var phone = request.phone().replaceAll("[^\\d.]", "");
                    return waiterRepository.findByPhone(phone)
                            .onItem().ifNotNull().failWith(() -> new AlreadyExistsException("Waiter with this phone already exists"))
                            .onItem().ifNull().continueWith(() -> {
                                Waiter waiter = WaiterMapper.INSTANCE.map(waiterRequest);
                                waiter.setIsActive(true);
                                waiter.setPhone(waiterRequest.phone().replaceAll("[^\\d.]", ""));

                                return waiter;
                            });
                })
                .onItem().transformToUni(waiter -> waiterRepository.save(waiter))
                .onItem().transform(WaiterMapper.INSTANCE::map);
    }
}
