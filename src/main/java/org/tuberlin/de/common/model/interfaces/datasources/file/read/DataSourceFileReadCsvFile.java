package org.tuberlin.de.common.model.interfaces.datasources.file.read;

import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;

/**
 * Created by oxid on 1/9/16.
 *
 * readCsvFile(path) / CsvInputFormat - Parses files of comma (or another char) delimited fields.
 * Returns a DataSet of tuples or POJOs. Supports the basic java types and their Value counterparts as field types.
 *
 */
public interface DataSourceFileReadCsvFile extends DataSourceFile{
}
