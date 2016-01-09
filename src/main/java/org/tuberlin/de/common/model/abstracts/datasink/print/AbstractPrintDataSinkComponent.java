package org.tuberlin.de.common.model.abstracts.datasink.print;

import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.abstracts.datasink.AbstractDataSinkComponent;
import org.tuberlin.de.common.model.interfaces.datasink.DataSinkComponent;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public abstract class AbstractPrintDataSinkComponent extends AbstractDataSinkComponent implements DataSinkComponent {

    @Override
    public String getJobSource() {
        if (!initialized) throw new IllegalStateException("Must be initialized");
        //TODO: Integrity checks
        String result = jobGraph.getEnvironmentIdentifier() + ".print()";
        result += ";";
        return result;
    }

}
