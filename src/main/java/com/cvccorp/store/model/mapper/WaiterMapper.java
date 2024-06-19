package com.cvccorp.store.model.mapper;

import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.requests.waiter.CreateWaiterRequest;
import com.cvccorp.store.model.responses.waiter.PageWaitersResponse;
import com.cvccorp.store.model.responses.waiter.WaiterResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WaiterMapper {

    WaiterMapper INSTANCE = Mappers.getMapper(WaiterMapper.class);

    Waiter map(CreateWaiterRequest request);

    WaiterResponse map(Waiter waiter);

    PageWaitersResponse map(Page<Waiter> page);

    default ObjectId  parseStringToObjectId(String id) {
        return new ObjectId(id);
    }

    default String parseObjectIdToString(ObjectId id) {
        return id.toString();
    }
}
