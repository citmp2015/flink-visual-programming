package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractReduceComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.ReduceComponent;

import java.util.Collection;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
public class  BaseReduceComponent extends AbstractReduceComponent implements ReduceComponent {
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
