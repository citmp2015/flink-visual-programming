package org.tuberlin.de.common.model.abstracts;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.types.ComponentTypes;
import org.tuberlin.de.common.model.types.StateModelTypes;

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
    private StateModelTypes stateModel = StateModelTypes.UNDEFINED;
    protected JobGraph jobGraph;
    protected Collection<String> imports;
    protected Collection<String> children;
    protected Collection<String> parents;
    //TODO syncronization

    @Override
    public boolean init(JobGraph jobGraph, Map<String, Object> parameters) {
        if(this.isImmutable()) return false;

        if(parameters == null || jobGraph == null) throw new IllegalArgumentException("Arguments must not be null!");
        this.parameters = parameters;
        this.imports = new HashSet<String>();
        this.jobGraph = jobGraph;
        this.parents = new ArrayList<String>();
        this.children = new ArrayList<String>();
        //TODO integrity: other types
        if (!(parameters.get(JobComponent.PARENT) == null) && parameters.get(JobComponent.PARENT) instanceof ArrayList){
            parents = (Collection<String>) parameters.get(JobComponent.PARENT);
        }
        if (!(parameters.get(JobComponent.CHILD) == null) && parameters.get(JobComponent.CHILD) instanceof ArrayList)
            children = (Collection<String>) parameters.get(JobComponent.CHILD);

        return this.setStateModel(StateModelTypes.INITIALIZED);
    }

    /**
     * Tests whether the Object has already been verified. If so further changes should be prohibit.
     * @return Whether Object is mutable or not
     */
    protected boolean isImmutable(){
        return this.getStateModel() == StateModelTypes.VERIFIED ? true : false;
    }

    /**
     * Tests whether the Object has already been initialized.
     * @return Whether Object is initialized
     */
    protected boolean isInitialized(){
        return this.getStateModel() != StateModelTypes.UNDEFINED ? true : false;
    }

    /**
     * Set the stateModel if the Component is not already Immutable.
     * @param t
     */
    public boolean setStateModel(StateModelTypes t){
        if(!this.isImmutable()) {
            this.stateModel = t;
            return true;
        }

        return false;
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
//        if (!stateModel) throw new IllegalStateException("Invalid state: must be stateModel");
//        return (String) parameters.get(Constants.COMPONENT_SOURCE_JSON);
//    }

//    @Override
//    public String getJobSource() {
//        //TODO maybe catch cast exception
//        if (!stateModel) throw new IllegalStateException("Invalid state: must be stateModel");
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
        if (this.getStateModel() ==StateModelTypes.UNDEFINED) throw new IllegalStateException("Invalid state: Initialize before!");
        return (String[]) parameters.get(Constants.JOB_COMPONENT_IMPORTS_JSON);
    }

    @Override
    public boolean verify() {
        if (this.getStateModel() ==StateModelTypes.UNDEFINED) throw new IllegalStateException("Invalid state: Initialize before!");
        //TODO implement basic validation
        return true;
    }

    @Override
    public ComponentTypes getType() throws IllegalStateException {
        if (this.getStateModel() ==StateModelTypes.UNDEFINED) throw new IllegalStateException("Invalid state: Initialize before!");
        //TODO maybe catch cast exception
        return (ComponentTypes) parameters.get(Constants.COMPONENT_TYPE_JSON);
    }

    @Override
    public Object getParameter(String key) {
        if (this.getStateModel() ==StateModelTypes.UNDEFINED) throw new IllegalStateException("Invalid state: Initialize before!");
        return parameters.get(key);
    }

    @Override
    public String getTypeDeclaration() throws IllegalStateException {
        return "DataSet<" + parameters.get(JobComponent.OUTPUT_TYPE) + ">";
    }

    public StateModelTypes getStateModel() {
        return stateModel;
    }

}
