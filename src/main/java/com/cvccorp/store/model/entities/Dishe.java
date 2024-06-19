package com.cvccorp.store.model.entities;

import com.cvccorp.store.model.enums.FoodTypes;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;

@MongoEntity(collection = "dishes")
public class Dishe {
    @BsonId
    private ObjectId id;
    private String description;
    private String name;
    private BigDecimal price;
    private List<Ingredient> ingredients;
    private List<FoodImage> images;
    private FoodTypes type;
    private Boolean isAvailable;

    public ObjectId getId() {
        return id;
    }

    public Dishe setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dishe setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Dishe setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Dishe setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Dishe setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<FoodImage> getImages() {
        return images;
    }

    public Dishe setImages(List<FoodImage> images) {
        this.images = images;
        return this;
    }

    public FoodTypes getType() {
        return type;
    }

    public Dishe setType(FoodTypes type) {
        this.type = type;
        return this;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Dishe setIsAvailable(Boolean available) {
        isAvailable = available;
        return this;
    }
}
