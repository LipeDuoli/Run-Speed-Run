package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.List;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.common.EnvelopeList;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

class GameSearchCategory implements IGameCategories {

    private final GameService gameService;
    private final String gameId;

    public GameSearchCategory(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public List<Category> fetch() {
        try {
            Response<EnvelopeList<Category>> response = gameService.categoriesForId(gameId).execute();
            if (!response.isSuccessful()){
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body().getData();
        } catch (IOException e) {
            throw new SearchException(e.getMessage());
        }
    }
}
