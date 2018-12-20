package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.keys.UnterKategorieKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.KategorieResolver;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * IUnterKategorie.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.UNTER_KATEGORIE)
@JsonPropertyOrder({ApiNames.ID, ApiNames.KATEGORIE, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS})
public interface IUnterKategorie extends INamedItem<UnterKategorieKey> {

    /**
     * Gets the kategorie.
     *
     * @return the kategorie
     */
    @JsonGetter(ApiNames.KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver = KategorieResolver.class)
    IKategorie getKategorie();

    /**
     * Sets the kategorie.
     *
     * @param kategorie the new kategorie
     */
    @JsonSetter(ApiNames.KATEGORIE)
    @JsonDeserialize(as= Kategorie.class)
    void setKategorie(IKategorie kategorie);
}