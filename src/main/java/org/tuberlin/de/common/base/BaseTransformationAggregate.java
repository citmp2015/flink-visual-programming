package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractTransformationAggregate;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationAggregate;

import java.util.Map;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
public class BaseTransformationAggregate extends AbstractTransformationAggregate implements TransformationAggregate {

    public BaseTransformationAggregate(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }

}
