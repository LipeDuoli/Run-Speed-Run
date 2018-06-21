package br.com.duoli.speedrunapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.Collections;
import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.repository.RunsRepository;
import br.com.duoli.speedrunapp.repository.RunsRepositoryImpl;
import br.com.duoli.speedrunapp.ui.detail.DetailActivity;
import br.com.duoli.sr4j.models.runs.Run;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class ListWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewFactory(getApplicationContext());
    }

    class ListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private CompositeDisposable mDisposable = new CompositeDisposable();
        private final RunsRepository mRepository;
        private List<Run> mRunsList;
        private Context mContext;

        ListRemoteViewFactory(Context context) {
            mContext = context;
            mRepository = new RunsRepositoryImpl();
            mRunsList = Collections.emptyList();
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            fetchRuns();
        }

        private void fetchRuns() {
            mDisposable.add(mRepository.getLatestRuns().subscribeWith(new DisposableSingleObserver<List<Run>>() {

                @Override
                public void onSuccess(List<Run> runs) {
                    mRunsList = runs;
                }

                @Override
                public void onError(Throwable e) {

                }
            }));
        }

        @Override
        public void onDestroy() {
            mDisposable.clear();
        }

        @Override
        public int getCount() {
            return mRunsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            Run currentRun = mRunsList.get(position);

            CharSequence listText = mContext.getString(R.string.widget_text,
                    currentRun.getGame().getNames().getInternational(),
                    currentRun.getCategory().getName());

            RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.widget_latest_run_item);
            view.setTextViewText(R.id.tv_latest_run_text, listText);

            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(DetailActivity.GAME_ID, currentRun.getGame().getId());
            fillInIntent.putExtra(DetailActivity.CATEGORY_ID, currentRun.getCategory().getId());
            view.setOnClickFillInIntent(R.id.tv_latest_run_text, fillInIntent);

            return view;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
