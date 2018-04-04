package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.List;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.EnvelopeList;
import br.com.duoli.sr4j.models.levels.Level;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

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
            Response<EnvelopeList<Level>> response = gameService.levelsForId(gameId).execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
