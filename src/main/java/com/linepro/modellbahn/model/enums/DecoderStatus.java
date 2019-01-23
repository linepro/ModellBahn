package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.util.ApiNames;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Stecker. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
@JsonRootName(value = ApiNames.STECKER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.STECKER, description = "Decoder statues")
public enum DecoderStatus implements IDescribedEnum {

    INSTALIERT("Instaliert.",""),

    FREI("Frei.", ""),

    BASTLER("Bastler.", "");

    private final String description;

    private final String tooltip;

    DecoderStatus(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @ApiModelProperty(value = "Decoder status name", example = "INSTALIERT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @ApiModelProperty(value = "Decoder status description", example = "Instaliert.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @ApiModelProperty(value = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}
