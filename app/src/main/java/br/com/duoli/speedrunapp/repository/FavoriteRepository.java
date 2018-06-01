package br.com.duoli.speedrunapp.repository;

import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public interface FavoriteRepository {

    boolean favoriteLeaderboard(Leaderboard leaderboard);

    int removeFavoriteLeaderboard(String gameId, String categoryId);

    boolean isfavorited(String gameId, String categoryId);
}
