package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Status.
 * The status of an article
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.STATUS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.STATUS, description = "Article status")
public enum Status implements DescribedEnum {
	
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
    @Schema(name = "Article status name", example = "GEKAUFT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(name = "Article status description", example = "Purchased.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(name = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}