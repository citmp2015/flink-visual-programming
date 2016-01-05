package org.tuberlin.de.common.model;

import java.util.Map;

/**
 * Created by Malcolm-X on 29.12.2015.
 */
public interface CUJobComponent implements JobComponent {

    public void init(JobGraph jobGraph, Map<String, Object> parameters) throws IllegalArgumentException {

    }

    public String getSource() throws IllegalStateException {
        return null;
    }

    public String getJobSource() throws IllegalStateException {
        return null;
    }

    public String[] getJobImports() throws IllegalStateException {
        return new String[0];
    }

    public boolean verify() throws IllegalStateException {
        return false;
    }

    public ComponentTypes getType() throws IllegalStateException {
        return null;
    }

    public Object getParameter(String key) throws IllegalStateException {
        return null;
    }
}
