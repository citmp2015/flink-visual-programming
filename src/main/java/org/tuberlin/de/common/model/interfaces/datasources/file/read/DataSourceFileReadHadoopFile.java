package org.tuberlin.de.common.model.interfaces.datasources.file.read;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

/**
 * Created by oxid on 1/9/16.
 *
 *
 * readHadoopFile(FileInputFormat, Key, Value, path) / FileInputFormat - Creates a JobConf and reads file from the specified path with the specified FileInputFormat,
 * Key class and Value class and returns them as Tuple2<Key, Value>.
 *
 *
 */
public interface DataSourceFileReadHadoopFile extends DataSourceFile{

    //path from DatasourceFile
    public static final String FILE_INPUT_FORMAT = Constants.DATA_SOURCE_FILE_READ_HADOOP_FILE_FILE_INPUT_FORMAT;
    public static final String KEY = Constants.DATA_SOURCE_FILE_READ_HADOOP_KEY;
    public static final String VALUE = Constants.DATA_SOURCE_FILE_READ_HADOOP_VALUE;

}
