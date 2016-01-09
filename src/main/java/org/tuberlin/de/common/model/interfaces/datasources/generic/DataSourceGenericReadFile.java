package org.tuberlin.de.common.model.interfaces.datasources.generic;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by oxid on 1/9/16.
 *
 * readFile(inputFormat, path) / FileInputFormat - Accepts a file input format.
 *
 */
public interface DataSourceGenericReadFile extends DataSource{

    public static final String IMPUT_FILE = Constants.DATA_SOURCE_GENERIC_READ_FILE_INPUT_FORMAT;

    public  static  final  String PATH = Constants.DATA_SOURCE_GENERIC_READ_FILE_PATH;

}
