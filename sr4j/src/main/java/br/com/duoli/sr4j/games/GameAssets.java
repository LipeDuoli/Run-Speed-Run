package br.com.duoli.sr4j.games;

import com.google.gson.annotations.SerializedName;

public class GameAssets {

    private ImageAsset logo;
    @SerializedName("cover-tiny")
    private ImageAsset coverTiny;
    @SerializedName("cover-small")
    private ImageAsset coverSmall;
    @SerializedName("cover-medium")
    private ImageAsset coverMedium;
    @SerializedName("cover-large")
    private ImageAsset coverLarge;
    private ImageAsset icon;
    @SerializedName("trophy-1st")
    private ImageAsset trophyFirst;
    @SerializedName("trophy-2nd")
    private ImageAsset trophySecond;
    @SerializedName("trophy-3rd")
    private ImageAsset trophyThird;
    @SerializedName("trophy-4th")
    private ImageAsset trophyFourth;
    private ImageAsset background;
    private ImageAsset foreground;

    public ImageAsset getLogo() {
        return logo;
    }

    public ImageAsset getCoverTiny() {
        return coverTiny;
    }

    public ImageAsset getCoverSmall() {
        return coverSmall;
    }

    public ImageAsset getCoverMedium() {
        return coverMedium;
    }

    public ImageAsset getCoverLarge() {
        return coverLarge;
    }

    public ImageAsset getIcon() {
        return icon;
    }

    public ImageAsset getTrophyFirst() {
        return trophyFirst;
    }

    public ImageAsset getTrophySecond() {
        return trophySecond;
    }

    public ImageAsset getTrophyThird() {
        return trophyThird;
    }

    public ImageAsset getTrophyFourth() {
        return trophyFourth;
    }

    public ImageAsset getBackground() {
        return background;
    }

    public ImageAsset getForeground() {
        return foreground;
    }
}
