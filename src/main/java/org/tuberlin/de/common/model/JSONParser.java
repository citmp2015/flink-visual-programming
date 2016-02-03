package org.tuberlin.de.common.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tuberlin.de.common.base.*;
import org.tuberlin.de.common.model.interfaces.CompilationUnitComponent;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationAggregate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {
    public static JobGraph getJobGraph(JSONObject obj){
        JSONObject processes = obj.getJSONObject("processes");
        // JSONObject components = obj.getJSONObject("components");
        JSONArray connections = obj.getJSONArray("connections");

        Map<String, Object> jobGraphParameters = new HashMap<>();
        JobGraph graph = new BaseJobGraph("testkey", Constants.ENTRY_CLASS_NAME, "testpackage", jobGraphParameters);

        HashMap<String, ArrayList<String>> parentMap = new HashMap<>();
        HashMap<String, ArrayList<String>> childrenMap = new HashMap<>();

        for(int i = 0; i < connections.length(); i++){
            JSONObject connection = connections.getJSONObject(i);
            String source = connection.getString("src");
            String target = connection.getString("tgt");

            if(!parentMap.containsKey(target)){
                parentMap.put(target, new ArrayList<>());
            }

            if(!childrenMap.containsKey(source)){
                childrenMap.put(source, new ArrayList<>());
            }

            parentMap.get(target).add(source);
            childrenMap.get(source).add(target);
        }

        HashMap<String, String> inputTypes = new HashMap<>();
        HashMap<String, String> outputTypes = new HashMap<>();

        for(String key : processes.keySet()){
            JSONObject val = processes.getJSONObject(key);
            if(!val.has("data")) continue;

            JSONObject data = val.getJSONObject("data");

            if(data.has("output_type")){
                outputTypes.put(key, data.getString("output_type"));
            }

            if(data.has("input_type")){
                inputTypes.put(key, data.getString("input_type"));
            }
        }

        //loop until all possible types were resolved
        boolean allResolved = false;

        while(!allResolved){
            allResolved = true;

            for(String key : processes.keySet()){
                if(!parentMap.containsKey(key)) continue;
                if(inputTypes.containsKey(key)) continue;
                if(outputTypes.containsKey(key)) continue;

                for(String parent : parentMap.get(key)){
                    String type = outputTypes.getOrDefault(parent, null);

                    if(type == null && inputTypes.containsKey(parent)){
                        type = inputTypes.get(parent);
                        outputTypes.put(parent, type);
                    }

                    if(type != null){
                        inputTypes.put(key, type);
                        allResolved = false;
                    }
                }
            }
        }

        for(String key : processes.keySet()){
            Map<String, Object> parameters = new HashMap<>();

            //name
            parameters.put(Constants.JOB_COMPOENT_KEY, key);

            parameters.put(Constants.JOB_COMPONENT_PARENT, parentMap.getOrDefault(key, new ArrayList<>(0)));
            parameters.put(Constants.JOB_COMPONENT_CHILDREN, childrenMap.getOrDefault(key, new ArrayList<>(0)));

            //in- & output types
            parameters.put(Constants.JOB_COMPONENT_INPUT_TYPE, inputTypes.getOrDefault(key, null));
            parameters.put(Constants.JOB_COMPONENT_OUTPUT_TYPE, outputTypes.getOrDefault(key, null));

            //parameters
            JSONObject val = processes.getJSONObject(key);

            if(val.has("data")){
                JSONObject data = val.getJSONObject("data");


                if(data.has("tupleIndex")){
                    parameters.put(Constants.TUPLE_INDEX, data.getInt("tupleIndex") + "");
                }

                addIfData(parameters, Constants.COMPONENT_PATH_JSON, data, "filePath");
                addIfData(parameters, CompilationUnitComponent.COMPONENT_SOURCE_JSON, data, "javaSourceCode");
                addIfData(parameters, CompilationUnitComponent.PACKAGE_NAME_KEY, data, "packageName");
                addIfData(parameters, CompilationUnitComponent.FUNCTION_NAME_KEY, data, "functionName");
            }

            //type
            String componentName = val.getString("component");
            JobComponent comp;

            // or:
            switch(componentName){
                case "textdatasource":
                case "readFile":
                    comp = new BaseDataSourceComponentText(graph, parameters);
                    break;

                case "group":
                case "groupBy":
                    comp = new BaseGroupBy(graph, parameters);
                    break;

                case "fastCreate: CSV Datasink":
                case "writeCSV":
                    comp = new BaseDataSinkPrint(graph, parameters);
                    break;

                case "flatmap":
                    comp = new BaseTransformationFlatMap(graph, parameters);
                    break;

                /*
                case "map":
                    comp = new BaseTransformationMap(graph, parameters);
                    break;

                case "reduce":
                    ...
                */

                case "sum":
                    parameters.put(TransformationAggregate.FUNCTION_KEY, "SUM");
                    comp = new BaseTransformationAggregate(graph, parameters);
                    break;

                default:
                    System.out.println("Ignoring component " + componentName);
                    continue; //FIXME should throw errors in the future
            }

            graph.addComponent(comp);
        }

//        graph.addComponent(new BaseDataSourceComponentCSV()); //TODO complete when class implemented
//        graph.addComponent(new BaseFlatMapComponent()); //TODO complete when class implemented
//        graph.addComponent(new BaseGroupByComponent()); //TODO complete when class implemented
//        graph.addComponent(new BaseAggregateComponent()); //TODO complete when class implemented
//        graph.addComponent(new BaseDataSinkComponentPrint()); //TODO complete when class implemented
        return graph;
    }

    private static void addIfData(Map<String, Object> parameters, String objKey, JSONObject data, String dataKey){
        if(data.has(dataKey)){
            parameters.put(objKey, data.getString(dataKey));
        }
    }
}