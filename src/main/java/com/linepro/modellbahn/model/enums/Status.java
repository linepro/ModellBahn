package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Status.
 * The status of an article
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.STATUS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.STATUS, description = "Article status")
public enum Status implements IDescribedEnum {
	
    WUNSCHMODEL("Wunschmodel", ""),
	
    GEKAUFT("Gekauft", ""),

    BASTLER("Bastler", ""),
	
    VERKAUFT("Verkauft", "");

    private final String description;

    private final String tooltip;

    Status(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @ApiModelProperty(value = "Article status name", example = "GEKAUFT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "Article status description", example = "Purchased.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @ApiModelProperty(value = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}