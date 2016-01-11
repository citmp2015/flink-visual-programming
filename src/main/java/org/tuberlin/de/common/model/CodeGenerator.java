package org.tuberlin.de.common.model;

import com.github.javaparser.ast.CompilationUnit;
import org.omg.CORBA.portable.OutputStream;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Malcolm-X on 10.12.2015.
 */
public class CodeGenerator {

    public static String generateCode(JobGraph jobGraph) {
//        return getCompilationUnit(jobGraph).toString();;
        String result = null;
        jobGraph.getDataSources();


        return result;
    }

    private static String generateStatic(JobGraph graph) throws IllegalArgumentException {
        if (graph == null || !graph.verify())
            throw new IllegalArgumentException("JobGraph must not be null and the verify()-method needs to return true " +
                    "(graph is not empty, complete, executable and valid)");
        String result = null;
        List<JobComponent> dataSources = new ArrayList<JobComponent>();
        List<JobComponent> dataSinks = new ArrayList<JobComponent>();
        Map<String, Object> jobPropertes = graph.getProperties();

        for (JobComponent component : graph.getComponents()) {
//            if (component.getType().equals)
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

    private static CompilationUnit getCompilationUnit(JobGraph graph) {
//        if (graph == null) return null;
//        if (graph.isEmpty()) return null;
//        //TODO weitere sanity checks auf parametern
//        CompilationUnit cu = new CompilationUnit();
//        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(graph.getPackage())));
//        ClassOrInterfaceDeclaration type;
//        type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, graph.getName());
//        ASTHelper.addTypeDeclaration(cu, type);
//
//        //collect tokens from jobgraph
//        List<String>
//        for(JobComponent comp : graph.getComponents()){
//
//        }
        return null;
    }
}
