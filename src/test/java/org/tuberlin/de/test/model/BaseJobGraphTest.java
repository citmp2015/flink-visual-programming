package org.tuberlin.de.test.model;

import org.tuberlin.de.common.base.BaseAggregateComponent;
import org.tuberlin.de.common.base.BaseDataSinkComponentPrint;
import org.tuberlin.de.common.base.BaseDataSourceComponentText;
import org.tuberlin.de.common.base.BaseFlatMapComponent;
import org.tuberlin.de.common.base.BaseGroupByComponent;
import org.tuberlin.de.common.base.BaseJobGraph;
import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSinkComponent;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.AggregateComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.FlatMapComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.GroupByComponent;

import java.util.HashMap;
import java.util.Map;

public class BaseJobGraphTest {



  /**  public static final String COMPONENT_TYPE_JSON = "COMPONENT_TYPE";
    public static final String TRANSFORMATION_TYPE_JSON = "TRANSFORMATION_TYPE";
    public static final String DATASOURCE_TYPE = "DATASOURCE_TYPE";
    public static final String DATASINK_TYPE = "DATASINK_TYPE";
    public static final String COMPONENT_IMPORTS_JSON = "COMPONENT_IMPORTS_JSON";
    public static final String COMPONENT_JOB_SOURCE_JSON = "COMPONENT_JOB_SOURCE_JSON";
    public static final String COMPONENT_SOURCE_JSON = "COMPONENT_SOURCE_JSON";
    public static final String COMPONENT_CHILDREN = "COMPONENT_CHILDREN_JSON";
    public static final String COMPONENT_PARENT = "COMPONENT_PARENT_JSON";
    public static final String COMPONENT_INPUT_TYPE = "COMPONENT_INPUT_TYPE_JSON";
    public static final String COMPONENT_OUTPUT_TYPE = "COMPONENT_OUTPUT_TYPE_JSON";
    public static final String COMPOENT_KEY = "COMPONENT_KEY_JSON";
    public static final String COMPONENT_PATH_JSON = "COMPONENT_CLASS_JSON"; */



    @org.junit.Before
    public void setUp() throws Exception {

        //JobGraph
        Map<String, Object> jGraphParamters = new HashMap<String, Object>();
        JobGraph jobGraph = new BaseJobGraph("testkey", "testname", "testpackage", jGraphParamters);

        //DataSource
        Map<String, Object> dSourceCompParameters = new HashMap<String, Object>();
        DataSourceComponent dataSourceComponent = new BaseDataSourceComponentText(jobGraph,  dSourceCompParameters);

        //FlatMap
        Map<String, Object> fMapPrarameters = new HashMap<String, Object>();
        FlatMapComponent flatMapComponent = new BaseFlatMapComponent(jobGraph, fMapPrarameters);

        //GroupBy
        Map<String, Object> gByParameters = new HashMap<String, Object>();
        GroupByComponent groupByComponent = new BaseGroupByComponent(jobGraph, gByParameters);

        //Aggregate
        Map<String, Object> aggParameters = new HashMap<String, Object>();
        aggParameters.put(AggregateComponent.FIELD_KEY, 1);
        aggParameters.put(AggregateComponent.FUNCTION_KEY, AggregateComponent.FUNCTION_TYPES.SUM);
        AggregateComponent aggregateComponent = new BaseAggregateComponent(jobGraph, aggParameters);

        //DataSink
        Map<String, Object> dSinkParameters = new HashMap<String, Object>();
        DataSinkComponent dataSinkComponent = new BaseDataSinkComponentPrint(jobGraph, dSinkParameters);



        //JobGraphParam
        jGraphParamters.put(Constants.COMPONENT_IMPORTS_JSON, "test.job.graph.import");

        //DataSourceParam
        dSourceCompParameters.put(Constants.COMPONENT_CHILDREN, flatMapComponent);
        dSourceCompParameters.put(Constants.COMPONENT_PARENT, null);
        dSourceCompParameters.put(Constants.COMPONENT_INPUT_TYPE, null);
        dSourceCompParameters.put(Constants.COMPONENT_OUTPUT_TYPE, "DataSet<String>");
        dSourceCompParameters.put(Constants.COMPOENT_KEY, "dataSourceKey");
        //TODO COMPONENT_JOB_SOURCE_JSON

        //FlatMapParam
        fMapPrarameters.put(Constants.COMPONENT_CHILDREN, groupByComponent);
        fMapPrarameters.put(Constants.COMPONENT_PARENT, dataSourceComponent);
        fMapPrarameters.put(Constants.COMPONENT_JOB_SOURCE_JSON,    "public class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {\n" +
                                                                        "@Override+\n" +
                                                                        "public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {+\n" +
                                                                            "// normalize and split the line into words+\n" +
                                                                            "String[] tokens = value.toLowerCase().split(\"\\\\W+\");\n" +
                                                                            "// emit the pairs\n" +
                                                                            "for (String token : tokens) {\n" +
                                                                                "if (token.length() > 0) {\n" +
                                                                                    "out.collect(new Tuple2<String, Integer>(token, 1));\n" +
                                                                                "}\n" +
                                                                            "}\n" +
                                                                        "}\n" +
                                                                    "}");
        fMapPrarameters.put(Constants.COMPONENT_INPUT_TYPE, dSourceCompParameters.get(Constants.COMPONENT_OUTPUT_TYPE));
        fMapPrarameters.put(Constants.COMPONENT_OUTPUT_TYPE, "Tuple2<String, Integer>");
        fMapPrarameters.put(Constants.COMPOENT_KEY, "flatmapKey");
        //TODO COMPONENT_JOB_SOURCE_JSON


        //GroupByParam
        //TODO add field to GoupByComponent interface
        gByParameters.put(Constants.COMPONENT_CHILDREN, aggregateComponent);
        gByParameters.put(Constants.COMPONENT_PARENT, flatMapComponent);
        gByParameters.put(Constants.COMPONENT_INPUT_TYPE, fMapPrarameters.get(Constants.COMPONENT_OUTPUT_TYPE));
        gByParameters.put(Constants.COMPONENT_OUTPUT_TYPE, "Tuple2<String, Integer>");
        gByParameters.put(Constants.COMPOENT_KEY, "groupByKey");
        //TODO COMPONENT_JOB_SOURCE_JSON

        //AggregateParam
        //TODO sum und 1
        aggParameters.put(Constants.COMPONENT_CHILDREN, dataSinkComponent);
        aggParameters.put(Constants.COMPONENT_PARENT, groupByComponent);
        aggParameters.put(Constants.COMPONENT_INPUT_TYPE, gByParameters.get(Constants.COMPONENT_OUTPUT_TYPE));
        aggParameters.put(Constants.COMPONENT_OUTPUT_TYPE, "Tuple2<String, Integer>");
        aggParameters.put(Constants.COMPOENT_KEY, "aggrKey");
        //TODO COMPONENT_JOB_SOURCE_JSON

        //DatSinkPara
        dSinkParameters.put(Constants.COMPONENT_CHILDREN, null);
        dSinkParameters.put(Constants.COMPONENT_PARENT, aggregateComponent);
        dSinkParameters.put(Constants.COMPONENT_INPUT_TYPE, aggParameters.get(Constants.COMPONENT_OUTPUT_TYPE));
        dSinkParameters.put(Constants.COMPONENT_OUTPUT_TYPE, null);
        dSinkParameters.put(Constants.COMPOENT_KEY, "dataSinkKey");
        //TODO COMPONENT_JOB_SOURCE_JSON



        jobGraph.addComponent(dataSourceComponent);
        jobGraph.addComponent(flatMapComponent);
        jobGraph.addComponent(groupByComponent);
        jobGraph.addComponent(aggregateComponent);
        jobGraph.addComponent(dataSinkComponent);



    }

    @org.junit.Test
    public void testGetJobSource() throws Exception {
        System.out.println();
    }
}