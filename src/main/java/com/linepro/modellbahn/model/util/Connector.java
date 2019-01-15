package com.linepro.modellbahn.model.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;

/**
 * Connector. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
@JsonRootName(value = ApiNames.STECKER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.STECKER, description = "Connector types")
public enum Connector implements IDescribedEnum {

    EINGEBAUT("Builin / Hardwired."),

    NEM651("NEM 651 6 pole."),

    NEM652("NEM 652 8 pole."),

    MTC21("MTC21."),

    PluX12("PluX12."),

    PluX16("PluX16."),

    PluX22("PluX22.");

    private final String description;
    
    Connector(String description) {
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
