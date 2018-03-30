package br.com.duoli.sr4j.leaderboards;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.common.Link;
import br.com.duoli.sr4j.common.TimeType;

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
    private List<LeaderboardPlace> runs;
    private List<Link> links;
    private Map<String, String> values;

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
}
