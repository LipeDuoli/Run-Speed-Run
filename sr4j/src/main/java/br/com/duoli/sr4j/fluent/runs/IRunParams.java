package br.com.duoli.sr4j.fluent.runs;

import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.runs.Run;
import br.com.duoli.sr4j.models.runs.RunStatusType;

public interface IRunParams {

    PageableList<Run> fetch();
    IRunParamsId withId(String runId);
    IRunParams fromUser(String userId);
    IRunParams fromGuest(String guestId);
    IRunParams fromExaminer(String examinerId);
    IRunParams ofGame(String gameId);
    IRunParams ofLevel(String levelId);
    IRunParams ofCategory(String categoryId);
    IRunParams ofPlataform(String plataformId);
    IRunParams ofRegion(String regionId);
    IRunParams onEmulator();
    IRunParams withStatus(RunStatusType status);
}
