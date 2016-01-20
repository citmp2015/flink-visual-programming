package org.tuberlin.de.common.base;

import org.tuberlin.de.common.codegenerator.CodeGenerator;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.interfaces.transorfmation.Transformation;

import java.util.*;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public class BaseJobGraph implements JobGraph {



    //private List<DataSourceComponent> dataSourceList;
    //private List<DataSinkComponent> dataSinkList;
    //private List<JobComponent> componentList;
//TODO "add"-method?
    private Map<String, JobComponent> componentMap;
    private boolean verified;
    private String jobKey, jobName, jobPackage;
    private Map<String, Object> properties;
    private Map<String, Transformation> transformationMap;
    private Map<String, DataSource> dataSourceMap;
    private Map<String, DataSink> dataSinkMap;

    /*
    *
    * */
    public BaseJobGraph(String jobKey, String jobName, String jobPackage, Map<String, Object> parameters){
        //
    //    this.dataSourceList = new ArrayList<DataSourceComponent>();
    //    this.componentList = new ArrayList<JobComponent>();
    //    this.dataSinkList = new ArrayList<DataSinkComponent>();
        this.componentMap = new HashMap<String, JobComponent>();
        this.transformationMap = new HashMap<String, Transformation>();
        this.dataSinkMap = new HashMap<String, DataSink>();
        this.dataSourceMap = new HashMap<String, DataSource>();
        this.verified = false;
        this.jobKey = jobKey;
        this.jobName = jobName;
        this.jobPackage =jobPackage;
    }

    //public void addDatasource(DataSourceComponent dataSourceComponent){
    //    this.dataSourceList.add(dataSourceComponent);
    //
    //}
    @Override
    public boolean verify(){
        //TODO verify later
        if (componentMap.isEmpty()){
            return false;
        }
        else{
            for(JobComponent comp : componentMap.values()){
                if (!comp.verify()) return false;
                //TODO check forward / backward relations
            }
        }

        return true;
    }

    @Override
    public Collection<JobComponent> getComponents() {
        return componentMap.values();
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
    @Override
    public String getJobSource(){
        // TODO discuss: this method can add a cyclic reference to the code-generator (convenience)
        return CodeGenerator.generateCode(this);
    }


    @Override
    public JobComponent getComponent(String componentName){
        //TODO integrity
        return componentMap.get(componentName);
    }
    @Override
    public void addComponent(JobComponent component){
        //NPE check
        if(component == null) throw new IllegalArgumentException("Parameter \"component\" must not be null!");
        //general parameter verification
//        else if(!component.verify()) throw new IllegalArgumentException("Call to .verify() returned false. Please check the parameters of the object \"component\" or check the impleentation.");
        //TODO verify integrity of whole graph (e.g. consistency between component relations, etc.
        //TODO componentName and componentKey are 2 different identifiers for the same value, check and fix
        //all components are added to main map
        componentMap.put(component.getComponentKey(), component);
        //store idexes in seperate hashmaps, no else-if, since we are working on interfaces
        if(component instanceof Transformation) transformationMap.put(component.getComponentKey(), (Transformation)component);
        if(component instanceof DataSink) dataSinkMap.put(component.getComponentKey(), (DataSink) component);
        if(component instanceof DataSource) dataSourceMap.put(component.getComponentKey(), (DataSource) component);

//TODO maybe add special treatment for compoentnts as part of init/verify life-cycle, to be discussed, ticket needed
//        //check component type
//        if(component instanceof GroupByComponent){
//
//
//        }//end if-instanceof
//        else if(component instanceof FlatMapComponent){
//            //component is flatmap function
//
//        }//end if-instanceof
//        else if(component instanceof DataSinkComponent){
//            //component is datasink
//        }//end if-instanceof
//        //component is aggregate function
//        else if(component instanceof DataSourceComponent){
//            //component is datasources
//        }//end if-instanceof
//        else if(component instanceof AggregateComponent){
//            //component is aggregate function
//        }//end if-instanceof
//        //default case (no matching componenttype found
//        else{
//            //TODO
//        }//end else-instanceof
        //TODO add more convenience here :)

    }

    @Override
    public String getEnvironmentIdentifier() {
        return JobGraph.ENVIRONMENT_IDENTIFIER;
    }

    @Override
    public Collection<DataSource> getDataSources() {
        return dataSourceMap.values();
    }

    @Override
    public String getPackageName() {
        return this.jobPackage;
    }

    @Override
    public Collection<Transformation> getTransformations() {
        return this.transformationMap.values();
    }

    @Override
    public Collection<String> getImports() {
        //TODO integrity
        Collection<String> imports = new HashSet<String>();
        for(JobComponent jc : componentMap.values()) {
            if (jc == null) System.out.println("OHOH");//TODO better output or fail
            if(!(jc.getImports() == null) && !jc.getImports().isEmpty())
            imports.addAll(jc.getImports());
        }
        imports.addAll(this.getLocalImports()); // in case there are local imports e.g. flink framework, ...)
        return imports;
    }

    private Collection<String> getLocalImports() {
        //TODO currently only a dummy / basic import, check for additional import cases
        //TODO design smart "dependency management", what about duplicate imports?
        Collection<String> result = new ArrayList<String>();
        result.add("org.apache.flink.api.common.*");
        result.add("org.apache.flink.api.java.*");
        result.add("org.apache.flink.util.*");
        result.add("org.apache.flink.api.java.operators.*");
        result.add("org.apache.flink.api.java.tuple.*");
        result.add("org.apache.flink.api.java.aggregation.Aggregations");
        return result;
    }

    @Override
    public String getClassName() {
        return this.jobName;
    }

    @Override
    public int size() {
        return componentMap.size();
    }

    @Override
    public boolean isEmpty() {
        return componentMap.isEmpty();
    }

 //   @Override
   // public boolean contains(Object o) {
     //   return componentMap.containsValue(o);
    //}

//    @Override
//    public Iterator<JobComponent> iterator() {
//        return componentMap.values().iterator();
//    }
//
//    @Override
//    public Object[] toArray() {
//        return componentMap.values().toArray();
//    }
//
//    @Override
//    public <T> T[] toArray(T[] a) {
//        return componentMap.values().toArray(a);
//    }
//
//    @Override
//    public boolean add(JobComponent jobComponent) {
//        if (jobComponent == null) return false;
//        String key = jobComponent.getKey();
//        if(key == null || key.isEmpty())
//                return false;
//        return (componentMap.put(key, jobComponent) == null || ) ? true : false;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends JobComponent> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }
}
