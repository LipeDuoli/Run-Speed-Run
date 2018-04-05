package br.com.duoli.sr4j.fluent.runs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.common.Envelope;
import br.com.duoli.sr4j.models.runs.Run;
import br.com.duoli.sr4j.services.RunService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

public class RunSearchId implements IRunParamsId {

    private RunService service;
    private String runId;
    private Map<String, String> queryParams;

    public RunSearchId(RunService service, String runId) {
        this.service = service;
        this.runId = runId;
        this.queryParams = new HashMap<>();
    }

    @Override
    public Run fetch() {
        try {
            Response<Envelope<Run>> response = service.withId(runId, queryParams).execute();
            if (!response.isSuccessful()) {
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public IRunParamsId embedResource(Embed.Runs... resources) {
        if (resources.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (Embed.Runs r : resources) {
                builder.append(r.toString()).append(",");
            }
            queryParams.put("embed", builder.toString());
        }
        return this;
    }
}
