package org.tuberlin.de.common.model.abstracts.datasink.print;

import org.tuberlin.de.common.model.abstracts.datasink.AbstractDataSink;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public abstract class AbstractPrintDataSink extends AbstractDataSink implements DataSink {

    @Override
    public String getJobSource() {
        if (!isInitialized()) throw new IllegalStateException("Must be stateModel");
        //TODO: Integrity checks
        String result = ".print();";
        return result;
    }

}
