package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.keys.DecoderFunktionKey;
import com.linepro.modellbahn.model.refs.IDecoderFunktionRef;
import com.linepro.modellbahn.model.refs.IDecoderRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoderFunktion.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.FUNKTION)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER, ApiNames.FUNKTION,  ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.FUNKTION, description = "Decoder function mapping.")
public interface IDecoderFunktion extends IItem<DecoderFunktionKey>, IDecoderFunktionRef {

    /**
     * Gets the decoder.
     *
     * @return the decoder
     */
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderRef.class)
    @ApiModelProperty(value = "", required = true)
    IDecoder getDecoder();

    /**
     * Sets the decoder.
     *
     * @param decoder the new decoder
     */
    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(as= Decoder.class)
    void setDecoder(IDecoder decoder);

    @JsonSetter(ApiNames.REIHE)
    void setReihe(Integer reihe);

    @JsonSetter(ApiNames.FUNKTION)
    void setFunktionStr(String funktion);

    /**
     * Gets the funktion.
     *
     * @return the funktion
     */
    @JsonIgnore
    IDecoderTypFunktion getFunktion();

    /**
     * Sets the funktion.
     *
     * @param funktion the new funktion
     */
    @JsonIgnore
    void setFunktion(IDecoderTypFunktion funktion);

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    @JsonSetter(ApiNames.BEZEICHNUNG)
    void setBezeichnung(String bezeichnung);

}
