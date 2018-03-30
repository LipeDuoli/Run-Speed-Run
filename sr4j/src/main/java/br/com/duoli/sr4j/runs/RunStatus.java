package br.com.duoli.sr4j.runs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

class RunStatus {

    private RunStatusType status;
    private String examiner;
    @SerializedName("verify-date")
    private Date verifyDate;
    private String reason;

    public RunStatusType getStatus() {
        return status;
    }

    public String getExaminer() {
        return examiner;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public String getReason() {
        return reason;
    }
}
