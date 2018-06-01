package br.com.duoli.speedrunapp.repository;

import java.util.concurrent.Callable;

import br.com.duoli.sr4j.SpeedRun4jClient;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.fluent.leaderboards.LeaderBoardsSearch;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import io.reactivex.Single;

public class LeaderboardRepositoryImpl implements LeaderboardRepository {

    private final LeaderBoardsSearch mLeaderboardsSearch;

    public LeaderboardRepositoryImpl() {
        mLeaderboardsSearch = SpeedRun4jClient.getLeaderbard();
    }

    @Override
    public Single<Leaderboard> getLeaderboard(final String gameId, final String categoryId) {
        return Single.fromCallable(new Callable<Leaderboard>() {
            @Override
            public Leaderboard call() {
                return mLeaderboardsSearch.toGame(gameId)
                        .toCategory(categoryId)
                        .embedResource(Embed.LeaderBoards.PLAYERS,
                                Embed.LeaderBoards.GAME,
                                Embed.LeaderBoards.CATEGORY)
                        .top(30)
                        .fetch();
            }
        });
    }
}
