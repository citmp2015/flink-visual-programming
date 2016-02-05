package org.tuberlin.de.common.model.abstracts.datasource;

import org.tuberlin.de.common.model.abstracts.AbstractJobComponent;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.datasources.DataSource;
import org.tuberlin.de.common.model.types.RelationTypes;

/**
 * Created by Malcolm-X on 26.12.2015.
 */
public abstract class AbstractDataSourceComponent extends AbstractJobComponent implements DataSource {
    @Override
    public String getTypeDeclaration() throws IllegalStateException {
        return "DataSet<" + parameters.get(JobComponent.OUTPUT_TYPE) + ">";
    }

    @Override
    public boolean verify() {
        //Checks whether the component is stateModel and the amount of parents/children is correct
        return      this.isInitialized()
                &&  this.getParents().size() == RelationTypes.NONE.getVal();

    }
}
