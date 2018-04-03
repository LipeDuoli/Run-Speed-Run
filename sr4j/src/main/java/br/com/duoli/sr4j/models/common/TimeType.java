package br.com.duoli.sr4j.models.common;

import com.google.gson.annotations.SerializedName;

public enum TimeType {

    @SerializedName("realtime") REALTIME,
    @SerializedName("realtime_noloads") REALTIME_NOLOADS,
    @SerializedName("ingame") INGAME
}
