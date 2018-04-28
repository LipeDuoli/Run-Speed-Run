package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

class GameSearchDeriveds implements IDerivedGames {

    private final GameService gameService;
    private final String gameId;
    private Map<String, String> queryParams;

    public GameSearchDeriveds(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
        this.queryParams = new HashMap<>();
    }

    @Override
    public PageableList<Game> fetch() {
        try {
            Response<PageableList<Game>> response = gameService
                    .derivedGamesForId(gameId, queryParams)
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
    public IDerivedGames max(int itens) {
        queryParams.put("max", String.valueOf(itens));
        return this;
    }

    @Override
    public IDerivedGames offset(int page) {
        queryParams.put("offset", String.valueOf(page));
        return this;
    }
}
