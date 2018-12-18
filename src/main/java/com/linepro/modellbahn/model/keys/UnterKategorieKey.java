package com.linepro.modellbahn.model.keys;

import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

import javax.persistence.Query;

public class UnterKategorieKey extends BaseKey {

    private final IKategorie kategorie;
    
    private final String unterKategorie;

    public UnterKategorieKey(final IKategorie kategorie, final String unterKategorie) {
        this.kategorie = kategorie;
        this.unterKategorie = unterKategorie;
    }

    private IKategorie getKategorie() {
        return kategorie;
    }

    private String getUnterKategorie() {
        return unterKategorie;
    }
    
    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.KATEGORIE, getKategorie());
        query.setParameter(DBNames.NAME, getUnterKategorie());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.KATEGORIE, getKategorie())
                .append(ApiNames.NAMEN, getUnterKategorie())
                .toString();
    }
}
