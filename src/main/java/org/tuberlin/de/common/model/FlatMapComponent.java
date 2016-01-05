package org.tuberlin.de.common.model;

import java.util.Map;

/**
 * Created by Malcolm-X on 08.12.2015.
 */
public interface FlatMapComponent extends JobComponent {
    void init(Map<String, Object> parameters);
}
