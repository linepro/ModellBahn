package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

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
        this.description = "com.linepro.modellbahn.adressTyp." + description + ".bezeichnung";
        this.tooltip = "com.linepro.modellbahn.adressTyp." + description + ".tooltip";
    }

    @Override
    @Schema(description = "Adress Type name", example = "DCC", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Adress Type description", example = "DCC lang.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "Adress Type tooltip", example = "0 - 10239.", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return tooltip;
    }
}
