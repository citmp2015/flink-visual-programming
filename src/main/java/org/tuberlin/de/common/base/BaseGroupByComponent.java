package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractGroupByComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.GroupByComponent;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
//TODO
public class BaseGroupByComponent extends AbstractGroupByComponent implements GroupByComponent {
    public BaseGroupByComponent(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }
}
