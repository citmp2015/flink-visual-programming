package org.tuberlin.de.common.model.abstracts;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.types.ComponentTypes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public abstract class AbstractJobComponent implements JobComponent {

    /*
     *
     *
     *
     */
    protected Map<String, Object> parameters;
    protected boolean initialized = false;
    protected JobGraph jobGraph;
    protected Collection<String> imports;
    //TODO syncronization

    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        if(parameters == null || jobGraph == null) throw new IllegalArgumentException("Arguments must not be null!");
        this.parameters = parameters;
        this.initialized = true;
        this.imports = new HashSet<String>();
    }

    @Override
    public Collection<String> getParents() throws IllegalStateException {
        return null;
    }

    @Override
    public Collection<String> getChildren() throws IllegalStateException {
        return null;
    }

//    @Override
//    public String getSource() {
//        if (!initialized) throw new IllegalStateException("Invalid state: must be initialized");
//        return (String) parameters.get(Constants.COMPONENT_SOURCE_JSON);
//    }

//    @Override
//    public String getJobSource() {
//        //TODO maybe catch cast exception
//        if (!initialized) throw new IllegalStateException("Invalid state: must be initialized");
//        return (String) parameters.get(Constants.COMPONENT_JOB_SOURCE_JSON);
//    }

    @Override
    public String getComponentKey() {
        return null;
    }

    @Override
    public String getInputType() {
        return null;
    }

    @Override
    public String getOutputType() {
        return null;
    }

    @Override
    public String[] getJobImports() {
        //TODO maybe catch cast exception
        if (!initialized) throw new IllegalStateException("Invalid state: must be initialized");
        return (String[]) parameters.get(Constants.JOB_COMPONENT_IMPORTS_JSON);
    }

    @Override
    public boolean verify() {
        if (!initialized) throw new IllegalStateException("Invalid state: must be initialized");
        //TODO implement basic validation
        return true;
    }

    @Override
    public ComponentTypes getType() throws IllegalStateException {
        if (!initialized) throw new IllegalStateException("Invalid state: must be initialized");
        //TODO maybe catch cast exception
        return (ComponentTypes) parameters.get(Constants.COMPONENT_TYPE_JSON);
    }

    @Override
    public Object getParameter(String key) {
        if (!initialized) throw new IllegalStateException("Invalid state: must be initialized");
        return parameters.get(key);
    }
}
