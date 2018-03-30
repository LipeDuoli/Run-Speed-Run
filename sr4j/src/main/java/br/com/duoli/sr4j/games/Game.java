package br.com.duoli.sr4j.games;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.common.Link;

public class Game {

    private String id;
    private GameNames names;
    private String abbreviation;
    private String weblink;
    @SerializedName("release-date")
    private Date releaseDate;
    private GameRuleSet ruleSet;
    private List<String> gametypes;
    private List<String> platforms;
    private List<String> regions;
    private List<String> genres;
    private List<String> engines;
    private List<String> developers;
    private List<String> publishers;
    private Map<String, String> moderators;
    private Date created;
    private GameAssets assets;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public GameNames getNames() {
        return names;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getWeblink() {
        return weblink;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public GameRuleSet getRuleSet() {
        return ruleSet;
    }

    public List<String> getGametypes() {
        return gametypes;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public List<String> getRegions() {
        return regions;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getEngines() {
        return engines;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public Map<String, String> getModerators() {
        return moderators;
    }

    public Date getCreated() {
        return created;
    }

    public GameAssets getAssets() {
        return assets;
    }

    public List<Link> getLinks() {
        return links;
    }
}
