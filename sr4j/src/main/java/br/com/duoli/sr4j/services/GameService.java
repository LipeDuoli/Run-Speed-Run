package br.com.duoli.sr4j.services;

import java.util.Map;

import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.common.Envelope;
import br.com.duoli.sr4j.models.common.EnvelopeList;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.models.levels.Level;
import br.com.duoli.sr4j.models.variables.Variable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GameService {

    String GAME_PATH = "games";
    String GAME_WITH_ID_PATH = GAME_PATH + "/{id}";

    @GET(GAME_PATH)
    Call<PageableList<Game>> getAll(@QueryMap Map<String, String> queryParams);

    @GET(GAME_WITH_ID_PATH)
    Call<Envelope<Game>> withId(@Path("id") String gameId, @QueryMap Map<String, String> queryParams);

    @GET(GAME_WITH_ID_PATH + "/categories")
    Call<EnvelopeList<Category>> categoriesForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/levels")
    Call<EnvelopeList<Level>> levelsForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/variables")
    Call<EnvelopeList<Variable>> variablesForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/derived-games")
    Call<PageableList<Game>> derivedGamesForId(@Path("id") String gameId, @QueryMap Map<String, String> queryParams);

    @GET(GAME_WITH_ID_PATH + "/records")
    Call<PageableList<Leaderboard>> recordsForId(@Path("id") String gameId, @QueryMap Map<String, String> queryParams);

}
