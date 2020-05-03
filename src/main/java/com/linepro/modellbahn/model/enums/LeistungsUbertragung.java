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
@JsonRootName(value = ApiNames.LEISTUNGSUBERTRAGUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.LEISTUNGSUBERTRAGUNG, description = "Drive mechanisms")
public enum LeistungsUbertragung implements DescribedEnum {

    NONE("", ""),
    
    ELEKTRISH("Elektrish", ""),

    HYDRAULISH("Hydraulish", ""),

    MECHANISH("Mechanish", ""),

    PRESSLUFT("Pressluft", "");

    private final String description;

    private final String tooltip;

    LeistungsUbertragung(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @ApiModelProperty(value = "LeistungsUbertragung name", example = "ELEKTRISH", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "LeistungsUbertragung description", example = "Elektrish", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @ApiModelProperty(value = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}
