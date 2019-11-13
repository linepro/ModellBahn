package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.linepro.modellbahn.model.refs.IWahrungRef;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;

/**
 * IWahrung.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.WAHRUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DEZIMAL, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.WAHRUNG, description = "A currency.")
public interface IWahrung extends INamedItem, IWahrungRef {

    @JsonSetter(ApiNames.NAMEN)
    void setName(String name);

    @JsonSetter(ApiNames.BEZEICHNUNG)
    void setBezeichnung(String bezeichnung);

    @JsonSetter(ApiNames.DEZIMAL)
    void setDecimals(Integer dezimal);
}