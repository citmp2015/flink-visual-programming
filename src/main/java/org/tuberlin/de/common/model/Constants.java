package org.tuberlin.de.common.model;

/**
 * Created by Malcolm-X on 10.12.2015.
 */
public class Constants {


    /**
     * JobComponent
     */
    //component successor null if it doesnt exist
    public static final String JOB_COMPONENT_CHILDREN = "COMPONENT_CHILDREN_JSON";
    //component predecessor null if it doesnt exist
    public static final String JOB_COMPONENT_PARENT = "COMPONENT_PARENT_JSON";
    //datatyp hand over to the component
    public static final String JOB_COMPONENT_INPUT_TYPE = "COMPONENT_INPUT_TYPE_JSON";
    //output of the component
    public static final String JOB_COMPONENT_OUTPUT_TYPE = "COMPONENT_OUTPUT_TYPE_JSON";
    //name of the component if the component contains an embedded class classname has to have the same value
    //also the name of the variable
    public static final String JOB_COMPOENT_KEY = "COMPONENT_KEY_JSON";
    //imports for the component
    public static final String JOB_COMPONENT_IMPORTS_JSON = "JOB_COMPONENT_IMPORTS_JSON";
    //type for the component (DataSource, DataSink, TRansformation) Enumeration in types
    public static final String COMPONENT_TYPE_JSON = "COMPONENT_TYPE";


    /**
     * Transformation type (GroupBy, Flatmap ...) Enumeration in types
     */
    public static final String TRANSFORMATION_TYPE_JSON = "TRANSFORMATION_TYPE";



    /**
     * DataSource type
     */
    public static final String DATA_SOURCE_TYPE = "DATA_SOURCE_TYPE";
    // DataSourceFile
    public static final String DATA_SOURCE_FILE_PATH = "FILE_PATH";
    // DataSourceFileReadFileOfPrimitives classname
    public static final String DATA_SOURCE_FILE_READ_FILE_OF_PRIMITIVES_CLASS_NAME = "PRIMITIVES_CLASS_NAME";
    // DataSourceFileReadFileOfPrimitives2 delimeter
    public static final String DATA_SOURCE_FILE_READ_FILE_OF_PRIMITIVES2_DELIMITER = "DELIMITER";
    // DataSourceFileReadHadoopFile fileInputFormat
    public static final String DATA_SOURCE_FILE_READ_HADOOP_FILE_FILE_INPUT_FORMAT = "HADOOP_FILE_INPUT_FORMAT";
    // DataSourceFileReadHadoopFile key
    public static final String DATA_SOURCE_FILE_READ_HADOOP_KEY = "HADOOP_KEY";
    // DataSourceFileReadHadoopFile value
    public static final String DATA_SOURCE_FILE_READ_HADOOP_VALUE = "HADOOP_VALUE";
    // DataSourceFileReadSequenceFile KEY
    public static final String DATA_SOURCE_FILE_READ_SEQUENCE_FILE_KEY = "SEQUENCE_FILE_KEY";


























    public static final String CSV_INCLUDE_FIELDS = "CSV_INCLUDE_FIELDS";
    public static final String CSV_FIELD_TYPES = "CSV_FIELD_TYPES";



    /**
     * DataSink
     */
    public static final String DATASINK_TYPE = "DATASINK_TYPE";








    /**
     * If we implement the imports in a extra class not required anymore. In private class required
     */

    /**
     *
     */
  //  public static final String COMPONENT_JOB_SOURCE_JSON = "COMPONENT_JOB_SOURCE_JSON";

    public static final String COMPONENT_PATH_JSON = "COMPONENT_CLASS_JSON";
//    public static final String
//    public static final String
//    public static final String


}
