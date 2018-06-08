package br.com.duoli.speedrunapp.presenter;

import java.util.List;

import br.com.duoli.speedrunapp.model.FavoriteGame;

public interface FavoriteContract {

    interface View extends BaseView {

        void displayGames(List<FavoriteGame> gamesList);

    }

    interface Presenter extends ReloadPresenter {

        void loadData();

        void setView(FavoriteContract.View view);

        void destroy();
    }
}
