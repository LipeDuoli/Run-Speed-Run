package br.com.duoli.sr4j.categories;

import java.util.List;

import br.com.duoli.sr4j.common.Link;

public class Category {

    private String id;
    private String name;
    private String weblink;
    private CategoryTypes type;
    private String rules;
    private CategoryPlayers players;
    private boolean miscellaneous;
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

    public CategoryTypes getType() {
        return type;
    }

    public String getRules() {
        return rules;
    }

    public CategoryPlayers getPlayers() {
        return players;
    }

    public boolean isMiscellaneous() {
        return miscellaneous;
    }

    public List<Link> getLinks() {
        return links;
    }
}
