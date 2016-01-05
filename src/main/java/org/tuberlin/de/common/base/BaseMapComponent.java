package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractMapComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.MapComponent;

import java.util.Collection;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
//TODO
public class BaseMapComponent extends AbstractMapComponent implements MapComponent {

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
