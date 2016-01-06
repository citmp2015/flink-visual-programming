package org.tuberlin.de.common.model.abstracts.transformation;

import org.tuberlin.de.common.model.abstracts.AbstractJobComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.FlatMapComponent;

import java.util.Map;

/**
 * Created by Malcolm-X on 09.12.2015.
 */
public abstract class AbstractFlatMapComponent extends AbstractJobComponent implements FlatMapComponent {
    //TODO central store for static strings(file?)

    public static final String FUNCTION_NAME_KEY = "function_name";
    public static final String FUNCTION_SOURCE_KEY = "function_source";
    public static final String PACKAGE_NAME_KEY = "package_name";

    //private static final String JOB_SOURCE = ".flatmap(new "+ functionName +"  )";



    private String functionName, functionSource, packageName;


    @Override
    public void init(Map<String, Object> parameters) {
        //neccesary params
        String currentKey = " ";
        if (!parameters.containsKey(FUNCTION_NAME_KEY)
                || !parameters.containsKey(FUNCTION_SOURCE_KEY)
                || !parameters.containsKey(FUNCTION_SOURCE_KEY)) {
            throw new IllegalArgumentException("Missing parameter, please refere to the documentation for a list of neccesarry parameters!");
        }

        try {
            currentKey = FUNCTION_NAME_KEY;
            this.functionName = (String) parameters.get(FUNCTION_NAME_KEY);
            currentKey = FUNCTION_SOURCE_KEY;
            this.functionSource = (String) parameters.get(FUNCTION_SOURCE_KEY);
            currentKey = PACKAGE_NAME_KEY;
            this.packageName = (String) parameters.get(PACKAGE_NAME_KEY);
        }catch (ClassCastException e){
            //TODO make exception more readable
            throw new IllegalArgumentException("Wrong parameter");
        }
        this.initialized = true;
        }



    @Override
    public String getSource() throws IllegalStateException{
        //TODO nice exception
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        return this.functionSource;
    }

    @Override
    public String getJobSource() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String source = "";
        source += ".flatmap(new " + functionName + "())";
        return source;
    }

    @Override
    public String[] getJobImports() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String imports = "";
        imports += packageName + "." + functionName;
        //TODO: support other imports?
        //TODO add return
        return null;
    }

    @Override
    public boolean verify() {
        //TODO
        if (!this.initialized) return false;
        return true;
    }

    private void cleanUpAfterInit(){
        //TODO remove all items that might have been created during the initialisation process
    }
}
