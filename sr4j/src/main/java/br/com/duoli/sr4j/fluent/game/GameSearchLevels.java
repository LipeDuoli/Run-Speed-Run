package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.List;

import br.com.duoli.sr4j.common.EnvelopeList;
import br.com.duoli.sr4j.games.GameService;
import br.com.duoli.sr4j.levels.Level;

class GameSearchLevels implements IGameLevels {

    private final GameService gameService;
    private final String gameId;

    public GameSearchLevels(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public EnvelopeList<Level> fetch() {
        try {
            return gameService.levelsForId(gameId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
