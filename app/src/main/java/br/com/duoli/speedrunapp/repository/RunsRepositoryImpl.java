package br.com.duoli.speedrunapp.repository;

import java.util.List;
import java.util.concurrent.Callable;

import br.com.duoli.sr4j.SpeedRun4jClient;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.fluent.common.OrderBy;
import br.com.duoli.sr4j.fluent.runs.RunSearch;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.runs.Run;
import br.com.duoli.sr4j.models.runs.RunStatusType;
import io.reactivex.Single;

public class RunsRepositoryImpl implements RunsRepository {

    private final RunSearch runSearch;

    public RunsRepositoryImpl() {
        runSearch = SpeedRun4jClient.getRun();
    }

    @Override
    public Single<List<Run>> getLatestRuns() {
        return Single.fromCallable(new Callable<List<Run>>() {
            @Override
            public List<Run> call() {
                PageableList<Run> pageableList = runSearch.withStatus(RunStatusType.VERIFIED)
                        .orderBy(OrderBy.Runs.SUBMITED)
                        .embedResource(Embed.Runs.GAME, Embed.Runs.CATEGORY, Embed.Runs.PLAYERS)
                        .desc()
                        .fetch();
                return pageableList.getData();
            }
        });
    }
}
