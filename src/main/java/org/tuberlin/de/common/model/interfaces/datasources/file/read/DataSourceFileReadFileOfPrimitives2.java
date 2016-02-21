package org.tuberlin.de.common.model.interfaces.datasources.file.read;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

/**
 * Created by oxid on 1/9/16.
 *
 * readFileOfPrimitives(path, delimiter, Class) / PrimitiveInputFormat - Parses files of new-line (or another char sequence)
 * delimited primitive data types such as String or Integer using the given delimiter.
 *
 */
public interface DataSourceFileReadFileOfPrimitives2 extends DataSourceFileReadFileOfPrimitives{

    //path from DatasourceFile
    //class from DataSourceFileReadFileOfPrimitives

    public static final String DELIMITER = Constants.DATA_SOURCE_FILE_READ_FILE_OF_PRIMITIVES2_DELIMITER;

}
