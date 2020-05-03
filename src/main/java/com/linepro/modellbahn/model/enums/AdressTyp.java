package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.base.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * AdressTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ADRESS_TYP)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.ADRESS_TYP, description = "Adress types")
public enum AdressTyp implements DescribedEnum {

    DCC("DCC lang", "0 - 10239."),
    
    DCC_SHORT("DCC kurz", "1 - 27."),
    
    DELTA("Märklin DELTA", "(2,6,8,18,20,24,26,54,56,60,62,72,74,78,80."),
    
    MM("Märklin Motorola", "1 - 80."),
    
    DIGITAL("Märklin Digital", "(fx/mfx) 1 - 255."),
    
    WEICHE("Märklin Magnetartikel", "1 - 256.");

    private final String description;

    private final String tooltip;

    AdressTyp(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @ApiModelProperty(value = "AdressTyp name", example = "DCC", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "AdressTyp description", example = "DCC lang.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @ApiModelProperty(value = "AdressTyp tooltip", example = "0 - 10239.", required = true)
    public String getTooltip() {
        return tooltip;
    }
}
