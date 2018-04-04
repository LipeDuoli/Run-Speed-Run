package br.com.duoli.sr4j.models.common;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {

    private int status;
    private String message = "";
    private List<Link> links = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Link> getLinks() {
        return links;
    }
}
