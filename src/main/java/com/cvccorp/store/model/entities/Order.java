package com.cvccorp.store.model.entities;

import com.cvccorp.store.model.enums.OrderStatus;
import com.cvccorp.store.model.enums.OrderType;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private ObjectId id;
    private String orderTicket;
    private List<Dishe> dishes;
    private BigDecimal total;
    private OrderStatus status;
    private OrderType type;
    private String waiterId;
    private Client client;

    public ObjectId getId() {
        return id;
    }

    public Order setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getOrderTicket() {
        return orderTicket;
    }

    public Order setOrderTicket(String orderTicket) {
        this.orderTicket = orderTicket;
        return this;
    }

    public List<Dishe> getDishes() {
        return dishes;
    }

    public Order setDishes(List<Dishe> dishes) {
        this.dishes = dishes;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Order setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderType getType() {
        return type;
    }

    public Order setType(OrderType type) {
        this.type = type;
        return this;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public Order setWaiterId(String waiterId) {
        this.waiterId = waiterId;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Order setClient(Client client) {
        this.client = client;
        return this;
    }
}
