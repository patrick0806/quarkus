package com.cvccorp.store.model.responses.waiter;

public record WaiterResponse(
        String id,
        String name,
        String phone,
        Boolean isActive
) {

}
