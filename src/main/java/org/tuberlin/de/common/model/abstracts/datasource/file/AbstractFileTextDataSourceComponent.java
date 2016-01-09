package org.tuberlin.de.common.model.abstracts.datasource.file;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

/**
 * Created by oxid on 1/8/16.
 */
public class AbstractFileTextDataSourceComponent extends AbstractFileDataSourceComponent implements DataSourceFile, DataSource {

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        String result = jobGraph.getEnvironmentIdentifier() + ".readTextFile(" + parameters.get(Constants.COMPONENT_PATH_JSON) + ")";
        result += ";";
        return result;
    }

}

