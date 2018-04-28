package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.fluent.common.OrderBy;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;

public interface IGameParams {

    IGameParams withName(String name);

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

    IGameParams embedResource(Embed.Games... resources);

    PageableList<Game> fetch();

    IGameParams orderBy(OrderBy.Games orderBy);

    IGameParams asc();

    IGameParams desc();

    IGameParams max(int itens);

    IGameParams offset(int page);

}
