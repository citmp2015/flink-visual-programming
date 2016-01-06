package org.tuberlin.de.common.model.abstracts.datasource.file;

import org.tuberlin.de.common.model.abstracts.datasource.AbstractDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.datasource.FileDataSourceComponent;

import java.util.Map;

/**
 * Created by oxid on 1/4/16.
 */
public abstract class AbstractFileDataSourceComponent extends AbstractDataSourceComponent implements FileDataSourceComponent, DataSourceComponent {



    @Override
    public boolean verify() {
        //TODO own verification
        return super.verify();
    }

    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);

    }

}
