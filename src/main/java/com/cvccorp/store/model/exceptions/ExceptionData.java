package com.cvccorp.store.model.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionData {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;

    public Integer getStatus() {
        return status;
    }

    public ExceptionData setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getType() {
        return type;
    }

    public ExceptionData setType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ExceptionData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public ExceptionData setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ExceptionData setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "ExceptionData{" +
                "status=" + status +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
