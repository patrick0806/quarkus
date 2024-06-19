package com.cvccorp.store.model.responses.waiter;

import java.util.List;

public record PageWaitersResponse(int page, int pageSize, int totalElements, int totalPages, List<WaiterResponse> content) {
}
