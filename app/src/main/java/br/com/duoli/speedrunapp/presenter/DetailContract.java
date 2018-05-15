package br.com.duoli.speedrunapp.presenter;

import br.com.duoli.sr4j.models.games.Game;

public interface DetailContract {

    interface View extends BaseView {

        void displayGameInfo(Game game);

    }

    interface Presenter {

        void loadData(String gameId);

        void setView(DetailContract.View view);

        void destroy();
    }
}
