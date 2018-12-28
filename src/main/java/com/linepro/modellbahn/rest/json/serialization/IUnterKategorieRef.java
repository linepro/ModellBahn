package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IUnterKategorieRef extends ILinkRef {

    @JsonGetter(ApiNames.KATEGORIE)
    String getKategorie();

    @JsonGetter(ApiNames.NAMEN)
    String getNamen();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    String getBezeichnung();
}
