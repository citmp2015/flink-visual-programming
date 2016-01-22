package org.tuberlin.de.common.model.interfaces.datasink.file.write;

import org.tuberlin.de.common.model.interfaces.datasink.file.DataSinkFile;

/**
 * Created by oxid on 1/9/16.
 *
 * writeAsCsv(...) / CsvOutputFormat - Writes tuples as comma-separated value files. Row and field delimiters are configurable.
 * The value for each field comes from the toString() method of the objects.
 *
 */
public interface DataSinkFileWriteAsCsv extends DataSinkFile{

    //path from DataSinkFile
    //TODO values.writeAsCsv("file:///path/to/the/result/file", "\n", "|"); the additional parameters

}
