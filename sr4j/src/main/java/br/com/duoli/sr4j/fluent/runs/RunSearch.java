package br.com.duoli.sr4j.fluent.runs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.runs.Run;
import br.com.duoli.sr4j.models.runs.RunStatusType;
import br.com.duoli.sr4j.services.RunService;
import br.com.duoli.sr4j.util.ErrorUtil;
import retrofit2.Response;

public class RunSearch implements IRunParams {

    private RunService service;
    private Map<String, String> queryParams;

    public RunSearch(RunService service) {
        this.service = service;
        this.queryParams = new HashMap<>();
    }

    @Override
    public PageableList<Run> fetch() {
        try {
            Response<PageableList<Run>> response = service.getAll(queryParams).execute();
            if (!response.isSuccessful()) {
                throw new SearchException(ErrorUtil.parseError(response).getMessage());
            }
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public IRunParamsId withId(String runId) {
        return new RunSearchId(service, runId);
    }

    @Override
    public IRunParams fromUser(String userId) {
        queryParams.put("user", userId);
        return this;
    }

    @Override
    public IRunParams fromGuest(String guestId) {
        queryParams.put("guest", guestId);
        return this;
    }

    @Override
    public IRunParams fromExaminer(String examinerId) {
        queryParams.put("examiner", examinerId);
        return this;
    }

    @Override
    public IRunParams ofGame(String gameId) {
        queryParams.put("game", gameId);
        return this;
    }

    @Override
    public IRunParams ofLevel(String levelId) {
        queryParams.put("level", levelId);
        return this;
    }

    @Override
    public IRunParams ofCategory(String categoryId) {
        queryParams.put("category", categoryId);
        return this;
    }

    @Override
    public IRunParams ofPlataform(String plataformId) {
        queryParams.put("platform", plataformId);
        return this;
    }

    @Override
    public IRunParams ofRegion(String regionId) {
        queryParams.put("region", regionId);
        return this;
    }

    @Override
    public IRunParams onEmulator() {
        queryParams.put("emulated", String.valueOf(true));
        return this;
    }

    @Override
    public IRunParams withStatus(RunStatusType status) {
        queryParams.put("status", status.toString());
        return this;
    }

}
