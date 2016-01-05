package org.tuberlin.de.common.model;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public interface JobGraph{
    public String ENVIRONMENT_IDENTIFIER = "environment";

    //public void addDatasource(DataSourceComponent dataSourceComponent){
    //    this.dataSourceList.add(dataSourceComponent);
    //
    //}
    //TODO
    public int size();
    public boolean isEmpty();
    public boolean verify();

    public Iterable<JobComponent> getComponents();

    public Map<String,Object> getProperties();

    public String getJobSource();

    public String getHeader();

    public JobComponent getComponent(String componentName);

    public void addComponent(JobComponent jobComponent);

    public String getEnvironmentIdentifier();
}
