package org.tuberlin.de.common.codegenerator;

import org.omg.CORBA.portable.OutputStream;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.interfaces.transorfmation.Transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Malcolm-X on 10.12.2015.
 */
public class CodeGenerator {

    public static String generateCode(JobGraph jobGraph) throws IllegalArgumentException {
        if (jobGraph == null) throw new IllegalArgumentException(); //TODO null check
        if (!jobGraph.verify()) throw new IllegalArgumentException(); // TODO: graph verification
        if (jobGraph.isEmpty()) return null;
        //String holding the resulting class
        String result = "";
        //aggregate datasources
        Collection<JobComponent> jobComponents = jobGraph.getComponents();
        Collection<DataSource> dataSourceComponents = jobGraph.getDataSources();
        result += printPackage(jobGraph) + "\n";
        result += printImports(jobGraph) + "\n";
        result += printClass(jobGraph);
        return result;
    }

    private static String printClass(JobGraph jobGraph) {
        String result = "public class " + jobGraph.getClassName() + "{\n\n";
        result += printMain(jobGraph);
        //TODO component classes ?
        result += "}";
        return result;
    }

    private static String printMain(JobGraph jobGraph) {
        String result = "public static void main(String[] args) throws Exception{\n";
        //TODO: maybe parse arguments for main
        result += printExecutionEnvironment(jobGraph) + "\n";
        result += printVariables(jobGraph) + "\n";
        result += printComponents(jobGraph) + "\n";
//        result += jobGraph.getEnvironmentIdentifier() + ".execute();"; // Unnecessary, as .print is already triggering the execution
        result += "\n}\n";
        return result;
    }

    private static String printComponents(JobGraph jobGraph) {
        String result = "";
        result += printDataSources(jobGraph) + "\n";

        result += printFlow(jobGraph) + "\n";
//        result += printDataSinks(jobGraph) + "\n";
        return result;
    }

    private static String printDataSinks(JobGraph jobGraph) {
        return null;
        //TODO
    }

    private static String printFlow(JobGraph jobGraph) {
        //TODO find solution to delay generation of transformations with
        // multiple input datasets (e.g. combine) untill all parants have been generated
//        Collection<Transformation> components = jobGraph.getTransformations();
//        if(components == null || components.isEmpty()) throw new IllegalArgumentException(); //TODO
        String result = "";
        for (DataSource c : jobGraph.getDataSources()){
            //TODO dirty hacking
            //TODO array or collection?
            Object parents = c.getParameter(JobComponent.PARENT);
            Object children = c.getParameter(JobComponent.CHILD);
            //TODO, pull to init/varify or define concrete type
            String[] s = new String[2];
//            Collection<JobComponent> parentCollection;
//            Collection<JobComponent> childCollection = null;
            Collection<String> childKeys = c.getChildren();
//            Collection<JobComponent> childCollection = null;
//            if(childKeys == null || childKeys)
//            if(parents == null){
//                //TODO
//            }
//            else if(parents instanceof String[]){
//                //TODO
//            }
//            else if(parents instanceof String){
//                //TODO
//            }
//            else if(parents instanceof JobComponent){
//                parentCollection = new ArrayList<>();
//                parentCollection.add((JobComponent) parents);
//            }
//            else if(parents instanceof JobComponent[]){
//                //TODO
//            }
//            else if(parents instanceof Collection){
//                //TODO check generic type
//            }
//            if(children == null){
//                //TODO
//            }
//            else if(children instanceof String[]){
//                //TODO
//            }
//            else if(children instanceof String){
//                //TODO
//            }
//            else if(children instanceof JobComponent){
//                childCollection = new ArrayList<JobComponent>();
//                childCollection.add((JobComponent) children);
//            }
//            else if(children instanceof JobComponent[]){
//                //TODO
//            }
//            else if(children instanceof Collection){
//                //TODO check generic type
//            }
            //TODO while
            do {
                Collection<String> temp_store1 = new ArrayList<String>();
                for (String childKey : childKeys) {
                    JobComponent child = jobGraph.getComponent(childKey);

                    //TODO ummm, think about that collection thingy a little bit, maybe use list?

                    if(!(child instanceof DataSink)){

                        result += child.getComponentKey();
                        result +=
                                " = ";
                    }
                    result += child.getParents().iterator().next();
                    result +=  child.getJobSource() + ";\n";


                    //TODO: the assumption is, that a only has multiple parents, when it is using multiple dataset (e.g. combine)
                    //this means, that a reused component needs to be added 2x under different keys or we need a differen notation
                    // in the json spec. discuss!
                    Collection<String> temp_store2 = child.getChildren();
                    //TODO integrity: check if parent is datasink, or trust on validate method
                    if(temp_store2 == null || temp_store2.isEmpty()) continue;
                    temp_store1.addAll(temp_store2);
                }
                childKeys = temp_store1;
            }while(!childKeys.isEmpty());

        }
        return result;
    }

    private static String printDataSources(JobGraph jobGraph) {
        String result = "";
        for (DataSource c : jobGraph.getDataSources()){
            result += c.getComponentKey() + " = " + c.getJobSource() + ";\n" ;
        }
        return result;
    }

    private static String printVariables(JobGraph jobGraph) {
        //TODO: maybe add prefix (e.g. Classname) in order to prevent variable names of numbers and to invrease readability
        String result = "";
        //TODO: think about concurrency, etc in collections/maps --> escallate through all classes
        for (JobComponent c : jobGraph.getComponents()){
            if(!(c instanceof DataSink)){
                result += c.getTypeDeclaration() + " " + c.getComponentKey() + ";\n";
            }
        }
        return result;
    }

    private static String printExecutionEnvironment(JobGraph jobGraph) {
        return "final ExecutionEnvironment " + jobGraph.getEnvironmentIdentifier() + " = ExecutionEnvironment.getExecutionEnvironment();\n";
    }



    private static String printImports(JobGraph jobGraph) {
        //TODO what happens with conflicting imports ? (org.tubit.JavaClass, de.tubit.JavaClass)
        Collection<String> packages = jobGraph.getImports();
        if(packages == null || packages.isEmpty()) return null;
        String result = "";
        for (String s : packages) {
            result += "import " + s + ";\n";
        }
        return result;
    }

    private static String printPackage(JobGraph jobGraph) {

       if(jobGraph.getPackageName() == null) return "";
           return "package " + jobGraph.getPackageName() + ";" + "\n";
    }


    private static String generateStatic(JobGraph graph) throws IllegalArgumentException {
        if (graph == null || !graph.verify())
            throw new IllegalArgumentException("JobGraph must not be null and the verify()-method needs to return true " +
                    "(graph is not empty, complete, executable and valid");
        String result = null;
        List<JobComponent> dataSources = new ArrayList<JobComponent>();
        List<JobComponent> dataSinks = new ArrayList<JobComponent>();
        Map<String, Object> jobPropertes = graph.getProperties();

        for (JobComponent component : graph.getComponents()) {
//            if (component.getType().equals)
                //TODO: REIHENFOLGE


        }


        return result;
    }

    public static void generateCode(JobGraph jobGraph, String filename) {
        // TODO
    }

    public static void generateCode(JobGraph jobGraph, OutputStream out) {
        //TODO
    }

    public static void generateCode(JobGraph jobGraph, File file) {
        //TODO
    }

    public static Map<String, String> getComponentSources(JobGraph graph){
        return graph.getComponentSources();
    }
}
