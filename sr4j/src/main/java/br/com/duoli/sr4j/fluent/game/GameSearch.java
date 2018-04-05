package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

public class GameSearch implements IGameParams {

    private GameService gameService;
    private Map<String, String> queryParams;

    public GameSearch(GameService gameService) {
        this.gameService = gameService;
        this.queryParams = new HashMap<>();
    }

    @Override
    public IGameParams withName(String name) {
        queryParams.put("name", name);
        return this;
    }

    public IGameParamsId withId(String id) {
        return new GameSearchId(gameService, id);
    }

    @Override
    public IGameParams withAbreviation(String abreviation) {
        queryParams.put("abbreviation", abreviation);
        return this;
    }

    @Override
    public IGameParams releasedIn(int year) {
        queryParams.put("released", String.valueOf(year));
        return this;
    }

    @Override
    public IGameParams toGameType(String gameTypeId) {
        queryParams.put("gametype", gameTypeId);
        return this;
    }

    @Override
    public IGameParams toPlataform(String plataformId) {
        queryParams.put("platform", plataformId);
        return this;
    }

    @Override
    public IGameParams toRegion(String regionId) {
        queryParams.put("toRegion", regionId);
        return this;
    }

    @Override
    public IGameParams withGenre(String genreId) {
        queryParams.put("withGenre", genreId);
        return this;
    }

    @Override
    public IGameParams withEngine(String engineId) {
        queryParams.put("withEngine", engineId);
        return this;
    }

    @Override
    public IGameParams fromDeveloper(String developerId) {
        queryParams.put("fromDeveloper", developerId);
        return this;
    }

    @Override
    public IGameParams fromPublisher(String publisherId) {
        queryParams.put("fromPublisher", publisherId);
        return this;
    }

    @Override
    public IGameParams fromModerator(String moderatorId) {
        queryParams.put("fromModerator", moderatorId);
        return this;
    }

    @Override
    public IGameParams bulk() {
        queryParams.put("_bulk", "true");
        return this;
    }

    @Override
    public IGameParams embedResource(Embed.Games... resources) {
        StringBuilder builder = new StringBuilder();
        for (Embed.Games r: resources) {
            builder.append(r.toString()).append(",");
        }
        queryParams.put("embed", builder.toString());
        return this;
    }

    @Override
    public PageableList<Game> fetch() {
        try {
            Response<PageableList<Game>> response = gameService.getAll(queryParams).execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
