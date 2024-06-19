package com.cvccorp.store.model.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@MongoEntity(collection = "ingredients")
public class Ingredient {
    @BsonId
    private ObjectId id;
    private String name;
    private BigDecimal price;
    private Boolean isAvailable;

    public ObjectId getId() {
        return id;
    }

    public Ingredient setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ingredient setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Ingredient setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Ingredient setIsAvailable(Boolean available) {
        isAvailable = available;
        return this;
    }
}
