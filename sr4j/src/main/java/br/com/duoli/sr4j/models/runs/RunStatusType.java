package br.com.duoli.sr4j.models.runs;

import com.google.gson.annotations.SerializedName;

public enum RunStatusType {

    @SerializedName("new") NEW {
        @Override
        public String toString() {
            return "new";
        }
    },
    @SerializedName("verified") VERIFIED {
        @Override
        public String toString() {
            return "verified";
        }
    },
    @SerializedName("rejected") REJECTED {
        @Override
        public String toString() {
            return "rejected";
        }
    }
}
