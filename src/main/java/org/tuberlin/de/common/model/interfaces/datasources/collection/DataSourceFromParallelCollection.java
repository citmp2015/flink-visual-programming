package org.tuberlin.de.common.model.interfaces.datasources.collection;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * fromParallelCollection(SplittableIterator, Class) - Creates a data set from an iterator, in parallel.
 * The class specifies the data type of the elements returned by the iterator.
 *
 */
public interface DataSourceFromParallelCollection extends DataSource{

    public static final String SPLITABLE_ITERATOR = Constants.DATA_SOURCE_FROM_PARALLEL_COLLECTION_SPLIT_ITERATOR;

    public static final String CLASS = Constants.DATA_SOURCE_FROM_PARALLEL_COLLECTION_CLASS;


}
