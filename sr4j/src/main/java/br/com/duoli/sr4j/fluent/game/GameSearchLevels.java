package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.List;

import br.com.duoli.sr4j.models.levels.Level;
import br.com.duoli.sr4j.services.GameService;

class GameSearchLevels implements IGameLevels {

    private final GameService gameService;
    private final String gameId;

    public GameSearchLevels(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public List<Level> fetch() {
        try {
            return gameService.levelsForId(gameId).execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
