package org.tuberlin.de.common.model.interfaces;

import java.util.Map;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public interface BackendController {
    public JobGraph getJobGraph(String json) throws Exception;

    public boolean validateJobGraph(JobGraph jobGraph) throws Exception;

    public String getJobSource(JobGraph jobGraph);

    public Map<String, String> getComponentSources(JobGraph jobGraph);

    public boolean storeGraph(String key, JobGraph graph);

    public boolean storeGraph(String key, String json);

    //TODO: package + deploy + ....


}