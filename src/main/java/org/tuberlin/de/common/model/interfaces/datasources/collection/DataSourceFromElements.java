package org.tuberlin.de.common.model.interfaces.datasources.collection;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * fromElements(T ...) - Creates a data set from the given sequence of objects. All objects must be of the same type.
 *
 */
public interface DataSourceFromElements extends DataSource{

    public static final String T = Constants.DATA_SOURCE_FROM_ELEMENTS_T;

}
