package com.cvccorp.store.model.mapper;

import com.cvccorp.store.model.entities.Page;
import com.cvccorp.store.model.entities.Waiter;
import com.cvccorp.store.model.requests.waiter.CreateWaiterRequest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WaiterMapperTest {

    @Test
    void ShouldBeAbleToParseStringToObjectId() {
        final var id = new ObjectId();
        final var idString = id.toString();
        final var objectId = WaiterMapper.INSTANCE.parseStringToObjectId(idString);

        assertEquals(id, objectId);
    }

    @Test
    void ShouldBeAbleToParseObjectIdToString() {
        final var id = new ObjectId();
        final var idString = id.toString();
        final var objectId = WaiterMapper.INSTANCE.parseObjectIdToString(id);

        assertEquals(idString, objectId);
    }

    @Test
    void ShouldBeAbleToMapCreateWaiterRequestToWaiter() {
        CreateWaiterRequest createWaiterRequest = new CreateWaiterRequest("name", "phone", "password");
        WaiterMapper.INSTANCE.map(createWaiterRequest);

        assertAll(
                () -> assertNotNull(createWaiterRequest),
                () -> assertEquals("name", createWaiterRequest.name()),
                () -> assertEquals("phone", createWaiterRequest.phone()),
                () -> assertEquals("password", createWaiterRequest.password())
        );
    }

    @Test
    void ShouldBeAbleToMapWaiterToCreateWaiterResponse() {
       final var waiter = new Waiter()
               .setId(ObjectId.get())
               .setName("name")
               .setPhone("phone")
               .setPassword("password")
               .setIsActive(true);

         final var waiterResponse = WaiterMapper.INSTANCE.map(waiter);

            assertAll(
                    () -> assertNotNull(waiterResponse),
                    () -> assertEquals(waiter.getId().toString(), waiterResponse.id()),
                    () -> assertEquals("name", waiterResponse.name()),
                    () -> assertEquals("phone", waiterResponse.phone()),
                    () -> assertTrue(waiterResponse.isActive())
            );
    }

    @Test
    void ShouldBeAbleToMapPageWaitersResponse() {
        final var waiter = new Waiter()
                .setId(ObjectId.get())
                .setName("name")
                .setPhone("phone")
                .setPassword("password")
                .setIsActive(true);

        final var page = new Page<Waiter>()
                .setPage(0)
                .setPageSize(10)
                .setTotalElements(1)
                .setTotalPages(1)
                .setContent(List.of(waiter));

        final var pageWaitersResponse = WaiterMapper.INSTANCE.map(page);

        assertAll(
                () -> assertNotNull(pageWaitersResponse),
                () -> assertEquals(0, pageWaitersResponse.page()),
                () -> assertEquals(10, pageWaitersResponse.pageSize()),
                () -> assertEquals(1, pageWaitersResponse.totalElements()),
                () -> assertEquals(1, pageWaitersResponse.totalPages()),
                () -> assertEquals(1, pageWaitersResponse.content().size()),
                () -> assertEquals(waiter.getId().toString(), pageWaitersResponse.content().get(0).id()),
                () -> assertEquals("name", pageWaitersResponse.content().get(0).name()),
                () -> assertEquals("phone", pageWaitersResponse.content().get(0).phone()),
                () -> assertTrue(pageWaitersResponse.content().get(0).isActive())
        );
    }
}
