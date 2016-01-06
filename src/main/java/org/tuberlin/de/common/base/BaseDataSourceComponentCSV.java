package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasource.file.AbstractFileDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.datasource.FileDataSourceComponent;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSourceComponentCSV extends AbstractFileDataSourceComponent implements DataSourceComponent, FileDataSourceComponent {
    @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);
    }

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        String result = jobGraph.getEnvironmentIdentifier() + ".readCsvFile(" + parameters.get(Constants.COMPONENT_PATH_JSON) + ")";
        if(parameters.containsKey(FileDataSourceComponent.CSV_INCLUDE_FIELDS)){
            //TODO integrity (Integer, number of values,...)
            result += ".includeFields(" + parameters.get(FileDataSourceComponent.CSV_INCLUDE_FIELDS)+")";
        }
        //TODO integrity
        if (parameters.containsKey(FileDataSourceComponent.CSV_FIELD_TYPES)){
            result += ".types(";
            //TODO integrity
            String[] types = ((String[])parameters.get(FileDataSourceComponent.CSV_FIELD_TYPES));
            for (int i = 0; i < types.length; i++){
                result += (i == types.length - 1) ? (types[i] + ".class") : (types[i] + ".class, ");
            }
            result += ")";
        }
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
