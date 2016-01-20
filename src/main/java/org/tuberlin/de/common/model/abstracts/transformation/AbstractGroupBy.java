package org.tuberlin.de.common.model.abstracts.transformation;

import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationGroupBy;

/**
 * Created by oxid on 1/4/16.
 */
public abstract class AbstractGroupBy extends AbstractTransformation implements TransformationGroupBy {

    @Override
    public String getJobSource() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String source = "";
        source += ".groupBy(" + parameters.get(COMPONENT_GROUP_BY_FIELD) + ")";
        return source;
    }
    @Override
    public String getTypeDeclaration() throws IllegalStateException {
        return "UnsortedGrouping<" + parameters.get(JobComponent.OUTPUT_TYPE) + ">";
    }

}
