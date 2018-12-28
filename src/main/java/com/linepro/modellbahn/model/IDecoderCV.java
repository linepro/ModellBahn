package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.keys.DecoderCVKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;

/**
 * IDecoderCV.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.CV)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.CV, ApiNames.WERT, ApiNames.DELETED, ApiNames.LINKS })
@ApiModel(value = ApiNames.CV, description = "Decoder CV setting.")
public interface IDecoderCV extends IItem<DecoderCVKey> {

    /**
     * Gets the decoder.
     *
     * @return the decoder
     */
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= DecoderResolver.class)
    IDecoder getDecoder();

    /**
     * Sets the decoder.
     *
     * @param decoder the new decoder
     */
    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(as= Decoder.class)
    void setDecoder(IDecoder decoder);

    /**
     * Gets the cv.
     *
     * @return the cv
     */
    @JsonIgnore
    IDecoderTypCV getCv();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    @JsonIgnore
    void setCv(IDecoderTypCV cv);

    @JsonGetter(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    Integer getCvValue();

    @JsonSetter(ApiNames.CV)
    void setCvValue(Integer cv);

    /**
     * Gets the wert.
     *
     * @return the wert
     */
    @JsonGetter(ApiNames.WERT)
    @JsonView(Views.DropDown.class)
    Integer getWert();

    /**
     * Sets the wert.
     *
     * @param wert the new wert
     */
    @JsonSetter(ApiNames.WERT)
    void setWert(Integer wert);
}