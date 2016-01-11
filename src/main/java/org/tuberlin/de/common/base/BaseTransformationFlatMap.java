package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractTransformationFlatMap;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationFlatMap;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
//TODO
public class BaseTransformationFlatMap extends AbstractTransformationFlatMap implements TransformationFlatMap {
    public BaseTransformationFlatMap(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }


    @Override
    public Collection<? extends String> getImports() {
        //TODO implemented for testing
        return null;
    }
}
