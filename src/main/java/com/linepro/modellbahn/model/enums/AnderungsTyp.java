package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ChangeTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ANDERUNGS_TYP)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.ANDERUNGS_TYP, description = "Change types")
public enum AnderungsTyp implements DescribedEnum {

    AUSGETAUSCHT("ausgetauscht"),
    
    ERSATZTEILE("ersatzteile"),
    
    REPARIERT("repariert"), 
    
    UMGEBAUT("umgebaut"),
    
    VERKAUFT("verkauft"),
    
    VERLOREN("verloren"),
    
    VERSCHROTTET("verschrottet");

    private final String description;

    private final String tooltip;

    AnderungsTyp(String description) {
        this.description = "${com.linepro.modellbahn.anderungsTyp." + description + ".bezeichnung}";
        this.tooltip = "${com.linepro.modellbahn.anderungsTyp." + description + ".tooltip}";
    }

    @Override
    @Schema(name = "Change type", example = "UMGEBAUT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(name = "Change type description", example = "umgebaut", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(name = "Change type tooltip")
    public String getTooltip() {
        return tooltip;
    }
}
