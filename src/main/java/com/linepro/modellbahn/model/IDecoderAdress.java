package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.keys.DecoderAdressKey;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoderAdress.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ADRESS)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.INDEX, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.DELETED, ApiNames.LINKS })
@ApiModel(value = ApiNames.ADRESS, description = "Decoder address setting.")
public interface IDecoderAdress extends IItem<DecoderAdressKey>, IAdress {

    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= DecoderResolver.class)
    @ApiModelProperty(dataType = "String", value = "The Decoder", required = true)
    IDecoder getDecoder();

    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(as= Decoder.class)
    void setDecoder(IDecoder decoder);

    @JsonGetter(ApiNames.INDEX)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The 0 based address index (always 0 for single address decoders)", required = true)
    Integer getIndex();

    @JsonSetter(ApiNames.INDEX)
    void setIndex(Integer index);
    
    @JsonSetter(ApiNames.ADRESS_TYP)
    void setAdressTyp(AdressTyp typ);

    @JsonSetter(ApiNames.ADRESS)
    void setAdress(Integer adress);
}