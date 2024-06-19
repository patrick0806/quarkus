package com.cvccorp.store.adapter.rest;

import com.cvccorp.store.model.requests.waiter.CreateWaiterRequest;
import com.cvccorp.store.model.requests.waiter.UpdateWaiterRequest;
import com.cvccorp.store.model.responses.waiter.PageWaitersResponse;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import com.cvccorp.store.usecase.waiter.CreateWaiterUseCase;
import com.cvccorp.store.usecase.waiter.FindWaiterByIdUseCase;
import com.cvccorp.store.usecase.waiter.ListWaitersUseCase;
import com.cvccorp.store.usecase.waiter.UpdateWaiterUseCase;
import io.smallrye.mutiny.Uni;

import jakarta.ws.rs.*;

@Path("/waiters")
public class WaiterController {

    private final CreateWaiterUseCase createWaiterUseCase;
    private final ListWaitersUseCase listWaitersUseCase;
    private final FindWaiterByIdUseCase findWaiterByIdUseCase;
    private final UpdateWaiterUseCase updateWaiterUseCase;

    WaiterController(
            CreateWaiterUseCase createWaiterUseCase,
            ListWaitersUseCase listWaitersUseCase,
            FindWaiterByIdUseCase findWaiterByIdUseCase,
            UpdateWaiterUseCase updateWaiterUseCase
    ) {
        this.createWaiterUseCase = createWaiterUseCase;
        this.listWaitersUseCase = listWaitersUseCase;
        this.findWaiterByIdUseCase = findWaiterByIdUseCase;
        this.updateWaiterUseCase = updateWaiterUseCase;
    }

    @GET
    public Uni<PageWaitersResponse> listWaiters(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return listWaitersUseCase.execute(page,pageSize);
    }

    @GET
    @Path("/{id}")
    public Uni<WaiterResponse> getWaiterById(@PathParam("id") String id) {
        return findWaiterByIdUseCase.execute(id);
    }

    @POST
    public Uni<WaiterResponse> createWaiter(CreateWaiterRequest createWaiterRequest) {
        return createWaiterUseCase.execute(createWaiterRequest);
    }

    @PATCH
    @Path("/{id}")
    public Uni<WaiterResponse> updateWaiter(@PathParam("id") String id, UpdateWaiterRequest updateWaiterRequest) {
        return updateWaiterUseCase.execute(id, updateWaiterRequest);
    }
}
