/*
 * IKategorie
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.refs.IKategorieRef;
import com.linepro.modellbahn.model.refs.IUnterKategorieRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IKategorie.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.KATEGORIE)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.UNTER_KATEGORIEN,
        ApiNames.LINKS })
@ApiModel(value = ApiNames.KATEGORIE, description = "Category.")
public interface IKategorie extends INamedItem, IKategorieRef {
    
    @JsonGetter(ApiNames.UNTER_KATEGORIEN)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentAs = IUnterKategorieRef.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.refs.IUnterKategorieRef;", value = "Sub categories", access = "READ_ONLY", required = true)
    Set<IUnterKategorie> getSortedUnterKategorien();

    @JsonIgnore
    Set<IUnterKategorie> getUnterKategorien();
    
    @JsonIgnore
    void setUnterKategorien(Set<IUnterKategorie> unterKategorien);

    void addUnterKategorie(IUnterKategorie unterKategorie);

    void removeUnterKategorie(IUnterKategorie unterKategorie);
}