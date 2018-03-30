package br.com.duoli.sr4j.games;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.duoli.sr4j.common.TimeType;

public class GameRuleSet {

    @SerializedName("show-milliseconds")
    private boolean showMilliseconds;
    @SerializedName("require-verification")
    private boolean verificationRequired;
    @SerializedName("require-video")
    private boolean videoRequired;
    @SerializedName("run-times")
    private List<TimeType> runTimes;
    @SerializedName("default-time")
    private String defaultTime;
    @SerializedName("emulators-allowed")
    private boolean emulatorsAllowed;

    public boolean isShowMilliseconds() {
        return showMilliseconds;
    }

    public boolean isVerificationRequired() {
        return verificationRequired;
    }

    public boolean isVideoRequired() {
        return videoRequired;
    }

    public List<TimeType> getRunTimes() {
        return runTimes;
    }

    public String getDefaultTime() {
        return defaultTime;
    }

    public boolean isEmulatorsAllowed() {
        return emulatorsAllowed;
    }
}
