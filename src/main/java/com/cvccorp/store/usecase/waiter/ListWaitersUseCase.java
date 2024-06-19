package com.cvccorp.store.usecase.waiter;

import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.exceptions.InvalidParamException;
import com.cvccorp.store.model.mapper.WaiterMapper;
import com.cvccorp.store.model.repositories.WaiterRepository;
import com.cvccorp.store.model.responses.waiter.PageWaitersResponse;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListWaitersUseCase {

    @Inject
    WaiterRepository waiterRepository;

    public Uni<PageWaitersResponse> execute(Integer page, Integer pageSize) {
        if(page == null || page < 0) {
            throw new InvalidParamException("Page is required and must be greater than 0");
        }

        if(pageSize == null || pageSize < 0) {
            throw new InvalidParamException("PageSize is required and must be greater than 0");
        }

        return waiterRepository.listWaiters(page, pageSize)
                .onItem()
                .transform(WaiterMapper.INSTANCE::map);
    }
}
