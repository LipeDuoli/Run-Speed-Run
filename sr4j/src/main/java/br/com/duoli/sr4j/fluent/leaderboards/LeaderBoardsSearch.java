package br.com.duoli.sr4j.fluent.leaderboards;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.services.LeaderboardsService;

public class LeaderBoardsSearch implements ILeaderboardsParams {

    private LeaderboardsService leaderboardsService;
    private Map<String, String> queryParams;
    private String gameId;
    private String categoryId;
    private String levelId;

    public LeaderBoardsSearch(LeaderboardsService service) {
        this.leaderboardsService = service;
        this.queryParams = new HashMap<>();
    }


    @Override
    public ILeaderboardsParams toGame(String gameId) {
        this.gameId = gameId;
        return this;
    }

    @Override
    public ILeaderboardsParams toCategory(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    @Override
    public ILeaderboardsParams toLevel(String levelId) {
        this.levelId = levelId;
        return this;
    }

    @Override
    public Leaderboard fetch() {
        Leaderboard leaderboard;
        try {
            if (levelId == null) {
                leaderboard = leaderboardsService.getLeaderBoards(gameId, categoryId, queryParams)
                        .execute()
                        .body()
                        .getData();
            } else {
                leaderboard = leaderboardsService
                        .getLevelLeaderBoards(gameId, categoryId, levelId, queryParams)
                        .execute()
                        .body()
                        .getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
            leaderboard = null;
        }
        return leaderboard;
    }
}
