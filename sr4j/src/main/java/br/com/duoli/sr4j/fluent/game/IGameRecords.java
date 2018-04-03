package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public interface IGameRecords {

    PageableList<Leaderboard> fetch();
}
