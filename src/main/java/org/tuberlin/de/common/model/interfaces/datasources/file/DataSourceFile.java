package org.tuberlin.de.common.model.interfaces.datasources.file;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;

/**
 * Created by Malcolm-X on 04.01.2016.
 */
public interface DataSourceFile extends DataSource {

    public static final String FILE_PATH = Constants.DATA_SOURCE_FILE_PATH;

    public String getFilePath();
}
