package org.tuberlin.de.common.model.interfaces.datasources.file.read;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

/**
 * Created by oxid on 1/9/16.
 *
 * readSequenceFile(Key, Value, path) / SequenceFileInputFormat - Creates a JobConf and reads file from the specified path with type SequenceFileInputFormat,
 * Key class and Value class and returns them as Tuple2<Key, Value>.
 *
 */
public interface DataSourceFileReadSequenceFile extends DataSourceFile{

    //path from DatasourceFile
    public static final String KEY = Constants.DATA_SOURCE_FILE_READ_SEQUENCE_FILE_KEY;
    public static final String VALUE = Constants.DATA_SOURCE_FILE_READ_SEQUENCE_FILE_VALUE;

}
