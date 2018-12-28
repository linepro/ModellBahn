package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.model.impl.DecoderFunktion;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.ProtokollResolver;
import com.linepro.modellbahn.rest.json.serialization.DecoderAdressSerializer;
import com.linepro.modellbahn.rest.json.serialization.DecoderCVSerializer;
import com.linepro.modellbahn.rest.json.serialization.DecoderFunktionSerializer;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * IDecoder.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.DECODER)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER_ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.PROTOKOLL, ApiNames.FAHRSTUFE, ApiNames.ADRESSEN, ApiNames.DELETED, ApiNames.CVS, ApiNames.FUNKTIONEN, ApiNames.LINKS })
@ApiModel(value = ApiNames.DECODER, description = "Decoder - installed or spare.")
public interface IDecoder extends INamedItem<NameKey> {

    @JsonGetter(ApiNames.DECODER_ID)
    @JsonView(Views.DropDown.class)
    String getName();

    @JsonSetter(ApiNames.DECODER_ID)
    void setName(String  name);

    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using= DecoderTypSerializer.class)
    @ApiModelProperty(name = ApiNames.DECODER_TYP, dataType = "com.linepro.modellbahn.rest.json.serialization.IDecoderTypRef")
    IDecoderTyp getDecoderTyp();

    /**
     * Sets the decoder typ.
     *
     * @param decoderTyp the new decoder typ
     */
    @JsonSetter(ApiNames.DECODER_TYP)
    @JsonDeserialize(as= DecoderTyp.class)
    void setDecoderTyp(IDecoderTyp decoderTyp );

    /**
     * Gets the protokoll.
     *
     * @return the protokoll
     */
    @JsonGetter(ApiNames.PROTOKOLL)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= ProtokollResolver.class)
    IProtokoll getProtokoll();

    /**
     * Sets the protokoll.
     *
     * @param protokoll the new protokoll
     */
    @JsonSetter(ApiNames.PROTOKOLL)
    @JsonDeserialize(as= Protokoll.class)
    void setProtokoll(IProtokoll protokoll);

    /**
     * Gets the fahrstufe.
     *
     * @return the fahrstufe
     */
    @JsonGetter(ApiNames.FAHRSTUFE)
    @JsonView(Views.Public.class)
    Integer getFahrstufe();

    /**
     * Sets the fahrstufe.
     *
     * @param fahrstufe the new fahrstufe
     */
    @JsonSetter(ApiNames.FAHRSTUFE)
    void setFahrstufe(Integer fahrstufe);

    /**
     * Gets the adressen.
     *
     * @return the adressen
     */
    @JsonGetter(ApiNames.ADRESSEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing= DecoderAdressSerializer.class)
    @ApiModelProperty(name = ApiNames.ADRESSEN, dataType = "[Lcom.linepro.modellbahn.rest.json.serialization.IDecoderAdressRef;", accessMode = AccessMode.READ_ONLY)
    Set<IDecoderAdress> getAdressen();

    /**
     * Sets the adressen.
     *
     * @param adressen the new adressen
     */
    @JsonSetter(ApiNames.ADRESSEN)
    @JsonDeserialize(contentAs= DecoderAdress.class)
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
    @JsonSerialize(contentUsing= DecoderCVSerializer.class)
    @ApiModelProperty(name = ApiNames.CVS, dataType = "[Lcom.linepro.modellbahn.rest.json.serialization.IDecoderCVRef;", accessMode = AccessMode.READ_ONLY)
    Set<IDecoderCV> getCVs();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    @JsonSetter(ApiNames.CVS)
    @JsonDeserialize(contentAs= DecoderCV.class)
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
    @JsonSerialize(contentUsing= DecoderFunktionSerializer.class)
    @ApiModelProperty(name = ApiNames.FUNKTIONEN, dataType = "[Lcom.linepro.modellbahn.rest.json.serialization.IDecoderFunktionRef;", accessMode = AccessMode.READ_ONLY)
    Set<IDecoderFunktion> getFunktionen();

    /**
     * Sets the funktionen.
     *
     * @param funktionen the new funktionen
     */
    @JsonSetter(ApiNames.FUNKTIONEN)
    @JsonDeserialize(contentAs= DecoderFunktion.class)
    void setFunktionen(Set<IDecoderFunktion> funktionen);

    void addFunktion(IDecoderFunktion cv);

    void removeFunktion(IDecoderFunktion cv);
}