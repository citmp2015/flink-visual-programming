package org.tuberlin.de.common.model.interfaces.transorfmation;

/**
 * Created by Malcolm-X on 09.12.2015.
 */
public interface AggregateComponent extends TransformationComponent {

    String FUNCTION_KEY = "aggregate_function";
    String FIELD_KEY = "aggregate_field";
    String PACKAGE_NAME_KEY = "package_name";

    public enum FUNCTION_TYPES {SUM}
}
