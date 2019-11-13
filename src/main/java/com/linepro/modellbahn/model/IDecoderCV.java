package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.refs.IDecoderCVRef;
import com.linepro.modellbahn.model.refs.IDecoderRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoderCV.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.CV)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.CV, ApiNames.WERT, ApiNames.DELETED, ApiNames.LINKS })
@ApiModel(value = ApiNames.CV, description = "Decoder CV setting.")
public interface IDecoderCV extends IItem, IDecoderCVRef {

    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IDecoderRef", value = "Parent decoder", required = true)
    IDecoder getDecoder();

    @JsonIgnore
    void setDecoder(IDecoder decoder);

    @JsonIgnore
    void setCv(IDecoderTypCV cv);

    @JsonSetter(ApiNames.WERT)
    void setWert(Integer wert);
}