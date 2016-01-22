package org.tuberlin.de.common.model.interfaces.transorfmation;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.CompilationUnitComponent;

/**
 * Created by Malcolm-X on 08.12.2015.
 *
 * Takes one element and produces zero, one, or more elements.
 * data.flatMap(new FlatMapFunction<String, String>() {
 * public void flatMap(String value, Collector<String> out) {
 * for (String s : value.split(" ")) {
 * out.collect(s);
 * }
 * }
 * });
 *
 *
 */
public interface TransformationFlatMap extends Transformation, CompilationUnitComponent {

    public static final String TYPE = Constants.TYPE_TRANSFORMATION_FLAT_MAP;

}
