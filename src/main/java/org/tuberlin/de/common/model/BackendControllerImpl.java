package org.tuberlin.de.common.model;

import org.tuberlin.de.common.model.interfaces.BackendController;
import org.tuberlin.de.common.model.interfaces.JobGraph;

import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public abstract class BackendControllerImpl implements BackendController {
    @Override
    public JobGraph getJobGraph(String json) throws Exception {
        //TODO: parser is currently dummy, no json

        return null;
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
}
