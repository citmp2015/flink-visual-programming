package org.tuberlin.de.common.model.interfaces;

import org.tuberlin.de.common.model.types.ComponentTypes;

import java.util.Collection;
import java.util.Map;

/**
 * The JobComponent interface is the interface every participating component has to implement. Here we are following the same structure as in the JobGraph.
 * A properties Map (Map<String, Object>) containing all the required Flink code fragments is supposed to be used by the CodeGenerator to generate the code.
 * Fuctions like <i>getImports()</i> supply a convenient access to frequently used code fragments.
 *
 *
 *
 * @author Daniel Schröder
 * @author Marvin Byfield
 *
 * @version 0.1
 *
 *
 */
public interface JobComponent {

    //TODO syncronization
    void init(JobGraph jobGraph, Map<String, Object> parameters) throws IllegalArgumentException;


    /**
     *
     * @return
     * @throws IllegalStateException
     */
    public String getJobSource() throws IllegalStateException;

    /**
     *
     * @return
     * @throws IllegalStateException
     */
    public String[] getJobImports() throws IllegalStateException;

    /**
     *
     * @return
     * @throws IllegalStateException
     */
    public boolean verify() throws IllegalStateException;

    /**
     *
     * @return
     * @throws IllegalStateException
     */
    public ComponentTypes getType() throws IllegalStateException;

    /**
     *
     * @param key
     * @return
     * @throws IllegalStateException
     */
    public Object getParameter(String key) throws IllegalStateException;

    public Collection<String> getParents() throws IllegalStateException;

    public Collection<String> getChildren() throws IllegalStateException;

    public String getComponentKey();

    public String getInputType();

    public String getOutputType();
}
