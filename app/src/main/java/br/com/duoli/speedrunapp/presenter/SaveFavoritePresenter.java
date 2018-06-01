package br.com.duoli.speedrunapp.presenter;

public interface SaveFavoritePresenter {

    void favoriteLeaderboard();

    void removeFavoriteLeaderboard();

    boolean isLeaderboardFavorited();
}
