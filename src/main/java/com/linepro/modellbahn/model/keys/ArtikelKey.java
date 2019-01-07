package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class ArtikelKey extends BaseKey {

    private final String artikelId;

    public ArtikelKey(final String artikelId) {
        this.artikelId = artikelId;
    }

    private String getArtikelId() {
        return artikelId;
    }
    
    @Override
    public void addCriteria(Query query) {
        query.setParameter(ApiNames.ARTIKEL_ID, getArtikelId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.ARTIKEL_ID, getArtikelId())
                .toString();
    }
}
