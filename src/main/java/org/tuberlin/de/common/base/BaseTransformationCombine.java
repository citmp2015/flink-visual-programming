package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractTransformationCombine;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationCombine;

import java.util.Collection;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
//TODO
public class BaseTransformationCombine extends AbstractTransformationCombine implements TransformationCombine {



    //TODO implement in higher levels ... prevent errors
    @Override
    public String getSource() throws IllegalStateException {return null;}
    @Override
    public String getJobSource() throws IllegalStateException {return null;}

    @Override
    public Collection<? extends String> getImports() {
        //TODO implemented for testing
        return null;
    }
}
