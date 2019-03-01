package com.nidara.sabanasblancas.gestion.model.dtos;

import java.util.List;

public class PagedResult<T> {

    public static final long DEFAULT_PAGE = 0;

    public static final int DEFAULT_SIZE = 20;

    private int page;

    private int size;

    private long totalElements;

    private List<T> elements;

    public PagedResult(List<T> elements, long totalElements, int page, int size) {

        this.elements = elements;

        this.totalElements = totalElements;

        this.page = page;

        this.size = size;

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}