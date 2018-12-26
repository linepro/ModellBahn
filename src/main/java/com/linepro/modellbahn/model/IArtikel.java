package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.Status;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.json.resolver.KupplungResolver;
import com.linepro.modellbahn.rest.json.resolver.LichtResolver;
import com.linepro.modellbahn.rest.json.resolver.MotorTypResolver;
import com.linepro.modellbahn.rest.json.resolver.SteuerungResolver;
import com.linepro.modellbahn.rest.json.resolver.WahrungResolver;
import com.linepro.modellbahn.rest.json.serialization.IProduktRef;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.json.serialization.ProduktSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

/**
 * IArtikel.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ARTIKEL)
@JsonPropertyOrder({ApiNames.ID, ApiNames.PRODUKT, ApiNames.KAUFDATUM, ApiNames.WAHRUNG, ApiNames.PREIS, ApiNames.STUCK, ApiNames.STEUERUNG, ApiNames.MOTOR_TYP, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.DECODER, ApiNames.ANMERKUNG, ApiNames.BELADUNG, ApiNames.ABBILDUNG, ApiNames.STATUS, ApiNames.DELETED, ApiNames.LINKS})
public interface IArtikel extends INamedItem<NameKey> {

    /**
     * Gets the produkt.
     *
     * @return the produkt
     */
    @JsonGetter(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as = IProduktRef.class, using= ProduktSerializer.class)
    @ApiModelProperty(name = ApiNames.PRODUKT, dataType = "com.linepro.modellbahn.rest.json.serialization.IProduktRef")
    IProdukt getProdukt();

    /**
     * Sets the produkt.
     *
     * @param produkt the new produkt
     */
    @JsonSetter(ApiNames.PRODUKT)
    @JsonDeserialize(as= Produkt.class)
    void setProdukt(IProdukt produkt);

    /**
     * Gets the kaufdatum.
     *
     * @return the kaufdatum
     */
    @JsonGetter(ApiNames.KAUFDATUM)
    @JsonView(Views.Public.class)
    @JsonFormat(shape=Shape.STRING, pattern= Formats.ISO8601_DATE)
    Date getKaufdatum();

    /**
     * Sets the kaufdatum.
     *
     * @param kaufdatum the new kaufdatum
     */
    @JsonSetter(ApiNames.KAUFDATUM)
    void setKaufdatum(Date kaufdatum);

    /**
     * Gets the wahrung.
     *
     * @return the wahrung
     */
    @JsonGetter(ApiNames.WAHRUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= WahrungResolver.class)
    IWahrung getWahrung();

    /**
     * Sets the wahrung.
     *
     * @param wahrung the new wahrung
     */
    @JsonSetter(ApiNames.WAHRUNG)
    @JsonDeserialize(as= Wahrung.class)
    void setWahrung(IWahrung wahrung);

    /**
     * Gets the preis.
     *
     * @return the preis
     */
    @JsonGetter(ApiNames.PREIS)
    @JsonView(Views.Public.class)
    BigDecimal getPreis();

    /**
     * Sets the preis.
     *
     * @param preis the new preis
     */
    @JsonSetter(ApiNames.PREIS)
    void setPreis(BigDecimal preis);

    /**
     * Gets the stuck.
     *
     * @return the stuck
     */
    @JsonGetter(ApiNames.STUCK)
    @JsonView(Views.Public.class)
    Integer getStuck();

    /**
     * Sets the stuck.
     *
     * @param stuck the new stuck
     */
    @JsonSetter(ApiNames.STUCK)
    void setStuck(Integer stuck);

    /**
     * Gets the steuerung.
     *
     * @return the steuerung
     */
    @JsonGetter(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= SteuerungResolver.class)
    ISteuerung getSteuerung();

    /**
     * Sets the steuerung.
     *
     * @param steuerung the new steuerung
     */
    @JsonSetter(ApiNames.STEUERUNG)
    @JsonDeserialize(as= Steuerung.class)
    void setSteuerung(ISteuerung steuerung);

    /**
     * Gets the motor typ.
     *
     * @return the motor typ
     */
    @JsonGetter(ApiNames.MOTOR_TYP)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= MotorTypResolver.class)
    IMotorTyp getMotorTyp();

    /**
     * Sets the motor typ.
     *
     * @param motorTyp the new motor typ
     */
    @JsonSetter(ApiNames.MOTOR_TYP)
    @JsonDeserialize(as= MotorTyp.class)
    void setMotorTyp(IMotorTyp motorTyp);

    /**
     * Gets the licht.
     *
     * @return the licht
     */
    @JsonGetter(ApiNames.LICHT)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= LichtResolver.class)
    ILicht getLicht();

    /**
     * Sets the licht.
     *
     * @param licht the new licht
     */
    @JsonSetter(ApiNames.LICHT)
    @JsonDeserialize(as= Licht.class)
    void setLicht(ILicht licht);

    /**
     * Gets the kupplung.
     *
     * @return the kupplung
     */
    @JsonGetter(ApiNames.KUPPLUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= KupplungResolver.class)
    IKupplung getKupplung();

    /**
     * Sets the kupplung.
     *
     * @param kupplung the new kupplung
     */
    @JsonSetter(ApiNames.KUPPLUNG)
    @JsonDeserialize(as= Kupplung.class)
    void setKupplung(IKupplung kupplung);

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

    /**
     * Gets the anmerkung.
     *
     * @return the anmerkung
     */
    @JsonGetter(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    String getAnmerkung();

    /**
     * Sets the anmerkung.
     *
     * @param anmerkung the new anmerkung
     */
    @JsonSetter(ApiNames.ANMERKUNG)
    void setAnmerkung(String anmerkung);

    /**
     * Gets the beladung.
     *
     * @return the beladung
     */
    @JsonGetter(ApiNames.BELADUNG)
    @JsonView(Views.Public.class)
    String getBeladung();

    /**
     * Sets the beladung.
     *
     * @param beladung the new beladung
     */
    @JsonSetter(ApiNames.BELADUNG)
    void setBeladung(String beladung);

    /**
     * Gets the abbildung.
     *
     * @return the abbildung
     */
    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= String.class, using = PathSerializer.class)
    @ApiModelProperty(name = ApiNames.ABBILDUNG, dataType = "String", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Path getAbbildung();

    /**
     * Sets the abbildung.
     *
     * @param abbildung
     *            the new abbildung
     */
    @JsonIgnore
    void setAbbildung(Path abbildung);

    /**
     * Gets the status.
     *
     * @return the status
     */
    @JsonGetter(ApiNames.STATUS)
    @JsonView(Views.DropDown.class)
    Status getStatus();

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    @JsonSetter(ApiNames.STATUS)
    void setStatus(Status status);

}
