package br.com.duoli.sr4j.fluent.leaderboards;

import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.common.TimeType;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public interface ILeaderboardsParams {

    ILeaderboardsParams toGame(String gameId);

    ILeaderboardsParams toCategory(String categoryId);

    ILeaderboardsParams toLevel(String levelId);

    Leaderboard fetch();

    ILeaderboardsParams top(int topN);

    ILeaderboardsParams fromPlataform(String plataformId);

    ILeaderboardsParams fromRegion(String regionId);

    ILeaderboardsParams onlyEmulator();

    ILeaderboardsParams onlyRealDevice();

    ILeaderboardsParams onlyWithVideos();

    ILeaderboardsParams timing(TimeType time);

    ILeaderboardsParams beforeDate(int year, int month, int day);

    ILeaderboardsParams withVariable(String baseVariable, String equalVariable);

    ILeaderboardsParams embedResource(Embed.LeaderBoards... resources);

}
