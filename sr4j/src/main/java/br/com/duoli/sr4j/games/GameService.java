package br.com.duoli.sr4j.games;

import java.util.Map;

import br.com.duoli.sr4j.categories.Category;
import br.com.duoli.sr4j.common.EnvelopeList;
import br.com.duoli.sr4j.common.PageableList;
import br.com.duoli.sr4j.leaderboards.Leaderboard;
import br.com.duoli.sr4j.levels.Level;
import br.com.duoli.sr4j.variables.Variable;
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
    Call<Game> withId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/categories")
    Call<EnvelopeList<Category>> categoriesForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/levels")
    Call<EnvelopeList<Level>> levelsForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/variables")
    Call<EnvelopeList<Variable>> variablesForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/derived-games")
    Call<PageableList<Game>> derivedGamesForId(@Path("id") String gameId);

    @GET(GAME_WITH_ID_PATH + "/records")
    Call<PageableList<Leaderboard>> recordsForId(@Path("id") String gameId);

}
