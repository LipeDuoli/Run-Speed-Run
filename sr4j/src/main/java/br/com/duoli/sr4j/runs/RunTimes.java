package br.com.duoli.sr4j.runs;

import com.google.gson.annotations.SerializedName;

class RunTimes {

    @SerializedName("primary")
    private String primary;
    @SerializedName("primary_t")
    private float primaryT;
    @SerializedName("realtime")
    private String realtime;
    @SerializedName("realtime_t")
    private float realtimeT;
    @SerializedName("realtime_noloads")
    private String realtimeNoloads;
    @SerializedName("realtime_noloads_t")
    private float realtimeNoloadsT;
    @SerializedName("ingame")
    private String ingame;
    @SerializedName("ingame_t")
    private float ingameT;

    public String getPrimary() {
        return primary;
    }

    public float getPrimaryT() {
        return primaryT;
    }

    public String getRealtime() {
        return realtime;
    }

    public float getRealtimeT() {
        return realtimeT;
    }

    public String getRealtimeNoloads() {
        return realtimeNoloads;
    }

    public float getRealtimeNoloadsT() {
        return realtimeNoloadsT;
    }

    public String getIngame() {
        return ingame;
    }

    public float getIngameT() {
        return ingameT;
    }
}
