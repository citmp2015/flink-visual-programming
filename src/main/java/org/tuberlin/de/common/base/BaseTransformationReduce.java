package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractTransformationReduce;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationReduce;

import java.util.Collection;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
public class BaseTransformationReduce extends AbstractTransformationReduce implements TransformationReduce {
    @Override
    public String getJobSource() throws IllegalStateException {
        return null;
    }

    //TODO have to be ipmlemented in the higher levels (just here to prevent errors
    @Override
    public Collection<String> getParents() throws IllegalStateException {return null;}
    @Override
    public Collection<String> getChildren() throws IllegalStateException {return null;}
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
    public String getSource() throws IllegalStateException {return null;}
}
