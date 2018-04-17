package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

class GameSearchDeriveds implements IDerivedGames {

    private final GameService gameService;
    private final String gameId;

    public GameSearchDeriveds(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public PageableList<Game> fetch() {
        try {
            Response<PageableList<Game>> response = gameService.derivedGamesForId(gameId).execute();
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
