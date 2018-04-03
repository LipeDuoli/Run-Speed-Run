package br.com.duoli.sr4j.models.runs;

import com.google.gson.annotations.SerializedName;

enum RunStatusType {

    @SerializedName("new") NEW,
    @SerializedName("verified") VERIFIED,
    @SerializedName("rejected") REJECTED
}
