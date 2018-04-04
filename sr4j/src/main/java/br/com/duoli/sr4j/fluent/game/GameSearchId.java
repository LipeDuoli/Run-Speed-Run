package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.common.Envelope;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

public class GameSearchId implements IGameParamsId {

    private GameService gameService;
    private String gameId;
    private Map<String, String> queryParams;

    public GameSearchId(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
        this.queryParams = new HashMap<>();
    }

    @Override
    public Game fetch() {
        try {
            Response<Envelope<Game>> response = gameService.withId(gameId, queryParams).execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IGameParamsId embedResource(Embed.Games... resources) {
        if (resources != null) {
            StringBuilder builder = new StringBuilder();
            for (Embed.Games r : resources) {
                builder.append(r.toString()).append(",");
            }
            queryParams.put("embed", builder.toString());
        }
        return this;
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
