package br.com.duoli.speedrunapp.repository;

import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import io.reactivex.Single;

public interface LeaderboardRepository {

    Single<Leaderboard> getLeaderboard(String gameId, String categoryId);
}
