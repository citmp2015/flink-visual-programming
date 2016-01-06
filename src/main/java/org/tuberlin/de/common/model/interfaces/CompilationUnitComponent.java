package org.tuberlin.de.common.model.interfaces;

/**
 * @author Marvin Byfield
 * @author Daniel Schröder
 *
 * decides wther a component inherits soucrce code or not
 *
 * e.g. Flatmap has to implement this interface and GroupBy not.
 *
 */
public interface CompilationUnitComponent extends JobComponent {

    /**
     *
     * @return Flink sourcecode
     * @throws IllegalStateException
     */
    public String getSource() throws IllegalStateException;

}
