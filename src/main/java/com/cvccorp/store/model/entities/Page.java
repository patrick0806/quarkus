package com.cvccorp.store.model.entities;

import java.util.List;

public class Page<T> {
    private int page;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private List<T> content;

    public int getPage() {
        return page;
    }

    public Page<T> setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public Page<T> setTotalElements(int totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Page<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public Page<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }
}
