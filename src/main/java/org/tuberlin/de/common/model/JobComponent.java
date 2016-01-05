package org.tuberlin.de.common.model;

import java.util.Map;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public interface JobComponent {
    //TODO: elaborate wether Exceptions are needed

    //TODO syncronization
    void init(JobGraph jobGraph, Map<String, Object> parameters) throws IllegalArgumentException;

    public String getSource() throws IllegalStateException;
    public String getJobSource() throws IllegalStateException;
    public String[] getJobImports() throws IllegalStateException;
    public boolean verify() throws IllegalStateException;

    public ComponentTypes getType() throws IllegalStateException;
    public Object getParameter(String key) throws IllegalStateException;
}
