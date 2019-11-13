package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.refs.IDecoderAdressRef;
import com.linepro.modellbahn.model.refs.IDecoderRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DecoderDeserializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoderAdress.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ADRESS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.INDEX, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.DELETED, ApiNames.LINKS })
@ApiModel(value = ApiNames.ADRESS, description = "Decoder address setting.")
public interface IDecoderAdress extends IItem, IDecoderAdressRef {

    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IDecoderRef", value = "Parent decoder", required = true)
    IDecoder getDecoder();

    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(using= DecoderDeserializer.class)
    void setDecoder(IDecoder decoder);

    @JsonSetter(ApiNames.INDEX)
    void setIndex(Integer index);
    
    @JsonSetter(ApiNames.ADRESS_TYP)
    void setAdressTyp(AdressTyp typ);

    @JsonSetter(ApiNames.ADRESS)
    void setAdress(Integer adress);
}