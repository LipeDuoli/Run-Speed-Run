package br.com.duoli.speedrunapp.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;

import br.com.duoli.speedrunapp.repository.GamesRepository;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GamesPresenterTest {

    private static final String ALL_GAMES = "";
    private static final int FIRST_PAGE = 0;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    GamesRepository gamesRepository;

    @Mock
    GamesContract.View view;

    GamesContract.Presenter presenter;
    PageableList<Game> emptyGameList = new PageableList<>();
    PageableList<Game> manyGameList = new PageableList<>(Arrays.asList(new Game(), new Game(), new Game()));;

    @Before
    public void setUp() {
        presenter = new GamesPresenter(gamesRepository, Schedulers.trampoline());
        presenter.setView(view);
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @After
    public void cleanUp() {
        RxJavaPlugins.reset();
    }

    @Test
    public void shouldPassRunsToView() {
        when(gamesRepository.getGames(ALL_GAMES, FIRST_PAGE)).thenReturn(Single.just(manyGameList));

        presenter.loadData(ALL_GAMES);

        verify(view).displayGames(manyGameList.getData());
    }

    @Test
    public void shouldDisplayLoadingViewWhenLoadRuns(){
        when(gamesRepository.getGames(ALL_GAMES, FIRST_PAGE)).thenReturn(Single.just(manyGameList));

        presenter.loadData(ALL_GAMES);

        verify(view).displayLoading();
    }

    @Test
    public void shouldDisplayNotFountLayoutWithEmptyResult(){
        when(gamesRepository.getGames(ALL_GAMES, FIRST_PAGE)).thenReturn(Single.just(emptyGameList));

        presenter.loadData(ALL_GAMES);

        verify(view).displayNotFound();
    }

    @Test
    public void shouldHandleError(){
        when(gamesRepository.getGames(ALL_GAMES, FIRST_PAGE)).thenReturn(Single.<PageableList<Game>>error(new Throwable()));

        presenter.loadData(ALL_GAMES);

        verify(view).displayError();
    }

}