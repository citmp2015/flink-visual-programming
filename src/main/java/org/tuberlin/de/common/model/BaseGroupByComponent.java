package org.tuberlin.de.common.model;

import java.util.Map;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
public class BaseGroupByComponent implements GroupByComponent, JobComponent {
    @Override
    public void init(Map<String, Object> parameters) {

    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public String getJobSource() {
        return null;
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
