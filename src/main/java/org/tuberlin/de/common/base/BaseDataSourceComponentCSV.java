package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasource.file.AbstractFileDataSourceComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSourceComponentCSV extends AbstractFileDataSourceComponent implements DataSource, DataSourceFile {
    @Override
    public boolean init(JobGraph jobGraph, Map<String, Object> parameters) {
        super.init(jobGraph, parameters);
        return true;
    }

    @Override
    public String getJobSource() {
        if (!isInitialized()) throw new IllegalStateException("Must be stateModel");
        //TODO: Integrity checks
        String result = jobGraph.getEnvironmentIdentifier() + ".readCsvFile(" + parameters.get(Constants.COMPONENT_PATH_JSON) + ")";
        //TODO do not include constants file
        if(parameters.containsKey(Constants.CSV_INCLUDE_FIELDS)){
            //TODO integrity (Integer, number of values,...)
            result += ".includeFields(" + parameters.get(Constants.CSV_INCLUDE_FIELDS)+")";
        }
        //TODO integrity
        if (parameters.containsKey(Constants.CSV_FIELD_TYPES)){
            result += ".types(";
            //TODO integrity
            String[] types = ((String[])parameters.get(Constants.CSV_FIELD_TYPES));
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
        return (String) parameters.get(DataSourceFile.FILE_PATH);
    }
    @Override
    public Collection<? extends String> getImports() {
        //TODO implemented for testing
        return null;
    }
}
