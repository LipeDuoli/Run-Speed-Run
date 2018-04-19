package br.com.duoli.sr4j.models.users;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import br.com.duoli.sr4j.models.common.Link;

public class User {

    private String id;
    private UserNames names;
    private String name;
    private String weblink;
    @SerializedName("name-style")
    private UserNameStyle nameStyle;
    private String role;
    private Date signup;
    private UserLocation location;
    private Link twitch;
    private Link hitbox;
    private Link youtube;
    private Link twitter;
    private Link speedrunslive;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public UserNames getNames() {
        return names;
    }

    public String getWeblink() {
        return weblink;
    }

    public UserNameStyle getNameStyle() {
        return nameStyle;
    }

    public String getRole() {
        return role;
    }

    public Date getSignup() {
        return signup;
    }

    public UserLocation getLocation() {
        return location;
    }

    public Link getTwitch() {
        return twitch;
    }

    public Link getHitbox() {
        return hitbox;
    }

    public Link getYoutube() {
        return youtube;
    }

    public Link getTwitter() {
        return twitter;
    }

    public Link getSpeedrunslive() {
        return speedrunslive;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getName() {
        if (name == null){
            if (names != null){
                return names.getInternational();
            } else {
                return "";
            }
        }
        return name;
    }
}
