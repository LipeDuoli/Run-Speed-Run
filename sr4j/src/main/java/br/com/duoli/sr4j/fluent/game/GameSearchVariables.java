package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.List;

import br.com.duoli.sr4j.games.GameService;
import br.com.duoli.sr4j.variables.Variable;

class GameSearchVariables implements IGameVariables {

    private final GameService gameService;
    private final String gameId;

    public GameSearchVariables(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public List<Variable> fetch() {
        try {
            return gameService.variablesForId(gameId).execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
