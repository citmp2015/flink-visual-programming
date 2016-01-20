package org.tuberlin.de.common.model.abstracts;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.types.ComponentTypes;

import java.util.ArrayList;
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
    protected Collection<String> children;
    protected Collection<String> parents;
    //TODO syncronization

    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        if(parameters == null || jobGraph == null) throw new IllegalArgumentException("Arguments must not be null!");
        this.parameters = parameters;
        this.initialized = true;
        this.imports = new HashSet<String>();
        this.jobGraph = jobGraph;
        this.parents = new ArrayList<String>();
        this.children = new ArrayList<String>();
        //TODO integrity: other types
        if (!(parameters.get(JobComponent.PARENT) == null) && parameters.get(JobComponent.PARENT) instanceof String){
            parents.add((String) parameters.get(JobComponent.PARENT));
        }
        if (!(parameters.get(JobComponent.CHILD) == null) && parameters.get(JobComponent.CHILD) instanceof String)
        children.add((String) parameters.get(JobComponent.CHILD));
    }

    @Override
    public Collection<String> getParents() throws IllegalStateException {

        return parents;
    }

    @Override
    public Collection<String> getChildren() throws IllegalStateException {
        return children;
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
        return (String) parameters.get(JobComponent.COMPONENT_KEY);
    }

    @Override
    public String getInputType() {
        return (String) parameters.get(JobComponent.INPUT_TYPE);
    }

    @Override
    public String getOutputType() {
        return (String) parameters.get(JobComponent.OUTPUT_TYPE);
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

    @Override
    public String getTypeDeclaration() throws IllegalStateException {
        return "DataSet<" + parameters.get(JobComponent.OUTPUT_TYPE) + ">";
    }
}
