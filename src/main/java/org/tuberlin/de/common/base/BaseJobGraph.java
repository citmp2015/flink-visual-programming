package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public class BaseJobGraph implements JobGraph {



    //private List<DataSourceComponent> dataSourceList;
    //private List<DataSinkComponent> dataSinkList;
    //private List<JobComponent> componentList;

    private Map<String, JobComponent> componentMap;
    private boolean verified;
    private String jobKey, jobName, jobPackage;
    private Map<String, Object> properties;
    /*
    *
    * */
    public BaseJobGraph(String jobKey, String jobName, String jobPackage, Map<String, Object> parameters){
        //
    //    this.dataSourceList = new ArrayList<DataSourceComponent>();
    //    this.componentList = new ArrayList<JobComponent>();
    //    this.dataSinkList = new ArrayList<DataSinkComponent>();
        this.componentMap = new HashMap<String, JobComponent>();
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
        //TODO
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




        return null;
    }


    @Override
    public JobComponent getComponent(String componentName){
        //TODO
        return null;
    }
    @Override
    public void addComponent(JobComponent component){
        //NPE check
        if(component == null) throw new IllegalArgumentException("Parameter \"component\" must not be null!");
        //general parameter verification
        else if(!component.verify()) throw new IllegalArgumentException("Call to .verify() returned false. Please check the parameters of the object \"component\" or check the impleentation.");
        //TODO verify integrity of whole graph (e.g. consistency between component relations, etc.


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
//            //component is datasource
//        }//end if-instanceof
//        else if(component instanceof AggregateComponent){
//            //component is aggregate function
//        }//end if-instanceof
//        //default case (no matching componenttype found
//        else{
//            //TODO
//        }//end else-instanceof
    }

    @Override
    public String getEnvironmentIdentifier() {
        return JobGraph.ENVIRONMENT_IDENTIFIER;
    }

    @Override
    public Collection<DataSourceComponent> getDataSources() {
        return null;
    }

    @Override
    public String getPackageName() {
        return null;
    }

    @Override
    public Collection<TransformationComponent> getTransformations() {
        return null;
    }

    @Override
    public Collection<String> getImports() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
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
