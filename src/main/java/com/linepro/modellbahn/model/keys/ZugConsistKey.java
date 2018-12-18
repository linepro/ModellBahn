package com.linepro.modellbahn.model.keys;

import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

import javax.persistence.Query;

public class ZugConsistKey extends BaseKey {

    private final IZug zug;

    private final Integer position;

    public ZugConsistKey(final IZug zug, final Integer position) {
        this.zug = zug;
        this.position = position;
    }

    private IZug getZug() {
        return zug;
    }

    private Integer getPosition() {
        return position;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.ZUG, getZug());
        query.setParameter(DBNames.POSITION, getPosition());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.ZUG, getZug())
                .append(ApiNames.POSITION, getPosition())
                .toString();
    }
}
