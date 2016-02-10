package org.tuberlin.de.common.model;

/**
 * Created by Malcolm-X on 10.12.2015.
 */
public class Constants {


    /**
     * TYPE IDENTIFIER
     */

    public static final String TYPE_DATA_SOURCE_FILE_READ_CSV_FILE = "READ_CSV_FILE";
    public static final String TYPE_DATA_SOURCE_FILE_READ_FILE_OF_PRIMITIVES = "READ_FILE_OF_PRIMITIVES";
    public static final String TYPE_DATA_SOURCE_FILE_READ_FILE_OF_PRIMITIVES_2 = "READ_FILE_OF_PRIMITIVES_2";
    public static final String TYPE_DATA_SOURCE_FILE_READ_HADOOP_FILE = "READ_HADOOP_FILE";
    public static final String TYPE_DATA_SOURCE_FILE_READ_SEQUENCE_FILE = "READ_SEQUENCE_FILE";
    public static final String TYPE_DATA_SOURCE_FILE_READ_TEXT_FILE = "READ_TEXT_FILE";
    public static final String TYPE_DATA_SOURCE_FILE_READ_TEXT_FILE_WITH_VALUE = "READ_TEXT_FILE_WITH_VALUE";


    public static final String TYPE_TRANSFORMATION_AGGREGATE = "AGGREGATE";
    public static final String TYPE_TRANSFORMATION_CO_GROUP = "CO_GROUP";
    public static final String TYPE_TRANSFORMATION_COMBINE = "COMBINE";
    public static final String TYPE_TRANSFORMATION_CROSS = "CROSS";
    public static final String TYPE_TRANSFORMATION_CUSTOM_PARTITIONING = "CUSTOM_PARTITIONING";
    public static final String TYPE_TRANSFORMATION_DISTINCT = "DISTINCT";
    public static final String TYPE_TRANSFORMATION_FILTER = "FILTER";
    public static final String TYPE_TRANSFORMATION_FIRST_N = "FIRST_N";
    public static final String TYPE_TRANSFORMATION_FLAT_MAP = "FLAT_MAP";
    public static final String TYPE_TRANSFORMATION_GROUP_BY = "GROUP_BY";
    public static final String TYPE_TRANSFORMATION_HASH_PARTITION = "HASH_PARTITION";
    public static final String TYPE_TRANSFORMATION_JOIN = "JOIN";
    public static final String TYPE_TRANSFORMATION_MAP = "MAP";
    public static final String TYPE_TRANSFORMATION_MAP_PARTITION = "MAP_PARTITION";
    public static final String TYPE_TRANSFORMATION_OUTER_JOIN = "OUTER_JOIN";
    public static final String TYPE_TRANSFORMATION_REBALANCE = "REBALANCE";
    public static final String TYPE_TRANSFORMATION_REDUCE = "REDUCE";
    public static final String TYPE_TRANSFORMATION_REDUCE_GROUP = "REDUCE_GROUP";
    public static final String TYPE_TRANSFORMATION_SORT_PARTITION = "SORT_PARTITION";
    public static final String TYPE_TRANSFORMATION_UNION = "UNION";

    public static final String TUPLE_INDEX = "TUPLE_INDEX";

    public static final String TYPE_DATA_SINK_PRINT = "PRINT";


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
    public static final String TRANSFORMATION_TYPE = "TRANSFORMATION_TYPE";


    /**
     * DataSource type
     */
    public static final String DATA_SOURCE_TYPE = "DATA_SOURCE_TYPE";
    // DataSourceFile path
    public static final String DATA_SOURCE_FILE_PATH = "DATA_SOURCE_FILE_PATH";
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
    // DataSourceFileReadSequenceFile key
    public static final String DATA_SOURCE_FILE_READ_SEQUENCE_FILE_KEY = "SEQUENCE_FILE_KEY";
    // DataSourceFileReadSequenceFile value
    public static final String DATA_SOURCE_FILE_READ_SEQUENCE_FILE_VALUE = "SEQUENCE_FILE_VALUE";

    // DataSourceFromCollection collection
    public static final String DATA_SOURCE_FROM_COLLECTION_COLLECTION = "COLLECTION_COLLECTION";
    // DataSourceFromCollection2 iterator
    public static final String DATA_SOURCE_FROM_COLLECTION2_ITERATOR = "COLLECTION_ITERATOR";
    // DataSourceFromCollection Class
    public static final String DATA_SOURCE_FROM_COLLECTION2_CLASS = "COLLECTION_CLASS";
    // DataSourceFromElements
    public static final String DATA_SOURCE_FROM_ELEMENTS_T = "ELEMENTS_T";
    // DataSourceFromParallelCollection SplitableIterator
    public static final String DATA_SOURCE_FROM_PARALLEL_COLLECTION_SPLIT_ITERATOR = "P_C_SPLIT_ITERATOR";
    // DataSourceFromParallelCollection Class
    public static final String DATA_SOURCE_FROM_PARALLEL_COLLECTION_CLASS = "P_C_CLASS";
    // DataSourceGenerateSequence from
    public static final String DATA_SOURCE_GENERATE_SEQUENCE_FROM = "GENERATE_FROM";
    // DataSourceGenerateSequence to
    public static final String DATA_SOURCE_GENERATE_SEQUENCE_TO = "GENERATE_TO";

    // DataSourceGenericReadFile inputFormat
    public static final String DATA_SOURCE_GENERIC_READ_FILE_INPUT_FORMAT = "GEN_INPUT_FORMAT";
    // DataSourceGenericReadFile path
    public static final String DATA_SOURCE_GENERIC_READ_FILE_PATH = "GEN_PATH";
    // DataSourceGenericCreateInput inputFormat
    public static final String DATA_SOURCE_GENERIC_CREATE_INPUT_FORMAT = "GEN_CREATE_INPUT_FORMAT";

    /**
     * DataSink
     */
    public static final String DATA_SINK_TYPE = "DATA_SINK_TYPE";
    // DataSinkFile path
    public static final String DATA_SINK_FILE_PATH = "DATA_SINK_FILE_PATH";

    public static final String CSV_INCLUDE_FIELDS = "CSV_INCLUDE_FIELDS";
    public static final String CSV_FIELD_TYPES = "CSV_FIELD_TYPES";

    /**
     * If we implement the imports in a extra class not required anymore. In private class required
     */

  //  public static final String COMPONENT_JOB_SOURCE_JSON = "COMPONENT_JOB_SOURCE_JSON";

    public static final String COMPONENT_PATH_JSON = "COMPONENT_CLASS_JSON";

    // Default values for the FlinkSkeleton
    public static final String ENTRY_CLASS_NAME = "Jobgraph";
    public static final String ENTRY_CLASS_NAME_WITH_PACKAGE = "testpackage.Jobgraph";
    public static final String FLINK_JOB_NAME = "FlinkJob";

    // The following values should be used, when referring to tags in the pom.xml, which text values should be replaced by the DOMParser
    public static final String ENTRY_CLASS_KEY = "mainClass";
    public static final String ARTIFACT_ID_KEY = "artifactId"; // Corresponds to the FLINK_JOB_NAME
}
