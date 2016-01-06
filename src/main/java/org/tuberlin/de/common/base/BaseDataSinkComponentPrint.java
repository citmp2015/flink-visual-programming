package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.datasink.file.AbstractFileDataSinkComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSinkComponent;
import org.tuberlin.de.common.model.interfaces.datasink.FileDataSinkComponent;
import org.tuberlin.de.common.model.interfaces.datasource.FileDataSourceComponent;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSinkComponentPrint extends AbstractFileDataSinkComponent implements DataSinkComponent, FileDataSinkComponent {
    public BaseDataSinkComponentPrint(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }

    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);
    }

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        //TODO check constant naming
        String result = ".print()";
        result += ";";
        return result;
    }



    @Override
    public boolean verify() {
        //TODO
        return super.verify();
    }

    @Override
    public Collection<String> getParents() throws IllegalStateException {
        return null;
    }

    @Override
    public Collection<String> getChildren() throws IllegalStateException {
        return null;
    }

    @Override
    public String getComponentKey() {
        return null;
    }

    @Override
    public String getInputType() {
        return null;
    }

    @Override
    public String getOutputType() {
        return null;
    }


    @Override
    public String getFilePath() {
        //TODO integrity checks
        return (String) parameters.get(FileDataSourceComponent.FILE_PATH_JSON);
    }
}
