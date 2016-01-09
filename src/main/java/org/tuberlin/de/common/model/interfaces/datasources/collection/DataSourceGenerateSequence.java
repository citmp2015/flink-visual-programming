package org.tuberlin.de.common.model.interfaces.datasources.collection;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * generateSequence(from, to) - Generates the sequence of numbers in the given interval, in parallel.
 *
 */
public interface DataSourceGenerateSequence extends DataSource{

    public static final String TO = Constants.DATA_SOURCE_GENERATE_SEQUENCE_TO;
    public static final String FROM = Constants.DATA_SOURCE_GENERATE_SEQUENCE_FROM;

}
