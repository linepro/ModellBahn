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
@JsonRootName(value = ApiNames.LEISTUNGSUBERTRAGUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.LEISTUNGSUBERTRAGUNG, description = "Drive mechanisms")
public enum LeistungsUbertragung implements IDescribedEnum {

    NONE(""),
    
    ELEKTRISH("Elektrish"),

    HYDRAULISH("Hydraulish"),

    MECHANISH("Mechanish"),

    PRESSLUFT("Pressluft")    ;

    private final String description;
    
    LeistungsUbertragung(String description) {
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
