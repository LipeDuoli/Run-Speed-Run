package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

class GameSearchRecords implements IGameRecords {

    private final GameService gameService;
    private final String gameId;

    public GameSearchRecords(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public PageableList<Leaderboard> fetch() {
        try {
            Response<PageableList<Leaderboard>> response = gameService.recordsForId(gameId).execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body();
        } catch (SearchException | IOException e) {
            e.printStackTrace();
            return new PageableList<>();
        }
    }
}
