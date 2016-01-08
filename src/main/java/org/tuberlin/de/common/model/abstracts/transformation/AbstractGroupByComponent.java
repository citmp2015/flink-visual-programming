package org.tuberlin.de.common.model.abstracts.transformation;

import org.tuberlin.de.common.model.abstracts.AbstractJobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.GroupByComponent;
import org.tuberlin.de.common.model.types.ComponentTypes;

import java.util.Map;

/**
 * Created by oxid on 1/4/16.
 */
public abstract class AbstractGroupByComponent extends AbstractTransformationComponent implements GroupByComponent{

    @Override
    public String getJobSource() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String source = "";
        source += ".groupBy(" + parameters.get(COMPONENT_GROUP_BY_FIELD) + ")";
        return source;
    }

}
