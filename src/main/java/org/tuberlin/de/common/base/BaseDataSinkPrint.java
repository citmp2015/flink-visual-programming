package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.datasink.print.AbstractPrintDataSink;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;

import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSinkPrint extends AbstractPrintDataSink implements DataSink {

    public BaseDataSinkPrint(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }

}
