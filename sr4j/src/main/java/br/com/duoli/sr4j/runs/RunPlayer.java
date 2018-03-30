package br.com.duoli.sr4j.runs;

import com.google.gson.annotations.SerializedName;

class RunPlayer {

    @SerializedName("rel")
    private RunPlayerType playerType;
    private String id;
    private String uri;

    public RunPlayerType getPlayerType() {
        return playerType;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }
}
