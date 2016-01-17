package org.tuberlin.de.common.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tuberlin.de.common.base.BaseDataSinkPrint;
import org.tuberlin.de.common.base.BaseDataSourceComponentText;
import org.tuberlin.de.common.base.BaseJobGraph;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;

import java.util.HashMap;
import java.util.Map;

public class JSONParser {
    public static JobGraph getJobGraph(String json){
        JSONObject obj = new JSONObject(json);
        JSONObject processes = obj.getJSONObject("processes");
        JSONObject components = obj.getJSONObject("components");
        JSONArray connections = obj.getJSONArray("connections");

        Map<String, Object> jobGraphParameters = new HashMap<>();
        JobGraph graph = new BaseJobGraph("testkey", "testname", "testpackage", jobGraphParameters);

        for(String key : processes.keySet()){
            Map<String, Object> parameters = new HashMap<>();

            //name
            parameters.put(Constants.JOB_COMPOENT_KEY, key);

            //connections
            //TODO multiple connections per process
            String parent = null;
            String children = null;

            for(int i = 0; i < connections.length(); i++){
                JSONObject connection = connections.getJSONObject(i);
                String source = connection.getString("src");
                String target = connection.getString("tgt");

                if(key.equals(source)){
                    children = target;
                }

                if(key.equals(target)){
                    parent = source;
                }
            }

            parameters.put(Constants.JOB_COMPONENT_PARENT, parent);
            parameters.put(Constants.JOB_COMPONENT_CHILDREN, children);

            //parameters
            JSONObject val = processes.getJSONObject(key);

            if(val.has("data")){
                JSONObject data = val.getJSONObject("data");

                //in- & output types
                String inputType = data.has("input_type")
                        ? data.getString("input_type")
                        : parent != null
                            ? getOptData(processes.getJSONObject(parent), "output_type")
                            : null;

                String outputType = data.has("output_type")
                        ? data.getString("output_type")
                        : parent != null
                            ? getOptData(processes.getJSONObject(parent), "input_type")
                            : null;

                parameters.put(Constants.JOB_COMPONENT_INPUT_TYPE, inputType);
                parameters.put(Constants.JOB_COMPONENT_OUTPUT_TYPE, outputType);

                //TODO source code
                //TODO function parameters
            }

            //type
            JSONObject component = components.getJSONObject(val.getString("component"));
            JobComponent comp = null;

            //TODO what is the source of truth for types?
            switch(component.getString("type")){
                case "source":
                    comp = new BaseDataSourceComponentText(graph, parameters);
                    break;

                case "transform":

                case "sink":
                    comp = new BaseDataSinkPrint(graph, parameters);
                    break;
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

    private static String getOptData(JSONObject process, String dataKey){
        return process.has("data") ? process.getJSONObject("data").optString(dataKey, null) : null;
    }
}
