package br.com.duoli.sr4j.models.common;

import com.google.gson.annotations.SerializedName;

public enum TimeType {

    @SerializedName("realtime") REALTIME {
        @Override
        public String toString() {
            return "realtime";
        }
    },
    @SerializedName("realtime_noloads") REALTIME_NOLOADS {
        @Override
        public String toString() {
            return "realtime_noloads";
        }
    },
    @SerializedName("ingame") INGAME {
        @Override
        public String toString() {
            return "ingame";
        }
    }
}
