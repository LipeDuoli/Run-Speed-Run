package br.com.duoli.sr4j.models.runs;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.duoli.sr4j.models.common.Link;

public class Run {

    private String id;
    private String weblink;
    private String game;
    private String level;
    private String category;
    private RunVideo video;
    private String comment;
    private RunStatus status;
    private List<RunPlayer> players;
    private Date date;
    private String submitted;
    private RunTimes times;
    private RunSystem system;
    private Link splits;
    private Map<String, String> values;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public String getWeblink() {
        return weblink;
    }

    public String getGame() {
        return game;
    }

    public String getLevel() {
        return level;
    }

    public String getCategory() {
        return category;
    }

    public RunVideo getVideo() {
        return video;
    }

    public String getComment() {
        return comment;
    }

    public RunStatus getStatus() {
        return status;
    }

    public List<RunPlayer> getPlayers() {
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
}
