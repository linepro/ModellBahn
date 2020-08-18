package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AdressTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ADRESS_TYP)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.ADRESS_TYP, description = "Adress types")
public enum AdressTyp implements DescribedEnum {

    DCC("dcc"),
    
    DCC_SHORT("kurz"),
    
    DELTA("delta"),
    
    MOTOROLA("motorola"),
    
    DIGITAL("digital"),
    
    MAGNETARTIKEL("magnetartikel");

    private final String description;

    private final String tooltip;

    AdressTyp(String description) {
        this.description = "${com.linepro.modellbahn.adressTyp." + description + ".bezeichnung}";
        this.tooltip = "${com.linepro.modellbahn.adressTyp." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "AdressTyp name", example = "DCC", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "AdressTyp description", example = "DCC lang.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "AdressTyp tooltip", example = "0 - 10239.", required = true)
    public String getTooltip() {
        return tooltip;
    }
}
