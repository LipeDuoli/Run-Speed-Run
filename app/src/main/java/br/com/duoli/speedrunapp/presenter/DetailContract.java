package br.com.duoli.speedrunapp.presenter;

import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public interface DetailContract {

    interface View extends BaseView {

        void displayLeaderboard(Leaderboard leaderboard);

    }

    interface Presenter {

        void loadData(String gameId, String categoryId);

        void setView(DetailContract.View view);

        void destroy();
    }
}
