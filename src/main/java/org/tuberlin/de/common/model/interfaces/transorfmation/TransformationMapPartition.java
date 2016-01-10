package org.tuberlin.de.common.model.interfaces.transorfmation;

import org.tuberlin.de.common.model.interfaces.CompilationUnitComponent;

/**
 * Created by oxid on 1/10/16.
 *
 * Transforms a parallel partition in a single function call. The function get the partition as an `Iterable` stream and can produce an arbitrary number of result values. The number of elements in each partition depends on the degree-of-parallelism and previous operations.
 *
 * data.mapPartition(new MapPartitionFunction<String, Long>() {
 * public void mapPartition(Iterable<String> values, Collector<Long> out) {
 * long c = 0;
 * for (String s : values) {
 * c++;
 * }
 * out.collect(c);
 * }
 * });
 *
 *
 */
public interface TransformationMapPartition extends Transformation, CompilationUnitComponent{

}
