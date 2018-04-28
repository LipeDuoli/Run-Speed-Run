package br.com.duoli.sr4j.models.common;

import java.util.Collections;
import java.util.List;

public class PageableList<T> {

    private List<T> data = Collections.emptyList();
    private Pagination pagination;

    public List<T> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public PageableList() {
    }

    public PageableList(List<T> data) {
        this.data = data;
    }
}
