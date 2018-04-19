package br.com.duoli.sr4j.models.variables;

import com.google.gson.annotations.SerializedName;

public enum VariableScopeTypes {

    @SerializedName("global") GLOBAL,
    @SerializedName("full-game") FULL_GAME,
    @SerializedName("all-levels") ALL_LEVELS,
    @SerializedName("single-level") SINGLE_LEVEL
}
