package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;
import java.util.List;

import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.services.GameService;

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
            return gameService.categoriesForId(gameId).execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
