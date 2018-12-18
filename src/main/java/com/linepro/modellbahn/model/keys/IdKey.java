package com.linepro.modellbahn.model.keys;

import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

import javax.persistence.Query;

public class IdKey extends BaseKey {

    private final Long id;

    public IdKey(final Long id) {
        this.id = id;
    }

    private Long getId() {
        return id;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.ID, getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.ID, getId())
                .toString();
    }
}
