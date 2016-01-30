package org.tuberlin.de.common.model.abstracts.datasource.file;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasource.AbstractDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/4/16.
 */
public abstract class AbstractFileCsvDataSourceComponent extends AbstractDataSourceComponent implements DataSource {

    //TODO add CSV file properties

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        String result = jobGraph.getEnvironmentIdentifier() + ".readCsvFile(" + parameters.get(Constants.COMPONENT_PATH_JSON) + ")";
        result += ";";
        return result;
    }
}
