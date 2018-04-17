package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.EnvelopeList;
import br.com.duoli.sr4j.models.variables.Variable;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

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
            Response<EnvelopeList<Variable>> response = gameService.variablesForId(gameId).execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body().getData();
        } catch (SearchException | IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
