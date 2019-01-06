package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.keys.UnterKategorieKey;
import com.linepro.modellbahn.model.refs.INamedItemRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IUnterKategorie.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.UNTER_KATEGORIE)
@JsonPropertyOrder({ApiNames.ID, ApiNames.KATEGORIE, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.UNTER_KATEGORIE, description = "Sub category.")
public interface IUnterKategorie extends INamedItem<UnterKategorieKey>, INamedItemRef {

    @JsonGetter(ApiNames.KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(value = "", required = true)
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