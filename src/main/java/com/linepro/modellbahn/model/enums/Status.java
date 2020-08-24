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
	
    WUNSCHMODEL("wunschmodel"),
	
    GEKAUFT("gekauft"),

    BASTELN("basteln"),
	
    VERKAUFT("verkauft");

    private final String description;

    private final String tooltip;

    Status(String description) {
        this.description = "{com.linepro.modellbahn.status." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.status." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "Article status name", example = "GEKAUFT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Article status description", example = "Purchased.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}