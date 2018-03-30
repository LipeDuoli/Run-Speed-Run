package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.games.Game;

public interface IGameParams {

    IGameParams name(String name);

    IGameParamsId id(String id);

    IGameParams abreviation(String abreviation);

    IGameParams released(int year);

    IGameParams gameType(String gameTypeId);

    IGameParams plataform(String plataformId);

    IGameParams region(String regionId);

    IGameParams genre(String genreId);

    IGameParams engine(String engineId);

    IGameParams developer(String developerId);

    IGameParams publisher(String publisherId);

    IGameParams moderator(String moderatorId);

    IGameParams bulk();

    PageableList<Game> fetch();

}
