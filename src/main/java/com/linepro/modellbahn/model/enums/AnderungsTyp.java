package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ChangeTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ANDERUNGS_TYP)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.ANDERUNGS_TYP, description = "Change types")
public enum AnderungsTyp implements IDescribedEnum {

    AUSGETAUSCHT("ausgetauscht", ""),
    
    ERSATZTEILE("Ersatzteile", ""),
    
    REPARIERT("repariert", ""),
    
    UMGEBAUT("umgebaut", ""),
    
    VERKAUFT("verkauft", ""),
    
    VERLOREN("verloren", ""),
    
    VERSCHROTTET("verschrottet", "");

    private final String description;

    private final String tooltip;

    AnderungsTyp(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @ApiModelProperty(value = "ChangeTyp name", example = "UMGEBAUT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "ChangeTyp description", example = "umgebaut", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @ApiModelProperty(value = "ChangeTyp tooltip")
    public String getTooltip() {
        return tooltip;
    }
}
