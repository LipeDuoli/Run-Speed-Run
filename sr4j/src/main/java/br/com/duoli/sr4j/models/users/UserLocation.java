package br.com.duoli.sr4j.models.users;

public class UserLocation {

    private UserLocationDetail country;
    private UserLocationDetail region;

    public UserLocationDetail getCountry() {
        return country;
    }

    public UserLocationDetail getRegion() {
        return region;
    }
}
