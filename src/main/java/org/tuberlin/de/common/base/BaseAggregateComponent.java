package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractAggregateComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.AggregateComponent;
import org.tuberlin.de.common.model.types.ComponentTypes;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
public class BaseAggregateComponent extends AbstractAggregateComponent implements AggregateComponent {

    public BaseAggregateComponent(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }
    @Override
    public ComponentTypes getType() throws IllegalStateException {
        return null;
    }

    @Override
    public Object getParameter(String key) throws IllegalStateException {
        return null;
    }

    @Override
    public Collection<String> getParents() throws IllegalStateException {
        return null;
    }

    @Override
    public Collection<String> getChildren() throws IllegalStateException {
        return null;
    }

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
}
