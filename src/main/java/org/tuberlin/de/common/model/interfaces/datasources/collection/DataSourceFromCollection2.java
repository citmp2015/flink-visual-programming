package org.tuberlin.de.common.model.interfaces.datasources.collection;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * fromCollection(Iterator, Class) - Creates a data set from an iterator. The class specifies the data type of the elements returned by the iterator.
 *
 */
public interface DataSourceFromCollection2 extends DataSource{

    public static final String ITERATOR = Constants.DATA_SOURCE_FROM_COLLECTION2_ITERATOR;
    public static final String CLASS = Constants.DATA_SOURCE_FROM_COLLECTION2_CLASS;

}
