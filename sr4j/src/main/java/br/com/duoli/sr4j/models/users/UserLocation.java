package br.com.duoli.sr4j.models.users;

class UserLocation {

    private UserLocationDetail country;
    private UserLocationDetail region;

    public UserLocationDetail getCountry() {
        return country;
    }

    public UserLocationDetail getRegion() {
        return region;
    }
}
