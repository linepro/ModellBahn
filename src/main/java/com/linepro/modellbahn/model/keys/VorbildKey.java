package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class VorbildKey extends BaseKey {

    private final IGattung gattung;
    
    public VorbildKey(final IGattung gattung) {
        this.gattung = gattung;
   }

    private IGattung getGattung() {
        return gattung;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.GATTUNG, getGattung());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.GATTUNG, getGattung())
                .toString();
    }
}
