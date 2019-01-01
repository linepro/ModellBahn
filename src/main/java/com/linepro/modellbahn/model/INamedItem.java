/*
 * INamedItem
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

/**
 * INamedItem.
 * @author   $Author$
 * @version  $Id$
 */
@JsonPropertyOrder({ApiNames.ID,ApiNames.NAMEN,ApiNames.BEZEICHNUNG,ApiNames.DELETED, ApiNames.LINKS})
public interface INamedItem<K extends IKey> extends IItem<K> {

    /**
     * Gets the name.
     *
     * @return the name
     */
    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    String getName();

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    @JsonSetter(ApiNames.NAMEN)
    void setName(String name);

    /**
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "")
    String getBezeichnung();

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    @JsonSetter(ApiNames.BEZEICHNUNG)
    void setBezeichnung(String bezeichnung);
}