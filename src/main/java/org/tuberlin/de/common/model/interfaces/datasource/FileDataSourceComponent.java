package org.tuberlin.de.common.model.interfaces.datasource;

/**
 * Created by Malcolm-X on 04.01.2016.
 */
public interface FileDataSourceComponent extends DataSourceComponent {
    public static final String FILE_PATH_JSON = "HDFS_PATH";
    public static final String CSV_INCLUDE_FIELDS = "CSV_INCLUDE_FIELDS";
    public static final String CSV_FIELD_TYPES = "CSV_FIELD_TYPES";

    public String getFilePath();
}
