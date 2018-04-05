package br.com.duoli.sr4j.fluent.runs;

import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.runs.Run;

public interface IRunParamsId {

    Run fetch();

    IRunParamsId embedResource(Embed.Runs... resources);
}
