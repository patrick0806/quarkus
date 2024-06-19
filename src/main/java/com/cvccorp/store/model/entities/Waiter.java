package com.cvccorp.store.model.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@MongoEntity(collection = "waiters")
public class Waiter {
    @BsonId
    private ObjectId id;
    private String name;
    private String phone;
    private String password;
    private Boolean isActive;

    public ObjectId getId() {
        return id;
    }

    public Waiter setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Waiter setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Waiter setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Waiter setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Waiter setIsActive(Boolean active) {
        isActive = active;
        return this;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
