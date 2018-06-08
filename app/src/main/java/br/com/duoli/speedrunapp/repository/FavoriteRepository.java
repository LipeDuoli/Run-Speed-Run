package br.com.duoli.speedrunapp.repository;

import java.util.List;

import br.com.duoli.speedrunapp.model.FavoriteGame;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import io.reactivex.Single;

public interface FavoriteRepository {

    boolean favoriteLeaderboard(Leaderboard leaderboard);

    int removeFavoriteLeaderboard(String gameId, String categoryId);

    boolean isfavorited(String gameId, String categoryId);

    Single<List<FavoriteGame>> loadFavoriteGames();
}
