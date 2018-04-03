package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;

public interface IDerivedGames {

    PageableList<Game> fetch();
}
