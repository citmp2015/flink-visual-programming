package org.tuberlin.de.common.model.abstracts.datasource.file;

import org.tuberlin.de.common.model.abstracts.datasource.AbstractDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

import java.util.Map;

/**
 * Created by oxid on 1/4/16.
 */
public abstract class AbstractFileDataSourceComponent extends AbstractDataSourceComponent implements DataSourceFile, DataSource {



    @Override
    public boolean verify() {
        //TODO own verification
        return super.verify();
    }

    @Override
    public String getFilePath() {
        //TODO integrity checks
        return (String) parameters.get(DataSourceFile.FILE_PATH);
    }


    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);

    }

}
