package br.com.duoli.sr4j.models.levels;

import java.util.List;

import br.com.duoli.sr4j.models.common.Link;

public class Level {

    private String id;
    private String name;
    private String weblink;
    private String rules;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeblink() {
        return weblink;
    }

    public String getRules() {
        return rules;
    }

    public List<Link> getLinks() {
        return links;
    }
}
