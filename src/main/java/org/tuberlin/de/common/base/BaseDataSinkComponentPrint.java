package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.datasink.print.AbstractPrintDataSinkComponent;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.common.model.interfaces.datasink.DataSinkComponent;

import java.util.Map;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public class BaseDataSinkComponentPrint extends AbstractPrintDataSinkComponent implements DataSinkComponent {

    public BaseDataSinkComponentPrint(JobGraph jobGraph, Map<String, Object> parameters){
        super();
        init(jobGraph, parameters);
    }

}
