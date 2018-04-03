package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;

import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.services.GameService;

public class GameSearchId implements IGameParamsId {

    private GameService gameService;
    private String gameId;

    public GameSearchId(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public Game fetch() {
        try {
            return gameService.withId(gameId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IGameCategories getCategories() {
        return new GameSearchCategory(gameService, gameId);
    }

    @Override
    public IGameLevels getLevels() {
        return new GameSearchLevels(gameService, gameId);
    }

    @Override
    public IGameVariables getVariables() {
        return new GameSearchVariables(gameService, gameId);
    }

    @Override
    public IDerivedGames getDerivedGames() {
        return new GameSearchDeriveds(gameService, gameId);
    }

    @Override
    public IGameRecords getGameRecords() {
        return new GameSearchRecords(gameService, gameId);
    }
}
