package org.tuberlin.de.common.model.abstracts.datasource.file;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasource.AbstractDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.datasource.FileDataSourceComponent;

import java.util.Collection;

/**
 * Created by oxid on 1/8/16.
 */
public class AbstractFileTextDataSourceComponent extends AbstractFileDataSourceComponent implements FileDataSourceComponent, DataSourceComponent {


    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        String result = jobGraph.getEnvironmentIdentifier() + ".readTextFile(" + parameters.get(Constants.COMPONENT_PATH_JSON) + ")";
        result += ";";
        return result;
    }

}
