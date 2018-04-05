package br.com.duoli.sr4j.models.platforms;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.duoli.sr4j.models.common.Link;

public class Platform {

    private String id;
    private String name;
    @SerializedName("released")
    private int yearReleased;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public List<Link> getLinks() {
        return links;
    }
}
