package org.tuberlin.de.common.model.interfaces.transorfmation;

import org.tuberlin.de.common.model.Constants;

/**
 * Created by Malcolm-X on 09.12.2015.
 *
 * Aggregates a group of values into a single value. Aggregation functions can be thought of as built-in reduce functions.
 * Aggregate may be applied on a full data set, or on a grouped data set.
 * Dataset<Tuple3<Integer, String, Double>> input = // [...]
 * DataSet<Tuple3<Integer, String, Double>> output = input.aggregate(SUM, 0).and(MIN, 2);
 * You can also use short-hand syntax for minimum, maximum, and sum aggregations.
 *
 * Dataset<Tuple3<Integer, String, Double>> input = // [...]
 * DataSet<Tuple3<Integer, String, Double>> output = input.sum(0).andMin(2);
 *
 */
public interface TransformationAggregate extends Transformation {

    public static final String TYPE = Constants.TYPE_TRANSFORMATION_AGGREGATE;

    String FUNCTION_KEY = "aggregate_function";
    String FIELD_KEY = "aggregate_field";
    String PACKAGE_NAME_KEY = "package_name";

    public enum FUNCTION_TYPES {SUM}
}
