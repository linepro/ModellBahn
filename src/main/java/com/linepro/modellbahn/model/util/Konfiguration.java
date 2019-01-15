package com.linepro.modellbahn.model.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;

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
public enum Konfiguration implements IDescribedEnum {

    NONE("The Decoder cannot be configured."),

    LINK("The Decoder can be configured by link (solder or jumper)."),

    SWITCH("The Decoder can be configured by switches."),

    CV("The Decoder can be configured by CV values.");

    private final String description;
    
    Konfiguration(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getBezeichnung() {
        return description;
    }
}
