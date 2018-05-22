package br.com.duoli.speedrunapp.presenter;

import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public interface LeaderboardContract {

    interface View extends BaseView {

        void displayLeaderboard(Leaderboard leaderboard);

    }

    interface Presenter extends ReloadPresenter {

        void loadData(String gameId, String categoryId);

        void setView(LeaderboardContract.View view);

        void destroy();
    }

}
