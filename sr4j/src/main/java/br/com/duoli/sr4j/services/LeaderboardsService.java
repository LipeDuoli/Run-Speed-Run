package br.com.duoli.sr4j.services;

import java.util.Map;

import br.com.duoli.sr4j.models.common.Envelope;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface LeaderboardsService {

    String LEADERBOARDS_PATH = "leaderboards/{game}/category/{category}";
    String LEADERBOARDS_LEVEL_PATH = "leaderboards/{game}/level/{level}/{category}";

    @GET(LEADERBOARDS_PATH)
    Call<Envelope<Leaderboard>> getLeaderBoards(@Path("game") String gameId,
                                                @Path("category") String categoryId,
                                                @QueryMap Map<String, String> queryParams);

    @GET(LEADERBOARDS_LEVEL_PATH)
    Call<Envelope<Leaderboard>> getLevelLeaderBoards(@Path("game") String gameId,
                                                     @Path("category") String categoryId,
                                                     @Path("level") String levelId,
                                                     @QueryMap Map<String, String> queryParams);
}
