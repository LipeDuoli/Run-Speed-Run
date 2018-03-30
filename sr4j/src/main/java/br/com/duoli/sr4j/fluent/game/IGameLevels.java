package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.common.EnvelopeList;
import br.com.duoli.sr4j.levels.Level;

public interface IGameLevels {

    EnvelopeList<Level> fetch();
}
