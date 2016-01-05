package org.tuberlin.de.common.model;

import java.util.Map;

/**
 * Created by Malcolm-X on 29.12.2015.
 */
public class BaseTextDataSourceComponent extends AbstractDataSourceComponent {
    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);
    }

    @Override
    public String getSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        return (String) parameters.get(Constants.COMPONENT_SOURCE_JSON);
    }

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        return jobGraph.getEnvironmentIdentifier() + ".";

    }

    @Override
    public String[] getJobImports() {
        return null;
    }

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public ComponentTypes getType() {
        return null;
    }

    @Override
    public Object getParameter(String key) {
        return null;
    }
}
