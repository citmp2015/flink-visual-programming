package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.datasource.file.AbstractFileTextDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSourceComponentText extends AbstractFileTextDataSourceComponent implements DataSource, DataSourceFile {

    public BaseDataSourceComponentText(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }

    @Override
    public Collection<? extends String> getImports() {
        //TODO implemented for testing
        return null;
    }

}
