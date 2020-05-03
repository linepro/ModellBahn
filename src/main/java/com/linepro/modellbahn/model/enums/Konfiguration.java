package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.base.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.KONFIGURATION)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.KONFIGURATION, description = "Configuration methods")
public enum Konfiguration implements DescribedEnum {

    NONE("Kein", "The Decoder cannot be configured."),

    LINK("Lötbrücke", "The Decoder can be configured by link (solder or jumper)."),

    SWITCH("Schalter", "The Decoder can be configured by switches."),

    CV("CV", "The Decoder can be configured by CV values.");

    private final String description;

    private final String tooltip;

    Konfiguration(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @ApiModelProperty(value = "Konfiguration name", example = "CV", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "Konfiguration description", example = "The Decoder can be configured by CV values.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @ApiModelProperty(value = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}
