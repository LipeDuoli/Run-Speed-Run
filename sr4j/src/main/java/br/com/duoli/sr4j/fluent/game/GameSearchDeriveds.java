package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;

import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.services.GameService;

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
            return gameService.derivedGamesForId(gameId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
