package br.com.duoli.speedrunapp.presenter;

import br.com.duoli.sr4j.models.games.Game;

public interface DetailContract {

    interface View extends BaseView {

        void displayGameInfo(Game game);

    }

    interface Presenter extends ReloadPresenter {

        void loadData(String gameId);

        void setView(DetailContract.View view);

        void destroy();
    }
}
