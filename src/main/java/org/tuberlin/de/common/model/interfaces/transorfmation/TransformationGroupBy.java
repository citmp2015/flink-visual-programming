package org.tuberlin.de.common.model.interfaces.transorfmation;

import org.tuberlin.de.common.model.Constants;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public interface TransformationGroupBy extends Transformation {



    public static final String TYPE = Constants.TYPE_TRANSFORMATION_GROUP_BY;
    /**
     *  .groupBy(0) the 0
     */
    public static final String COMPONENT_GROUP_BY_FIELD = Constants.TUPLE_INDEX;

}
