package org.tuberlin.de.deployment;

import java.io.OutputStream;
import java.util.List;

/**
 * This interface describes the interaction between the code generation module and the deployment of the generated code.
 * The interface provides two main methods to (1) compile a JAR file from the sources and (2) to deploy this JAR file
 * to a Flink cluster
 *
 * Created by Fabian on 10.01.2016.
 */
public interface DeplyomentInterface {

    /**
     * This method creates a JAR containing all classes from the job graph.
     *
     * example:
     * entryClass:
     *      public class MyFlinkJob{
     *          public static void main(String args){
     *              DataSet set = ...   //some datasource
     *              set.map(new MyMapper1());
     *              set.flatMap(new MyFlatMapper1());
     *              set.map(new MyMapper2());
     *          }
     *      }
     * clazzes is a list of classes like this (all plain String):
     *      public class MyMapper1 implements MapFunction<String, String> {
     *          @Override
     *          public void map(String value, Collector<String> out){
     *              //some udf map function
     *          }
     *      }
     *
     * @param entryClass this is the class that contains the job graph. This class is essentially the entry point of
     *                   the flink job and contains only the calls of the user defined functions
     * @param clazzes this is a list of all user defined functions, each of those a class.
     * @param deploy when this flag is true the compiled JAR will be deployed to a running Flink cluster
     *               The configurations will be saved into config files (default) and can later in the project be overwritten by
     *               a dialogue in the frontend.
     */
    public void generateProjectJAR(String entryClass, List<String> clazzes, boolean deploy);

    /**
     * This method is called after the JAR was generated. It will return a OutputStream that contains the JAR File.
     * This is mainly for frontend functions to download the generated JAR.
     */
    public OutputStream getJarStream();
}
