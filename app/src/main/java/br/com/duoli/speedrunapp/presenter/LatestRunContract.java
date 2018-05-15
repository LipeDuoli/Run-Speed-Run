package br.com.duoli.speedrunapp.presenter;

import java.util.List;

import br.com.duoli.sr4j.models.runs.Run;

public interface LatestRunContract {

    interface View extends BaseView {

        void displayRuns(List<Run> runList);

    }

    interface Presenter extends ReloadPresenter {

        void loadData();

        void setView(LatestRunContract.View view);

        void destroy();
    }
}
