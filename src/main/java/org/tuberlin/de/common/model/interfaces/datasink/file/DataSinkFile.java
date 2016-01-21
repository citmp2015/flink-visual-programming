package org.tuberlin.de.common.model.interfaces.datasink.file;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;

/**
 * Created by Malcolm-X on 04.01.2016.
 */
public interface DataSinkFile extends DataSink {

    public static final String FILE_PATH = Constants.DATA_SINK_FILE_PATH;

    String getFilePath();
}
