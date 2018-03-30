package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.games.Game;

public interface IDerivedGames {

    PageableList<Game> fetch();
}
