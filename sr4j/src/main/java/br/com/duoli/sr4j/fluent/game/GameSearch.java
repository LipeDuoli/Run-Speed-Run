package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.games.Game;
import br.com.duoli.sr4j.games.GameService;

public class GameSearch implements IGameParams {

    private GameService gameService;
    private Map<String, String> queryParams;

    public GameSearch(GameService gameService) {
        this.gameService = gameService;
        this.queryParams = new HashMap<>();
    }

    @Override
    public IGameParams name(String name) {
        queryParams.put("name", name);
        return this;
    }

    @Override
    public IGameParamsId id(String id) {
        return new GameSearchId(gameService, id);
    }

    @Override
    public IGameParams abreviation(String abreviation) {
        queryParams.put("abbreviation", abreviation);
        return this;
    }

    @Override
    public IGameParams released(int year) {
        queryParams.put("released", String.valueOf(year));
        return this;
    }

    @Override
    public IGameParams gameType(String gameTypeId) {
        queryParams.put("gametype", gameTypeId);
        return this;
    }

    @Override
    public IGameParams plataform(String plataformId) {
        queryParams.put("platform", plataformId);
        return this;
    }

    @Override
    public IGameParams region(String regionId) {
        queryParams.put("region", regionId);
        return this;
    }

    @Override
    public IGameParams genre(String genreId) {
        queryParams.put("genre", genreId);
        return this;
    }

    @Override
    public IGameParams engine(String engineId) {
        queryParams.put("engine", engineId);
        return this;
    }

    @Override
    public IGameParams developer(String developerId) {
        queryParams.put("developer", developerId);
        return this;
    }

    @Override
    public IGameParams publisher(String publisherId) {
        queryParams.put("publisher", publisherId);
        return this;
    }

    @Override
    public IGameParams moderator(String moderatorId) {
        queryParams.put("moderator", moderatorId);
        return this;
    }

    @Override
    public IGameParams bulk() {
        queryParams.put("_bulk", "true");
        return this;
    }

    @Override
    public PageableList<Game> fetch() {
        try {
            return gameService.getAll(queryParams).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
