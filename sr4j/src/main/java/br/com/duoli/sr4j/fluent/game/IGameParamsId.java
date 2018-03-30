package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.games.Game;

public interface IGameParamsId {

    Game fetch();

    IGameCategories getCategories();

    IGameLevels getLevels();

    IGameVariables getVariables();

    IDerivedGames getDerivedGames();

    IGameRecords getGameRecords();

}
