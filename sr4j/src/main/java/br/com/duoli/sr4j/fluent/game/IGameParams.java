package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.games.Game;

public interface IGameParams {

    IGameParams withName(String name);

    IGameParamsId withId(String id);

    IGameParams withAbreviation(String abreviation);

    IGameParams releasedIn(int year);

    IGameParams toGameType(String gameTypeId);

    IGameParams toPlataform(String plataformId);

    IGameParams toRegion(String regionId);

    IGameParams withGenre(String genreId);

    IGameParams withEngine(String engineId);

    IGameParams fromDeveloper(String developerId);

    IGameParams fromPublisher(String publisherId);

    IGameParams fromModerator(String moderatorId);

    IGameParams bulk();

    PageableList<Game> fetch();

}
