package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractGroupBy;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationGroupBy;

import java.util.Map;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
//TODO
public class BaseGroupBy extends AbstractGroupBy implements TransformationGroupBy {
    public BaseGroupBy(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }
}
