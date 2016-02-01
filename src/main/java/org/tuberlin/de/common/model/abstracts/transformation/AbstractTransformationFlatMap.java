package org.tuberlin.de.common.model.abstracts.transformation;

import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationFlatMap;
import org.tuberlin.de.common.model.types.RelationTypes;

import java.util.Collection;

/**
 * Created by Malcolm-X on 09.12.2015.
 */
public abstract class AbstractTransformationFlatMap extends AbstractTransformation implements TransformationFlatMap {
    //TODO central store for static strings(file?)
    //private static final String JOB_SOURCE = ".flatmap(new "+ functionName +"  )";
    //private String functionName, functionSource, packageName;
/*
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
        this.stateModel = true;
        }
*/

    @Override
    public Collection<? extends String> getImports() {
//        Collection<String> result = imports;
//        result.add("org.apache.flink.api.common.functions.FlatMapFunction")
        return imports;
    }

    @Override
    public String getSource() throws IllegalStateException{
        //TODO nice exception
        //TODO integrity
        if (!this.isInitialized()) throw new IllegalStateException("Forgot to init!");
        return (String) parameters.get(COMPONENT_SOURCE_JSON);
                //this.functionSource;
    }

    @Override
    public String getJobSource() {
        if (!this.isInitialized()) throw new IllegalStateException("Forgot to init!");
        String source = "";
        source += ".flatMap(new " + parameters.get(FUNCTION_NAME_KEY) + "())";
        return source;
    }

    @Override
    public String[] getJobImports() {
        if (!this.isInitialized()) throw new IllegalStateException("Forgot to init!");
        String imports = "";
        imports += parameters.get(PACKAGE_NAME_KEY) + "." + parameters.get(FUNCTION_NAME_KEY);
        //TODO: support other imports?
        //TODO add return
        return null;
    }

    @Override
    public boolean verify() {
        //Checks whether the component is stateModel and the amount of parents/children is correct
        return      this.isInitialized()
                &&  this.getParents().size() == RelationTypes.ONE.getVal()
                &&  this.getChildren().size() == RelationTypes.ONE.getVal();
    }

    private void cleanUpAfterInit(){
        //TODO remove all items that might have been created during the initialisation process
    }
}
