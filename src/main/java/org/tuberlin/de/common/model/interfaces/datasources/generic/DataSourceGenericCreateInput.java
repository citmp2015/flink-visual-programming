package org.tuberlin.de.common.model.interfaces.datasources.generic;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * createInput(inputFormat) / InputFormat - Accepts a generic input format.
 *
 */
public interface DataSourceGenericCreateInput extends DataSource{

    public static final String INPUT_FORMAT = Constants.DATA_SOURCE_GENERIC_CREATE_INPUT_FORMAT;
    
}
