package org.tuberlin.de.common.base;

import org.tuberlin.de.common.model.abstracts.transformation.AbstractCombineComponent;
import org.tuberlin.de.common.model.interfaces.transorfmation.CombineComponent;

import java.util.Collection;

/**
 * Created by Malcolm-X on 23.11.2015.
 */
//TODO
public class BaseCombineComponent extends AbstractCombineComponent implements CombineComponent {



    //TODO implement in higher levels ... prevent errors
    @Override
    public String getSource() throws IllegalStateException {return null;}
    @Override
    public String getJobSource() throws IllegalStateException {return null;}
}
