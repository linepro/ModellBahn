package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.model.util.Status;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.ProduktSerializer;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Artikel.
 * An article. 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Artikel")
@Table(name = "Artikel", indexes = { @Index(columnList = "produkt_id"),
        @Index(columnList = "wahrung_id"),
        @Index(columnList = "steuerung_id"),
        @Index(columnList = "motor_typ_id"),
        @Index(columnList = "licht_id"),
        @Index(columnList = "kupplung_id"),
        @Index(columnList = "decoder_id") })
@JsonRootName(value = ApiNames.ARTIKEL)
@JsonPropertyOrder({ApiNames.ID, ApiNames.PRODUKT, ApiNames.KAUFDATUM, ApiNames.WAHRUNG, ApiNames.PREIS, ApiNames.STUCK, ApiNames.STEUERUNG, ApiNames.MOTOR_TYP, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.DECODER, ApiNames.ANMERKUNG, ApiNames.BELADUNG, ApiNames.STATUS, ApiNames.DELETED, ApiNames.LINKS})
public class Artikel extends AbstractNamedItem implements IArtikel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8652624782179487496L;

    /** The produkt. */
    private IProdukt produkt;

    /** The Kaufdatum. */
    private Date Kaufdatum;

    /** The wahrung. */
    private IWahrung wahrung;

    /** The preis. */
    private BigDecimal preis;

    /** The stuck. */
    private Integer stuck;

    /** The steuerung. */
    private ISteuerung steuerung;

    /** The motor typ. */
    private IMotorTyp motorTyp;

    /** The licht. */
    private ILicht licht;

    /** The kupplung. */
    private IKupplung kupplung;

    /** The decoder. */
    private IDecoder decoder;

    /** The anmerkung. */
    private String anmerkung;

    /** The beladung. */
    private String beladung;

    /** The status. */
    private Status status;

    /**
     * Instantiates a new artikel.
     */
    public Artikel() {
        super();
    }

    /**
     * Instantiates a new artikel.
     *
     * @param id the id
     * @param produkt the produkt
     * @param kaufdatum the kaufdatum
     * @param wahrung the wahrung
     * @param preis the preis
     * @param stuck the stuck
     * @param steuerung the steuerung
     * @param motorTyp the motor typ
     * @param licht the licht
     * @param kupplung the kupplung
     * @param decoder the decoder
     * @param artikelNr the artikel nr
     * @param beschreibung the beschreibung
     * @param anmerkung the anmerkung
     * @param beladung the beladung
     * @param status the status
     * @param deleted the deleted
     */
    public Artikel(Long id, IProdukt produkt, Date kaufdatum, IWahrung wahrung, BigDecimal preis, Integer stuck,
            ISteuerung steuerung, IMotorTyp motorTyp, ILicht licht, IKupplung kupplung, IDecoder decoder,
            String artikelNr, String beschreibung, String anmerkung,
            String beladung, Status status, Boolean deleted) {
        super(id, artikelNr, beschreibung, deleted);

        setProdukt(produkt);
        setKaufdatum(kaufdatum);
        setWahrung(wahrung);
        setPreis(preis);
        setStuck(stuck);
        setSteuerung(steuerung);
        setMotorTyp(motorTyp);
        setLicht(licht);
        setKupplung(kupplung);
        setDecoder(decoder);
        setAnmerkung(anmerkung);
        setBeladung(beladung);
        setStatus(status);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = "produkt_id", nullable = false, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk1"))
    @JsonGetter(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(contentUsing=ProduktSerializer.class)
    public IProdukt getProdukt() {
        return produkt;
    }

    @Override
    @JsonSetter(ApiNames.PRODUKT)
    public void setProdukt(IProdukt produkt) {
        this.produkt = produkt;
    }

    @Override
    @Column(name = ApiNames.KAUFDATUM, nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter(ApiNames.KAUFDATUM)
    @JsonView(Views.Public.class)
    @JsonFormat(shape=Shape.STRING, pattern=Formats.ISO8601_DATE)
    public Date getKaufdatum() {
        return Kaufdatum;
    }

    @Override
    @JsonSetter(ApiNames.KAUFDATUM)
    public void setKaufdatum(Date kaufdatum) {
        Kaufdatum = kaufdatum;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Wahrung.class)
    @JoinColumn(name = "wahrung_id", nullable = true, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk2"))
    @JsonGetter(ApiNames.WAHRUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME)
    public IWahrung getWahrung() {
        return wahrung;
    }

    @Override
    @JsonSetter(ApiNames.WAHRUNG)
    public void setWahrung(IWahrung wahrung) {
        this.wahrung = wahrung;
    }

    @Override
    @Column(name = ApiNames.PREIS, nullable = true, precision = 6, scale = 2)
    @JsonGetter(ApiNames.PREIS)
    @JsonView(Views.Public.class)
    public BigDecimal getPreis() {
        return preis;
    }

    @Override
    @JsonSetter(ApiNames.PREIS)
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    @Override
    @Column(name = ApiNames.STUCK, nullable = false)
    @JsonGetter(ApiNames.STUCK)
    @JsonView(Views.Public.class)
    public Integer getStuck() {
        return stuck;
    }

    @Override
    @JsonSetter(ApiNames.STUCK)
    public void setStuck(Integer stuck) {
        this.stuck = stuck;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = "steuerung_id", nullable = true, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk3"))
    @JsonGetter(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME)
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    @JsonSetter(ApiNames.STEUERUNG)
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = "motor_typ_id", nullable = true, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk4"))
    @JsonGetter(ApiNames.MOTOR_TYP)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME)
    public IMotorTyp getMotorTyp() {
        return motorTyp;
    }

    @Override
    @JsonSetter(ApiNames.MOTOR_TYP)
    public void setMotorTyp(IMotorTyp motorTyp) {
        this.motorTyp = motorTyp;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = "licht_id", nullable = true, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk5"))
    @JsonGetter(ApiNames.LICHT)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME)
    public ILicht getLicht() {
        return licht;
    }

    @Override
    @JsonSetter(ApiNames.LICHT)
    public void setLicht(ILicht licht) {
        this.licht = licht;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = "kupplung_id", nullable = true, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk6"))
    @JsonGetter(ApiNames.KUPPLUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME)
    public IKupplung getKupplung() {
        return kupplung;
    }

    @Override
    @JsonSetter(ApiNames.KUPPLUNG)
    public void setKupplung(IKupplung kupplung) {
        this.kupplung = kupplung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = "decoder_id", nullable = true, referencedColumnName = ApiNames.ID, foreignKey = @ForeignKey(name = "artikel_fk7"))
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.ID)
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    @JsonSetter(ApiNames.DECODER)
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    @Column(name = ApiNames.ANMERKUNG, length = 100, nullable = true)
    @JsonGetter(ApiNames.ANMERKUNG)
    @JsonView(Views.DropDown.class)
    public String getAnmerkung() {
        return anmerkung;
    }

    @Override
    @JsonSetter(ApiNames.ANMERKUNG)
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    @Column(name = ApiNames.BELADUNG, length = 100, nullable = true)
    @JsonGetter(ApiNames.BELADUNG)
    @JsonView(Views.Public.class)
    public String getBeladung() {
        return beladung;
    }

    @Override
    @JsonSetter(ApiNames.BELADUNG)
    public void setBeladung(String beladung) {
        this.beladung = beladung;
    }

    @Override
    @Column(name = ApiNames.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonGetter(ApiNames.STATUS)
    @JsonView(Views.DropDown.class)
    public Status getStatus() {
        return status;
    }

    @Override
    @JsonSetter(ApiNames.STATUS)
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append(ApiNames.PRODUKT, getProdukt())
                .append(ApiNames.KAUFDATUM, getKaufdatum())
                .append(ApiNames.WAHRUNG, getWahrung())
                .append(ApiNames.PREIS, getPreis())
                .append(ApiNames.STUCK, getStuck())
                .append(ApiNames.STEUERUNG, getSteuerung())
                .append(ApiNames.MOTOR_TYP, getMotorTyp())
                .append(ApiNames.LICHT, getLicht())
                .append(ApiNames.KUPPLUNG, getKupplung())
                .append(ApiNames.DECODER, getDecoder())
                .append(ApiNames.ANMERKUNG, getAnmerkung())
                .append(ApiNames.BELADUNG, getBeladung())
                .append(ApiNames.STATUS, getStatus())
                .toString();
    }
}