package org.tuberlin.de.common.model.interfaces.datasources.file.read;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

/**
 * Created by oxid on 1/9/16.
 *
 * readFileOfPrimitives(path, Class) / PrimitiveInputFormat - Parses files of new-line (or another char sequence) delimited primitive data types such as String or Integer.
 *
 */
public interface DataSourceFileReadFileOfPrimitives extends DataSourceFile{

    //path from DatasourceFile

    public static final String CLASS = Constants.DATA_SOURCE_FILE_READ_FILE_OF_PRIMITIVES_CLASS_NAME;

}
