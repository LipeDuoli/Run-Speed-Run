package br.com.duoli.speedrunapp.repository;

import java.util.List;

import br.com.duoli.sr4j.models.runs.Run;
import io.reactivex.Single;

public interface RunsRepository {

    Single<List<Run>> getLatestRuns();
}
