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
    //type for the component (DataSource, DataSink, TRansformation)
    public static final String COMPONENT_TYPE_JSON = "COMPONENT_TYPE";


    /**
     * Transformation
     */
    public static final String TRANSFORMATION_TYPE_JSON = "TRANSFORMATION_TYPE";



    /**
     * DataSource
     */
    public static final String DATASOURCE_TYPE = "DATASOURCE_TYPE";



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
