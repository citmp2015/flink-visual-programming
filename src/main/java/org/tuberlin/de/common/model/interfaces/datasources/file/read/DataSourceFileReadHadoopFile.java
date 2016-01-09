package org.tuberlin.de.common.model.interfaces.datasources.file.read;

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

}
