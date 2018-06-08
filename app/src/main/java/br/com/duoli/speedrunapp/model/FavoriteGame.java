package br.com.duoli.speedrunapp.model;

public class FavoriteGame {

    private String gameId;
    private String gameName;
    private String gameCoverPath;
    private String categoryId;
    private String categoryName;
    private String firstPlaceId;
    private String firstPlaceAssetPath;

    public FavoriteGame() {
    }

    public FavoriteGame(String gameId, String gameName, String gameCoverPath, String categoryId, String categoryName, String firstPlaceId, String firstPlaceAssetPath) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameCoverPath = gameCoverPath;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.firstPlaceId = firstPlaceId;
        this.firstPlaceAssetPath = firstPlaceAssetPath;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameCoverPath() {
        return gameCoverPath;
    }

    public void setGameCoverPath(String gameCoverPath) {
        this.gameCoverPath = gameCoverPath;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFirstPlaceId() {
        return firstPlaceId;
    }

    public void setFirstPlaceId(String firstPlaceId) {
        this.firstPlaceId = firstPlaceId;
    }

    public String getFirstPlaceAssetPath() {
        return firstPlaceAssetPath;
    }

    public void setFirstPlaceAssetPath(String firstPlaceAssetPath) {
        this.firstPlaceAssetPath = firstPlaceAssetPath;
    }
}
