package br.com.duoli.sr4j.models.leaderboards;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.models.common.JsonEmbedDataListAdapter;
import br.com.duoli.sr4j.models.common.Link;
import br.com.duoli.sr4j.models.common.TimeType;
import br.com.duoli.sr4j.models.platforms.Platform;
import br.com.duoli.sr4j.models.regions.Region;
import br.com.duoli.sr4j.models.users.User;
import br.com.duoli.sr4j.models.variables.Variable;

public class Leaderboard {

    private String game;
    private String weblink;
    private String category;
    private String level;
    private String platform;
    private String region;
    private String emulators;
    @SerializedName("video-only")
    private boolean videoOnly;
    private TimeType timing;
    private Map<String, String> values;
    private List<LeaderboardPlace> runs;
    private List<Link> links;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<User> players;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Region> regions;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Platform> platforms;
    @JsonAdapter(JsonEmbedDataListAdapter.class)
    private List<Variable> variables;

    public String getGame() {
        return game;
    }

    public String getWeblink() {
        return weblink;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }

    public String getPlatform() {
        return platform;
    }

    public String getRegion() {
        return region;
    }

    public String getEmulators() {
        return emulators;
    }

    public boolean isVideoOnly() {
        return videoOnly;
    }

    public TimeType getTiming() {
        return timing;
    }

    public List<LeaderboardPlace> getRuns() {
        return runs;
    }

    public List<Link> getLinks() {
        return links;
    }

    public Map<String, String> getValues() {
        return values;
    }

    /**
     * @return a list of players only if you embed this resource on query
     */
    public List<User> getPlayers() {
        return players;
    }

    /**
     * @return a list of variables only if you embed this resource on query
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * @return a list of plataforms only if you embed this resource on query
     */
    public List<Platform> getPlatforms() {
        return platforms;
    }

    /**
     * @return a list of regions only if you embed this resource on query
     */
    public List<Region> getRegions() {
        return regions;
    }
}
