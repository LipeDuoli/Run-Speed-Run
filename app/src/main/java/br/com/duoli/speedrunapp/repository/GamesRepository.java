package br.com.duoli.speedrunapp.repository;

import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import io.reactivex.Single;

public interface GamesRepository {

    Single<PageableList<Game>> getGames(int pageOffset);
}