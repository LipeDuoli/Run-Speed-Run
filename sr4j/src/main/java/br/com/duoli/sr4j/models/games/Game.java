package br.com.duoli.sr4j.models.games;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.common.EnvelopeList;
import br.com.duoli.sr4j.models.common.Link;
import br.com.duoli.sr4j.models.levels.Level;

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
    private EnvelopeList<Level> levels;
    private EnvelopeList<Category> categories;

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

    /**
     * @return a list of levels only if you embed this resource on query
     */
    public List<Level> getLevels() {
        return levels.getData();
    }

    /**
     * @return a list of categories only if you embed this resource on query
     */
    public List<Category> getCategories() {
        return categories.getData();
    }
}
