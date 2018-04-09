package br.com.duoli.sr4j.models.genres;

import java.util.List;

import br.com.duoli.sr4j.models.common.Link;

public class Genre {

    private String id;
    private String name;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Link> getLinks() {
        return links;
    }
}
