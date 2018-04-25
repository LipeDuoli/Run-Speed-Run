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

    public LatestRunPresenter(LatestRunContract.View view, RunsRepository runsRepository, Scheduler scheduler) {
        mView = view;
        mRunsRepository = runsRepository;
        mScheduler = scheduler;
    }

    @Override
    public void loadLatestRuns() {
        mView.showLoading();
        if (mRuns != null){
            Log.d(TAG, "loadLatestRuns: load saved " + toString());
            mView.displayRuns(mRuns);
        } else {
            Log.d(TAG, "loadLatestRuns: load net " + toString());
            loadRuns();
        }
    }

    private void loadRuns() {
        disposable.add(mRunsRepository.getLatestRuns()
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<List<Run>>() {
                    @Override
                    public void onSuccess(List<Run> runs) {
                        mRuns = runs;
                        mView.displayRuns(mRuns);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "loadLatestRuns: " + e.getMessage());
                        e.printStackTrace();
                    }
                }));
    }

    @Override
    public void destroy() {
        disposable.clear();
    }
}
