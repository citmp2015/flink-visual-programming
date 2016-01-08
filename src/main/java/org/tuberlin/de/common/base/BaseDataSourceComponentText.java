package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasource.file.AbstractFileDataSourceComponent;
import org.tuberlin.de.common.model.abstracts.datasource.file.AbstractFileTextDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.datasource.FileDataSourceComponent;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSourceComponentText extends AbstractFileTextDataSourceComponent implements DataSourceComponent, FileDataSourceComponent {

    public BaseDataSourceComponentText(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }

    @Override
    public String getJobSource() throws IllegalStateException {
        return null;
    }


    @Override
    public boolean verify() {
        //TODO
        return super.verify();
    }

}
