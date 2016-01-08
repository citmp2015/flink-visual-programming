package org.tuberlin.de.common.codegenerator;

import org.omg.CORBA.portable.OutputStream;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSinkComponent;
import org.tuberlin.de.common.model.interfaces.datasource.DataSourceComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationComponent;

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
        Collection<DataSourceComponent> dataSourceComponents = jobGraph.getDataSources();
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
        String result = "public static void main(String[] args){\n";
        //TODO: maybe parse arguments for main
        result += printExecutionEnvironment(jobGraph) + "\n";
        result += printVariables(jobGraph) + "\n";
        result += printComponents(jobGraph) + "\n";
        result += "}";
        return result;
    }

    private static String printComponents(JobGraph jobGraph) {
        String result = "";
        result += printDataSources(jobGraph) + "\n";

        result += printTransformations(jobGraph) + "\n";
        result += printDataSinks(jobGraph) + "\n";
        return result;
    }

    private static String printDataSinks(JobGraph jobGraph) {
        return null;
        //TODO
    }

    private static String printTransformations(JobGraph jobGraph) {
        //TODO find solution to delay generation of transformations with
        // multiple input datasets (e.g. combine) untill all parants have been generated
        Collection<TransformationComponent> components = jobGraph.getTransformations();
        if(components == null || components.isEmpty()) throw new IllegalArgumentException(); //TODO
        String result = "";
        for (DataSourceComponent c : jobGraph.getDataSources()){
            //TODO dirty hacking
            JobComponent comp = ((JobComponent[])c.getParents().toArray())[0];
            result += c.getComponentKey() + " = " + comp.getComponentKey() + c.getJobSource() + ";\n" ;

        }
        return result;
    }

    private static String printDataSources(JobGraph jobGraph) {
        String result = "";
        for (DataSourceComponent c : jobGraph.getDataSources()){
            result += c.getComponentKey() + " = " + c.getJobSource() + ";\n" ;
        }
        return result;
    }

    private static String printVariables(JobGraph jobGraph) {
        //TODO: maybe add prefix (e.g. Classname) in order to prevent variable names of numbers and to invrease readability
        String result = "";
        //TODO: think about concurrency, etc in collections/maps --> escallate through all classes
        for (JobComponent c : jobGraph.getComponents()){
            if(!(c instanceof DataSinkComponent)){
                result += c.getInputType() + " " + c.getComponentKey() + ";\n";
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

}
