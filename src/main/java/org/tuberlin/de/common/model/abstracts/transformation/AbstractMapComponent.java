package org.tuberlin.de.common.model.abstracts.transformation;

import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.MapComponent;
import org.tuberlin.de.common.model.types.ComponentTypes;

import java.util.Map;

/**
 * Created by oxid on 1/4/16.
 */
public abstract class AbstractMapComponent implements MapComponent {
    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) throws IllegalArgumentException {

    }

    @Override
    public String getSource() throws IllegalStateException {
        return null;
    }

    @Override
    public String getJobSource() throws IllegalStateException {
        return null;
    }

    @Override
    public String[] getJobImports() throws IllegalStateException {
        return new String[0];
    }

    @Override
    public boolean verify() throws IllegalStateException {
        return false;
    }

    @Override
    public ComponentTypes getType() throws IllegalStateException {
        return null;
    }

    @Override
    public Object getParameter(String key) throws IllegalStateException {
        return null;
    }
}
