package br.com.duoli.sr4j.services;

import java.util.Map;

import br.com.duoli.sr4j.models.common.Envelope;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.runs.Run;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RunService {

    String RUN_PATH = "runs";
    String RUN_WITH_ID_PATH = RUN_PATH + "/{id}";

    @GET(RUN_PATH)
    Call<PageableList<Run>> getAll(@QueryMap Map<String, String> queryParams);

    @GET(RUN_WITH_ID_PATH)
    Call<Envelope<Run>> withId(@Path("id") String runId, @QueryMap Map<String, String> queryParams);

}
