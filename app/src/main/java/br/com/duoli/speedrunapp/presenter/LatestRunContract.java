package br.com.duoli.speedrunapp.presenter;

import java.util.List;

import br.com.duoli.sr4j.models.runs.Run;

public interface LatestRunContract {

    interface View {

        void displayRuns(List<Run> runList);

        void showLoadingLayout();

        void hideLoadingLayout();

        void displayNotFoundLayout();

        void hideNotFoundLayout();

    }

    interface Presenter {

        void loadLatestRuns();

        void setView(LatestRunContract.View view);

        void destroy();
    }
}
