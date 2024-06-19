package com.cvccorp.store.model.requests.dishe;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;

import java.io.InputStream;

public class UploadDishImageRequest {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;

    @FormParam("dishId")
    private String dishId;

    @FormParam("description")
    private String description;

    @FormParam("isThumbnail")
    private Boolean isThumbnail;


    public InputStream getFile() {
        return file;
    }

    public UploadDishImageRequest setFile(InputStream file) {
        this.file = file;
        return this;
    }

    public String getDishId() {
        return dishId;
    }

    public UploadDishImageRequest setDishId(String dishId) {
        this.dishId = dishId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UploadDishImageRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getThumbnail() {
        return isThumbnail;
    }

    public UploadDishImageRequest setThumbnail(Boolean thumbnail) {
        isThumbnail = thumbnail;
        return this;
    }
}
