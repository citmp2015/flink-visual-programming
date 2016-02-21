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
     * source code
     */
    public static final String COMPONENT_SOURCE_JSON = "COMPONENT_SOURCE_JSON";

    /**
     * e.g. LineSplitter in Wordcount
     */
    public static final String FUNCTION_NAME_KEY = "FUNCTION_NAME";
    /**
     *
     */
    public static final String FUNCTION_SOURCE_KEY = "FUNCTION_SOURCE";

    /**
     *
     */
    public static final String PACKAGE_NAME_KEY = "PACKAGE_NAME";

    /**
     *
     * @return Flink sourcecode
     * @throws IllegalStateException
     */
    public String getSource() throws IllegalStateException;

}
