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
import com.linepro.modellbahn.model.keys.DecoderFunktionKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;

/**
 * IDecoderFunktion.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.FUNKTION)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER, ApiNames.FUNKTION,  ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.FUNKTION, description = "Decoder function mapping.")
public interface IDecoderFunktion extends IItem<DecoderFunktionKey> {

    /**
     * Gets the decoder.
     *
     * @return the decoder
     */
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.ID, resolver= DecoderResolver.class)
    IDecoder getDecoder();

    /**
     * Sets the decoder.
     *
     * @param decoder the new decoder
     */
    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(as= Decoder.class)
    void setDecoder(IDecoder decoder);

    @JsonGetter(ApiNames.REIHE)
    @JsonView(Views.DropDown.class)
    Integer getReihe();

    @JsonSetter(ApiNames.REIHE)
    void setReihe(Integer reihe);

    @JsonGetter(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    String getFunktionStr();

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
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    String getBezeichnung();

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    @JsonSetter(ApiNames.BEZEICHNUNG)
    void setBezeichnung(String bezeichnung);

}
