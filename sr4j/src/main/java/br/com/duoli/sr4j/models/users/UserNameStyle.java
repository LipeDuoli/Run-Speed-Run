package br.com.duoli.sr4j.models.users;

import com.google.gson.annotations.SerializedName;

class UserNameStyle {

    private UserColorType style;
    private UserColor color;
    @SerializedName("color-from")
    private UserColor colorFrom;
    @SerializedName("color-to")
    private UserColor colorTo;

    public UserColorType getStyle() {
        return style;
    }

    /**
     * @return User color only if style is solid
     */
    public UserColor getColor() {
        return color;
    }

    /**
     * @return User color only if style is gradient
     */
    public UserColor getColorFrom() {
        return colorFrom;
    }

    /**
     * @return User color only if style is gradient
     */
    public UserColor getColorTo() {
        return colorTo;
    }
}
