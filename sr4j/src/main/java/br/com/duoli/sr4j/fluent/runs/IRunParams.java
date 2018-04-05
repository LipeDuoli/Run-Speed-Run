package br.com.duoli.sr4j.fluent.runs;

import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.runs.Run;

public interface IRunParams {

    PageableList<Run> fetch();
    IRunParamsId withId(String runId);
}
