package br.com.duoli.sr4j;

import java.util.concurrent.TimeUnit;

import br.com.duoli.sr4j.fluent.game.GameSearch;
import br.com.duoli.sr4j.fluent.leaderboards.LeaderBoardsSearch;
import br.com.duoli.sr4j.fluent.runs.RunSearch;
import br.com.duoli.sr4j.services.GameService;
import br.com.duoli.sr4j.services.LeaderboardsService;
import br.com.duoli.sr4j.services.RunService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeedRun4jClient {

    private static final String BASE_URL = "https://www.speedrun.com/api/v1/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create());

    private static <S> S createService(Class<S> clazz) {
        return builder.build().create(clazz);
    }

    public static GameSearch getGame() {
        return new GameSearch(createService(GameService.class));
    }

    public static LeaderBoardsSearch getLeaderbard(){
        return new LeaderBoardsSearch(createService(LeaderboardsService.class));
    }

    public static RunSearch getRun(){
        return new RunSearch(createService(RunService.class));
    }
}
