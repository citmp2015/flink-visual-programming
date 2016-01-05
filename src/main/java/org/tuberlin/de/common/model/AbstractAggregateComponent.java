package org.tuberlin.de.common.model;

import java.util.Map;

/**
 * Created by Malcolm-X on 09.12.2015.
 */
public abstract class AbstractAggregateComponent implements AggregateComponent {
    private String packageName;

    public enum FUNCTION_TYPES {SUM};//TODO only sum is supported right now
    public static final String FUNCTION_KEY = "aggregate_function";
    public static final String FIELD_KEY = "aggregate_field";
    public static final String PACKAGE_NAME_KEY = "package_name";
    protected Map<String, Object> parameters;

    private FUNCTION_TYPES functionName;
    private int fieldIndex;
    private boolean initialized = false;


    @Override
    public void init(Map<String, Object> parameters) {
        //neccesary params
        String currentKey = " ";
        if (!parameters.containsKey(FIELD_KEY)
                || !parameters.containsKey(FUNCTION_KEY)) {
            throw new IllegalArgumentException("Missing parameter, please refere to the documentation for a list of neccesarry parameters!");
        }

        try {
            currentKey = FUNCTION_KEY;
            this.functionName = (FUNCTION_TYPES) parameters.get(FUNCTION_KEY);
            currentKey = FIELD_KEY;
            this.fieldIndex = (Integer) parameters.get(FIELD_KEY);
            currentKey = PACKAGE_NAME_KEY;
            this.packageName = (String) parameters.get(PACKAGE_NAME_KEY);

            //currentKey = PACKAGE_NAME_KEY;
            //this.packageName = (String) parameters.get(PACKAGE_NAME_KEY);
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
        //return this.functionSource;
        return null;
    }

    @Override
    public String getJobSource() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String source = "";
        source += "aggregate(" + functionName + ", "+ fieldIndex + ")";
        return source;
    }

    @Override
    public String[] getJobImports() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String imports = "";
        imports += packageName + "." + functionName;
        //TODO: support other imports?
        return imports;
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
