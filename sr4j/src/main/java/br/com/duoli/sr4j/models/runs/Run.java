package br.com.duoli.sr4j.models.runs;

import com.google.gson.annotations.JsonAdapter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.common.JsonEmbedDataAdapter;
import br.com.duoli.sr4j.models.common.JsonEmbedDataUserAdapter;
import br.com.duoli.sr4j.models.common.Link;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.models.levels.Level;
import br.com.duoli.sr4j.models.platforms.Platform;
import br.com.duoli.sr4j.models.regions.Region;
import br.com.duoli.sr4j.models.users.User;

public class Run {

    private String id;
    private String weblink;
    @JsonAdapter(JsonEmbedDataAdapter.class)
    private Game game;
    @JsonAdapter(JsonEmbedDataAdapter.class)
    private Level level;
    @JsonAdapter(JsonEmbedDataAdapter.class)
    private Category category;
    private RunVideo videos;
    private String comment;
    private RunStatus status;
    @JsonAdapter(JsonEmbedDataUserAdapter.class)
    private List<User> players;
    private Date date;
    private String submitted;
    private RunTimes times;
    private RunSystem system;
    private Link splits;
    private Map<String, String> values;
    private List<Link> links;
    @JsonAdapter(JsonEmbedDataAdapter.class)
    private Platform platform;
    @JsonAdapter(JsonEmbedDataAdapter.class)
    private Region region;

    public String getId() {
        return id;
    }

    public String getWeblink() {
        return weblink;
    }

    public Game getGame() {
        return game;
    }

    public Level getLevel() {
        return level;
    }

    public Category getCategory() {
        return category;
    }

    public RunVideo getVideos() {
        return videos;
    }

    public String getComment() {
        return comment;
    }

    public RunStatus getStatus() {
        return status;
    }

    public List<User> getPlayers() {
        return players;
    }

    public Date getDate() {
        return date;
    }

    public String getSubmitted() {
        return submitted;
    }

    public RunTimes getTimes() {
        return times;
    }

    public RunSystem getSystem() {
        return system;
    }

    public Link getSplits() {
        return splits;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public List<Link> getLinks() {
        return links;
    }

    /**
     * @return an plataform only if you embed this resource on query
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return an region only if you embed this resource on query
     */
    public Region getRegion() {
        return region;
    }
}
