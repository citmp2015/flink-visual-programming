package org.tuberlin.de.common.model.interfaces.datasources.collection;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * fromCollection(Collection) - Creates a data set from the Java Java.util.Collection. All elements in the collection must be of the same type.
 *
 */
public interface DataSourceFromCollection extends DataSource{

    public static final String COLLECTION = Constants.DATA_SOURCE_FROM_COLLECTION_COLLECTION;

}
