package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasink.file.AbstractDataSinkFile;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;
import org.tuberlin.de.common.model.interfaces.datasink.file.DataSinkFile;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
//TODO
public class BaseDataSinkFileText extends AbstractDataSinkFile implements DataSink, DataSinkFile {
    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);
    }

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        //TODO check constant naming
        String result = ".writeAsText(" + parameters.get(Constants.COMPONENT_PATH_JSON) + ")";
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
    public Collection<? extends String> getImports() {
        //TODO implemented for testing
        return null;
    }


    @Override
    public String getFilePath() {
        //TODO integrity checks
        return (String) parameters.get(DataSourceFile.FILE_PATH);
    }
}
