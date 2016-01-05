package org.tuberlin.de.common.model.interfaces;

import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationComponent;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Daniel Schröder
 * @author Marvin Byfield
 *
 * @version 0.1
 *
 * The JobGraph is basically the root of the whole Job or JobGraph it contains all participating Components e.g. FlatMapComponets, GroupByComponents as well as many others.
 * Components are classified in the following categories:
 * <ul>
 * <li>TRANSFORMATION</li>
 * <li>DATASOURCE</li>
 * <li>DATASINK</li>
 * </ul>
 *
 * All Components should be stored in a map (componentMap) with a corresponding ID/key extracted from the front-end JSON file. Map<String, JobComponent>
 *
 * As the component managing the whole graph a Job graph is responsable for <h4>verifing</h4>, which means calling the verify method is supposed to call all underlying
 * (JobComponent) verify methods and summerise the result. If one of the verifing methods gives back a false the whole graph is not valid.
 *
 *
 * @see org.tuberlin.de.common.model.types.ComponentTypes
 *
 *
 */
public interface JobGraph{

    /**
     * The envirorment id is the the varablename for the Flink enviroment. It is important for the code generation.
     * e.g. the Flink code: <i>env.execute("WordCount Example");</i> from the wordcount example can be written as
     * <i>JobGraph.getEnviromentIdentifier + ".execute(\"WordCount\");";</i>
     */
    public String ENVIRONMENT_IDENTIFIER = "environment";

    /**
     * Size of the componentMap
     *
     * @return the number of included JobComponents for this JobGraph
     */
    public int size();

    /**
     * Indicates wether the componentMap is empty or not
     *
     * @return if the graph contains JobComponets or not
     */
    public boolean isEmpty();

    /**
     * Here the verification for this JobGraph instance has to be implemented. conditions like:
     *
     * <ul>
     * <li>Does the output type of the parent match the children input type?</li>
     * <li>Does the JobComponent have the correct amount of children/parents?</li>
     * <li>Correct number of DataSinks/DataSources ... </li>
     * </ul>
     *
     * <h3>ATTENTION</h3> the verify methods of all underlying JobComponents have to be called and the
     * result have to be considered.
     *
     * The JobGraph has to be verified before the code generation is done!
     *
     * @return if the JobGraph is valid or not
     */
    public boolean verify();

    /**
     * The Map of participating JobComponents is supposed to be a Map<String, JobComponent>
     *
     * @return JobComponents
     */
    public Collection<JobComponent> getComponents();

    /**
     * Not defined yet any additional stuff from "outside" e.g. the packagename, imports, comments
     *
     * @return properties
     */
    public Map<String,Object> getProperties();

    /**
     * The Flink source code related to the JobGraph e.g.
     * <br>
     * <i>public class WordCount {<br>
     * <tab>public static void main(String[] args) throws Exception {</tab><br>
     * ...
     * </i>
     *
     * @return returns the code JobGraph specific code required to generate the FlinkJob
     */
    public String getJobSource();

    /**
     *
     * @param componentName
     * @return
     */
    public JobComponent getComponent(String componentName);

    /**
     *
     * @param jobComponent
     */
    public void addComponent(JobComponent jobComponent);

    /*
     * @return env ident
     */
    public String getEnvironmentIdentifier();

    public Collection<DataSourceComponent> getDataSources();

    public String getPackageName();

    public Collection<TransformationComponent> getTransformations();

    public Collection<String> getImports();

    public String getClassName();
}
