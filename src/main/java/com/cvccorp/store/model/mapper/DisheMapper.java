package com.cvccorp.store.model.mapper;

import com.cvccorp.store.model.entities.Dishe;
import com.cvccorp.store.model.requests.dishe.CreateDisheRequest;
import com.cvccorp.store.model.responses.dishe.DisheResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisheMapper {
    DisheMapper INSTANCE = Mappers.getMapper(DisheMapper.class);

    Dishe map(CreateDisheRequest createDisheRequest);

    DisheResponse map (Dishe dishe);

    default ObjectId parseStringToObjectId(String id) {
        return new ObjectId(id);
    }

    default String parseObjectIdToString(ObjectId id) {
        return id.toString();
    }
}
