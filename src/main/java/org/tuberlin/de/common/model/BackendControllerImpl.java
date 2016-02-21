package org.tuberlin.de.common.model;

import org.json.JSONObject;
import org.tuberlin.de.common.model.interfaces.BackendController;
import org.tuberlin.de.common.model.interfaces.JobGraph;

import java.util.Map;

public class BackendControllerImpl implements BackendController {

    private static BackendController instance;

    public static BackendController getInstance() {
        if (instance == null) {
            instance = new BackendControllerImpl();
        }
        return instance;
    }

    @Override
    public JobGraph getJobGraph(JSONObject json) throws Exception {
        //TODO: parser is currently dummy, no json
        return JSONParser.getJobGraph(json);
    }

    @Override
    public boolean validateJobGraph(JobGraph jobGraph) throws Exception {
        return false;
    }

    @Override
    public String getJobSource(JobGraph jobGraph) {
        return null;
    }

    @Override
    public Map<String, String> getComponentSources(JobGraph jobGraph) {
        return null;
    }

    @Override
    public boolean storeGraph(String key, JobGraph graph) {
        return false;
    }

    @Override
    public boolean storeGraph(String key, String json) {
        return false;
    }
}
