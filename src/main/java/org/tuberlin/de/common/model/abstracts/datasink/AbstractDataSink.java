package org.tuberlin.de.common.model.abstracts.datasink;

import org.tuberlin.de.common.model.abstracts.AbstractJobComponent;
import org.tuberlin.de.common.model.interfaces.datasink.DataSink;
import org.tuberlin.de.common.model.types.RelationTypes;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public abstract class AbstractDataSink extends AbstractJobComponent implements DataSink {
    @Override
    public boolean verify() {
        //Checks whether the component is initialized and the amount of parents/children is correct
        return      this.initialized
                &&  this.getChildren().size() == RelationTypes.NONE.getVal();
    }

}
