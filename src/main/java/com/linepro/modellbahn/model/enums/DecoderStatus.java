package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

/**
 * Stecker. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
@JsonRootName(value = ApiNames.STECKER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LINKS })
@Schema(name = ApiNames.STECKER, description = "Decoder statues")
public enum DecoderStatus implements DescribedEnum {

    INSTALIERT("instaliert"),

    FREI("frei"),

    BASTLER("bastler");

    private final String description;

    private final String tooltip;

    DecoderStatus(String description) {
        this.description = "{com.linepro.modellbahn.decoder." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.decoder." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "Decoder status name", example = "INSTALIERT", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Decoder status description", example = "Instaliert.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "Decoder status tooltip", example = "Instaliert.", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return tooltip;
    }
}
