package com.cvccorp.store.model.entities;

public class FoodImage {
    private String url;
    private String description;
    private Boolean isThumbnail;

    public String getUrl() {
        return url;
    }

    public FoodImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FoodImage setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getIsThumbnail() {
        return isThumbnail;
    }

    public FoodImage setIsThumbnail(Boolean thumbnail) {
        isThumbnail = thumbnail;
        return this;
    }
}
