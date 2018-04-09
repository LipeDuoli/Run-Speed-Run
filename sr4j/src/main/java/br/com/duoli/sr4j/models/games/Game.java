package br.com.duoli.sr4j.models.games;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.common.JsonEmbedDataListAdapter;
import br.com.duoli.sr4j.models.common.Link;
import br.com.duoli.sr4j.models.developers.Developers;
import br.com.duoli.sr4j.models.genres.Genre;
import br.com.duoli.sr4j.models.levels.Level;
import br.com.duoli.sr4j.models.platforms.Platform;
import br.com.duoli.sr4j.models.publishers.Publishers;
import br.com.duoli.sr4j.models.regions.Region;
import br.com.duoli.sr4j.models.variables.Variable;

public class Game {

    private String id;
    private GameNames names;
    private String abbreviation;
    private String weblink;
    @SerializedName("release-date")
    private Date releaseDate;
    private GameRuleSet ruleSet;
    private List<String> gametypes;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Platform> platforms;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Region> regions;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Genre> genres;
    private List<String> engines;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Developers> developers;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Publishers> publishers;
    private Map<String, String> moderators;
    private Date created;
    private GameAssets assets;
    private List<Link> links;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Level> levels;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Category> categories;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Variable> variables;

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

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<String> getEngines() {
        return engines;
    }

    public List<Developers> getDevelopers() {
        return developers;
    }

    public List<Publishers> getPublishers() {
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
        return levels;
    }

    /**
     * @return a list of categories only if you embed this resource on query
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @return a list of variables only if you embed this resource on query
     */
    public List<Variable> getVariables() {
        return variables;
    }
}
