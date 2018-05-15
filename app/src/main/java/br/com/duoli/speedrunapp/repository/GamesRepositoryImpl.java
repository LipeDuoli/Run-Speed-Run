package br.com.duoli.speedrunapp.repository;

import java.util.concurrent.Callable;

import br.com.duoli.sr4j.SpeedRun4jClient;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.fluent.game.GameSearch;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import io.reactivex.Single;

public class GamesRepositoryImpl implements GamesRepository {

    private final GameSearch gameSearch;

    public GamesRepositoryImpl() {
        gameSearch = SpeedRun4jClient.getGame();
    }

    @Override
    public Single<PageableList<Game>> getGames(final int pageOffset) {
        return Single.fromCallable(new Callable<PageableList<Game>>() {
            @Override
            public PageableList<Game> call() {
                return gameSearch.offset(pageOffset).fetch();
            }
        });
    }

    @Override
    public Single<Game> getGame(final String gameId) {
        return Single.fromCallable(new Callable<Game>() {
            @Override
            public Game call() {
                return gameSearch.withId(gameId)
                        .embedResource(Embed.Games.PLATAFORMS, Embed.Games.CATEGORIES)
                        .fetch();
            }
        });
    }
}
