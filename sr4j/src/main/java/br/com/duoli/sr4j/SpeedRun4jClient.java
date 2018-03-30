package br.com.duoli.sr4j;

import br.com.duoli.sr4j.fluent.game.GameSearch;
import br.com.duoli.sr4j.games.GameService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeedRun4jClient {

    private static final String BASE_URL = "https://www.speedrun.com/api/v1/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static <S> S createService(Class<S> clazz) {
        return builder.build().create(clazz);
    }

    public static GameSearch getGame() {
        return new GameSearch(createService(GameService.class));
    }
}
