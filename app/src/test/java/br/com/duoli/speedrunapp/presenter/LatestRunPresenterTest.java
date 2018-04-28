package br.com.duoli.speedrunapp.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.duoli.speedrunapp.repository.RunsRepository;
import br.com.duoli.sr4j.models.runs.Run;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LatestRunPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    RunsRepository runsRepository;

    @Mock
    LatestRunContract.View view;

    LatestRunContract.Presenter presenter;
    List<Run> runList = Arrays.asList(new Run(), new Run(), new Run());

    @Before
    public void setUp() {
        presenter = new LatestRunPresenter(runsRepository, Schedulers.trampoline());
        presenter.setView(view);
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @After
    public void cleanUp(){
        RxJavaPlugins.reset();
    }

    @Test
    public void shouldPassRunsToView() {
        when(runsRepository.getLatestRuns()).thenReturn(Single.just(runList));

        presenter.loadData();

        verify(view).displayRuns(runList);
    }

    @Test
    public void shouldDisplayLoadingViewWhenLoadRuns(){
        when(runsRepository.getLatestRuns()).thenReturn(Single.just(runList));

        presenter.loadData();

        verify(view).displayLoading();
    }

    @Test
    public void shouldDisplayNotFountLayoutWithEmptyResult(){
        when(runsRepository.getLatestRuns()).thenReturn(Single.just(Collections.<Run>emptyList()));

        presenter.loadData();

        verify(view).displayNotFound();
    }

    @Test
    public void shouldHandleError(){
        when(runsRepository.getLatestRuns()).thenReturn(Single.<List<Run>>error(new Throwable()));

        presenter.loadData();

        verify(view).displayError();
    }

}