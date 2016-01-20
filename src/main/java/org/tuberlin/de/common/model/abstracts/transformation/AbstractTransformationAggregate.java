package org.tuberlin.de.common.model.abstracts.transformation;

import org.tuberlin.de.common.model.interfaces.transorfmation.TransformationAggregate;

/**
 * Created by Malcolm-X on 09.12.2015.
 */
public abstract class AbstractTransformationAggregate extends AbstractTransformation implements TransformationAggregate {
    private String packageName;

    ;//TODO only sum is supported right now
  /*  protected Map<String, Object> parameters;*/

/*    private FUNCTION_TYPES functionName;
    private int fieldIndex;
    private boolean initialized = false;*/


 /*   @Override
    public void init(JobGraph jobGraph, Map<String, Object> parameters) throws  IllegalArgumentException{
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
    }*/


    @Override
    public String getJobSource() {
        if (!this.initialized) throw new IllegalStateException("Forgot to init!");
        String source = "";
        source += ".aggregate(Aggregations." + parameters.get(TransformationAggregate.FUNCTION_KEY) + ", "+ parameters.get(TransformationAggregate.FIELD_KEY) + ")";
        return source;
    }


}
