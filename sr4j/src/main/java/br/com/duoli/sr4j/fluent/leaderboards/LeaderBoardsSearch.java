package br.com.duoli.sr4j.fluent.leaderboards;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.Envelope;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.services.LeaderboardsService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

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
        if (gameId == null || categoryId == null) {
            throw new SearchException("Inform at least a Game AND Category");
        }
        Leaderboard leaderboard = null;
        Response<Envelope<Leaderboard>> response;
        try {
            if (levelId == null) {
                response = leaderboardsService
                        .getLeaderBoards(gameId, categoryId, queryParams)
                        .execute();
            } else {
                response = leaderboardsService
                        .getLevelLeaderBoards(gameId, categoryId, levelId, queryParams)
                        .execute();
            }
            if (!response.isSuccessful()) {
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            leaderboard = response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }
}
