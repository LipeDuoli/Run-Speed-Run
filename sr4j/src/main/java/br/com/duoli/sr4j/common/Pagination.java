package br.com.duoli.sr4j.common;

import java.util.List;

public class Pagination {

    private int offset;
    private int max;
    private int size;
    private List<Link> links;

    public int getOffset() {
        return offset;
    }

    public int getMax() {
        return max;
    }

    public int getSize() {
        return size;
    }

    public List<Link> getLinks() {
        return links;
    }
}
