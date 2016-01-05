package org.tuberlin.de.common.model;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public class JSONParser {
    public static JobGraph getJobGraph(String json){
        //TODO dummy impl
        JobGraph graph = new BaseJobGraph("dummy", "DummyJob", "org.tuberlin.de.dummy");
        //parse json
        graph.addComponent(new BaseDataSourceComponentCSV()); //TODO complete when class implemented
        graph.addComponent(new BaseFlatMapComponent()); //TODO complete when class implemented
        graph.addComponent(new BaseGroupByComponent()); //TODO complete when class implemented
        graph.addComponent(new BaseAggregateComponent()); //TODO complete when class implemented
        graph.addComponent(new BasePrintDataSinkComponent()); //TODO complete when class implemented
    }
}
