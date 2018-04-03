package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.games.Game;

public interface IGameParamsId {

    Game fetch();

    IGameParamsId embedResource(Embed.Games... resources);

    IGameCategories getCategories();

    IGameLevels getLevels();

    IGameVariables getVariables();

    IDerivedGames getDerivedGames();

    IGameRecords getGameRecords();

}
