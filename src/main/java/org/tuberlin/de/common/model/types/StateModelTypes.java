package org.tuberlin.de.common.model.types;

/**
 * Created by scarvy on 25.01.16.
 */

/**
 * Describes the amount of incoming and outgoing edges of a component
 */
public enum StateModelTypes {

    UNDEFINED(0), INITIALIZED(1), VERIFIED(2);

    private final int stateValue;

    private StateModelTypes(int val){
          this.stateValue = val;
    }

    public int getVal(){
        return stateValue;
    }
}
