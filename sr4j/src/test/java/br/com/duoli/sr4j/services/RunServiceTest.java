package br.com.duoli.sr4j.services;

import org.junit.Test;

import java.util.List;

import br.com.duoli.sr4j.SpeedRun4jClient;
import br.com.duoli.sr4j.models.runs.Run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RunServiceTest {

    private String runId = "90y6pm7e";

    @Test
    public void testDescerializeAllRuns_noParameters() {
        List<Run> runs = SpeedRun4jClient.getRun().fetch().getData();

        assertNotNull(runs);
    }

    @Test
    public void testDescerializeSimgleRun_noParameters() {
        Run run = SpeedRun4jClient.getRun().withId(runId).fetch();

        assertNotNull(run);
        assertEquals(runId, run.getId());
    }

}