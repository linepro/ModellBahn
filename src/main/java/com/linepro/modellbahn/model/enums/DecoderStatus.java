package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Stecker. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
@JsonRootName(value = ApiNames.STECKER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.STECKER, description = "Decoder statues")
public enum DecoderStatus implements DescribedEnum {

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
    @Schema(name = "Decoder status name", example = "INSTALIERT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(name = "Decoder status description", example = "Instaliert.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(name = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}
