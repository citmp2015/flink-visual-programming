package org.tuberlin.de.common.model;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BasePrintDataSinkComponent extends AbstractPrintDataSinkComponent {
    @Override
    public String getJobSource() throws IllegalStateException {
        return jobGraph.getEnvironmentIdentifier();
    }
}
