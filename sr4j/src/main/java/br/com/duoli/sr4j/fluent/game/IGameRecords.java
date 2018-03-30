package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.leaderboards.Leaderboard;

public interface IGameRecords {

    PageableList<Leaderboard> fetch();
}
