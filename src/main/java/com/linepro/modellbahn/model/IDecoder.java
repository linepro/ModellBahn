package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.refs.IDecoderAdressRef;
import com.linepro.modellbahn.model.refs.IDecoderCVRef;
import com.linepro.modellbahn.model.refs.IDecoderFunktionRef;
import com.linepro.modellbahn.model.refs.IDecoderRef;
import com.linepro.modellbahn.model.refs.IProtokollRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypDeserializer;
import com.linepro.modellbahn.rest.json.serialization.ProtokollDeserializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IDecoder.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.DECODER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER_ID, ApiNames.DECODER_TYP, ApiNames.BEZEICHNUNG, ApiNames.PROTOKOLL, ApiNames.FAHRSTUFE, ApiNames.DELETED, ApiNames.ADRESSEN, ApiNames.CVS, ApiNames.FUNKTIONEN, ApiNames.LINKS })
@ApiModel(value = ApiNames.DECODER, description = "Decoder - installed or spare.")
public interface IDecoder extends IItem, IDecoderRef {

    @JsonSetter(ApiNames.DECODER_ID)
    void setDecoderId(String  name);

    @JsonSetter(ApiNames.DECODER_TYP)
    @JsonDeserialize(using= DecoderTypDeserializer.class)
    void setDecoderTyp(IDecoderTyp decoderTyp );

    void setBezeichnung(String bezeichnung);

    @JsonGetter(ApiNames.PROTOKOLL)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IProtokollRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IProtokollRef", value = "Decoder protocol", required = true)
    IProtokoll getProtokoll();

    @JsonSetter(ApiNames.PROTOKOLL)
    @JsonDeserialize(using= ProtokollDeserializer.class)
    void setProtokoll(IProtokoll protokoll);

    @JsonGetter(ApiNames.FAHRSTUFE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Decoder speed steps", example="27", required = true)
    Integer getFahrstufe();

    @JsonSetter(ApiNames.FAHRSTUFE)
    void setFahrstufe(Integer fahrstufe);

    @JsonGetter(ApiNames.ADRESSEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentAs= IDecoderAdressRef.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.refs.IDecoderAdressRef;", value = "Decoder addresses", access = "READ_ONLY", required = true)
    Set<IDecoderAdress> getSortedAdressen();

    @JsonIgnore
    void setAdressen(Set<IDecoderAdress> adressen);

    void addAdress(IDecoderAdress adress);

    void removeAdress(IDecoderAdress adress);

    /**
     * Gets the cv.
     *
     * @return the cv
     */
    @JsonGetter(ApiNames.CVS)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentAs= IDecoderCVRef.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.refs.IDecoderCVRef;", value = "Decoder cv values", access = "READ_ONLY")
    Set<IDecoderCV> getSortedCVs();

    @JsonIgnore
    void setCVs(Set<IDecoderCV> cv);

    void addCV(IDecoderCV cv);

    void removeCV(IDecoderCV cv);

    /**
     * Gets the funktionen.
     *
     * @return the funktionen
     */
    @JsonGetter(ApiNames.FUNKTIONEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentAs= IDecoderFunktionRef.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.refs.IDecoderFunktionRef;", value = "Decoder functions", access = "READ_ONLY", required = true)
    Set<IDecoderFunktion> getSortedFunktionen();

    /**
     * Sets the funktionen.
     *
     * @param funktionen the new funktionen
     */
    @JsonIgnore
    void setFunktionen(Set<IDecoderFunktion> funktionen);

    void addFunktion(IDecoderFunktion cv);

    void removeFunktion(IDecoderFunktion cv);

    @JsonIgnore
    Set<IDecoderAdress> getAdressen();

    @JsonIgnore
    Set<IDecoderCV> getCVs();

    @JsonIgnore
    Set<IDecoderFunktion> getFunktionen();
}