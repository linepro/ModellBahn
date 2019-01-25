package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class AnderungKey extends BaseKey {

    private final IArtikel artikel;
    
    private final Integer anderungsId;

    public AnderungKey(final IArtikel aritkel, final Integer anderungsId) {
        this.artikel = aritkel;
        this.anderungsId = anderungsId;
    }

    private IArtikel getArtikel() {
        return artikel;
    }

    private Integer getAnderungsId() {
        return anderungsId;
    }
    
    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.ARTIKEL, getArtikel());
        query.setParameter(DBNames.ANDERUNG_ID, getAnderungsId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.ARTIKEL, getArtikel())
                .append(ApiNames.ANDERUNG_ID, getAnderungsId())
                .toString();
    }
}
