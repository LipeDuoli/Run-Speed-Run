package br.com.duoli.sr4j.fluent.runs;

import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.fluent.common.OrderBy;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.runs.Run;
import br.com.duoli.sr4j.models.runs.RunStatusType;

public interface IRunParams {

    PageableList<Run> fetch();

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

    IRunParams embedResource(Embed.Runs... resources);

    IRunParams orderBy(OrderBy.Runs orderBy);

    IRunParams asc();

    IRunParams desc();
}
