package org.tuberlin.de.common.model.types;

/**
 * Created by scarvy on 25.01.16.
 */

/**
 * Describes the amount of incoming and outgoing edges of a component
 */
public enum RelationTypes {

    ARBITRARY(-1), NONE(0), ONE(1), TWO(2), MANY(3);

    private final int relationValue;

    private RelationTypes(int val){
          this.relationValue = val;
    }

    public int getVal(){
        return relationValue;
    }
}
