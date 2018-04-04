package br.com.duoli.sr4j.fluent.leaderboards;

import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public interface ILeaderboardsParams {

    ILeaderboardsParams toGame(String gameId);
    ILeaderboardsParams toCategory(String categoryId);
    ILeaderboardsParams toLevel(String levelId);
    Leaderboard fetch();
}
