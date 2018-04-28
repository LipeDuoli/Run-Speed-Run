package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

class GameSearchRecords implements IGameRecords {

    private final GameService gameService;
    private final String gameId;
    private Map<String, String> queryParams;

    public GameSearchRecords(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
        this.queryParams = new HashMap<>();
    }

    @Override
    public PageableList<Leaderboard> fetch() {
        try {
            Response<PageableList<Leaderboard>> response = gameService
                    .recordsForId(gameId, queryParams)
                    .execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body();
        } catch (IOException e) {
            throw new SearchException(e.getMessage());
        }
    }

    @Override
    public IGameRecords max(int itens) {
        queryParams.put("max", String.valueOf(itens));
        return this;
    }

    @Override
    public IGameRecords offset(int page) {
        queryParams.put("offset", String.valueOf(page));
        return this;
    }
}
