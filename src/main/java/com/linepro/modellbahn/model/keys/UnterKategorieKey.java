package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class UnterKategorieKey extends BaseKey {

    protected final IKategorie kategorie;
    
    protected final String unterKategorie;

    public UnterKategorieKey(final IKategorie kategorie, final String unterKategorie) {
        this.kategorie = kategorie;
        this.unterKategorie = unterKategorie;
    }

    public IKategorie getKategorie() {
        return kategorie;
    }

    public String getUnterKategorie() {
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
                .append(ApiNames.NAME, getUnterKategorie())
                .toString();
    }
}
