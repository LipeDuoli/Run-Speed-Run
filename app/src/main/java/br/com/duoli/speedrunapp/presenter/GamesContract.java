package br.com.duoli.speedrunapp.presenter;

import java.util.List;

import br.com.duoli.sr4j.models.games.Game;

public interface GamesContract {

    interface View extends BaseView {

        void displayGames(List<Game> runList);

    }

    interface Presenter extends DataPresenter {

        void setView(GamesContract.View view);

        void destroy();

        void loadMore();
    }
}
