package com.linepro.modellbahn.model;
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
@JsonPropertyOrder({ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DECIMALS, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.WAHRUNG, description = "A currency.")
public interface IWahrung extends IWahrungRef {

    @JsonSetter(ApiNames.DECIMALS)
    void setDecimals(Integer decimals);
}