package br.com.duoli.sr4j.models.common;

import java.util.List;

public class PageableList<T> {

    private List<T> data;
    private Pagination pagination;

    public List<T> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
