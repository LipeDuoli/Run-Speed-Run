package br.com.duoli.sr4j.fluent.game;

import br.com.duoli.sr4j.categories.Category;
import br.com.duoli.sr4j.common.EnvelopeList;

public interface IGameCategories {

    EnvelopeList<Category> fetch();
}
