package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.refs.IDecoderTypAdressRef;
import com.linepro.modellbahn.model.refs.IDecoderTypRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoderTypAdress.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ADRESS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER_TYP,  ApiNames.INDEX,  ApiNames.ADRESS_TYP,  ApiNames.SPAN,  ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.ADRESS, description = "Decoder type address - template for Decoder.")
public interface IDecoderTypAdress extends IItem, IDecoderTypAdressRef {

    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IDecoderTypRef", value = "Decoder type", required = true)
    IDecoderTyp getDecoderTyp();

    /**
     * Sets the decoder typ.
     *
     * @param decoderTyp the new decoder typ
     */
    @JsonIgnore
    void setDecoderTyp(IDecoderTyp decoderTyp);

    /**
     * Sets the index.
     *
     * @param index the new index
     */
    @JsonSetter(ApiNames.INDEX)
    void setIndex(Integer index);

    /**
     * Sets the adressTyp.
     *
     * @param adressTyp the new adressTyp
     */
    @JsonSetter(ApiNames.ADRESS_TYP)
    void setAdressTyp(AdressTyp adressTyp);

    /**
     * Sets the span.
     *
     * @param span the new span
     */
    @JsonSetter(ApiNames.SPAN)
    void setSpan(Integer span);

    /**
     * Sets the werkseinstellung.
     *
     * @param werkseinstellung the new werkseinstellung
     */
    @JsonSetter(ApiNames.WERKSEINSTELLUNG)
    void setAdress(Integer werkseinstellung);
}