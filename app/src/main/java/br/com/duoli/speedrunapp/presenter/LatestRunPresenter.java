package br.com.duoli.speedrunapp.presenter;

import android.util.Log;

import java.util.List;

import br.com.duoli.speedrunapp.repository.RunsRepository;
import br.com.duoli.sr4j.models.runs.Run;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LatestRunPresenter implements LatestRunContract.Presenter {

    private final String TAG = LatestRunPresenter.class.getSimpleName();

    private LatestRunContract.View mView;
    private RunsRepository mRunsRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Scheduler mScheduler;
    private List<Run> mRuns;

    public LatestRunPresenter(RunsRepository runsRepository, Scheduler scheduler) {
        mRunsRepository = runsRepository;
        mScheduler = scheduler;
    }

    @Override
    public void loadLatestRuns() {
        if (mRuns != null) {
            mView.displayRuns(mRuns);
            mView.hideLoadingLayout();
        } else {
            loadRuns();
        }
    }

    @Override
    public void setView(LatestRunContract.View view) {
        this.mView = view;
    }

    private void loadRuns() {
        mView.showLoadingLayout();
        mView.hideNotFoundLayout();
        disposable.add(mRunsRepository.getLatestRuns()
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<List<Run>>() {
                    @Override
                    public void onSuccess(List<Run> runs) {
                        mRuns = runs;
                        mView.hideLoadingLayout();
                        if (mRuns.size() > 0){
                            mView.displayRuns(mRuns);
                        } else {
                            mView.displayNotFoundLayout();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "loadRuns: " + e.getMessage());
                        e.printStackTrace();
                    }
                }));
    }

    @Override
    public void destroy() {
        disposable.clear();
    }
}
