package br.com.duoli.sr4j.fluent.game;

import java.io.IOException;

import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.games.GameService;
import br.com.duoli.sr4j.leaderboards.Leaderboard;

class GameSearchRecords implements IGameRecords {

    private final GameService gameService;
    private final String gameId;

    public GameSearchRecords(GameService gameService, String gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public PageableList<Leaderboard> fetch() {
        try {
            return gameService.recordsForId(gameId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
