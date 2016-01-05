package org.tuberlin.de.common.model.interfaces.transorfmation;

import org.tuberlin.de.common.model.interfaces.CompilationUnitComponent;

import java.util.Map;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public interface FlatMapComponent extends TransformationComponent, CompilationUnitComponent {
    void init(Map<String, Object> parameters);
}
