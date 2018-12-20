/*
 * IKategorie
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.IUnterKategorieRef;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * IKategorie.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.KATEGORIE)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.UNTER_KATEGORIEN,
        ApiNames.LINKS })
public interface IKategorie extends INamedItem<NameKey> {
    
    /**
     * Gets the unter kategorien.
     *
     * @return the unter kategorien
     */
    @JsonGetter(ApiNames.UNTER_KATEGORIEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentAs = IUnterKategorieRef.class, contentUsing = UnterKategorieSerializer.class)
    Set<IUnterKategorie> getUnterKategorien();

    /**
     * Sets the unter kategorie.
     *
     * @param unterKategorien the new unter kategorie
     */
    @JsonSetter(ApiNames.UNTER_KATEGORIEN)
    @JsonDeserialize(contentAs = UnterKategorie.class)
    void setUnterKategorien(Set<IUnterKategorie> unterKategorien);

    void addUnterKategorie(IUnterKategorie unterKategorie);

    void removeUnterKategorie(IUnterKategorie unterKategorie);
}