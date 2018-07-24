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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Artikel.
 * An article. 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Artikel")
@Table(name = "artikelen", indexes = { @Index(columnList = "produkt_id"),
        @Index(columnList = "wahrung_id"),
        @Index(columnList = "steuerung_id"),
        @Index(columnList = "motor_typ_id"),
        @Index(columnList = "licht_id"),
        @Index(columnList = "kupplung_id"),
        @Index(columnList = "decoder_id") })
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
    @JoinColumn(name = "produkt_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk1"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IProdukt getProdukt() {
        return produkt;
    }

    @Override
    public void setProdukt(IProdukt produkt) {
        this.produkt = produkt;
    }

    @Override
    @Column(name = "kaufdatum", nullable = true)
    @Temporal(TemporalType.DATE)
    public Date getKaufdatum() {
        return Kaufdatum;
    }

    @Override
    public void setKaufdatum(Date kaufdatum) {
        Kaufdatum = kaufdatum;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Wahrung.class)
    @JoinColumn(name = "wahrung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk2"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IWahrung getWahrung() {
        return wahrung;
    }

    @Override
    public void setWahrung(IWahrung wahrung) {
        this.wahrung = wahrung;
    }

    @Override
    @Column(name = "preis", nullable = true, precision = 6, scale = 2)
    public BigDecimal getPreis() {
        return preis;
    }

    @Override
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    @Override
    @Column(name = "stuck", nullable = false)
    public Integer getStuck() {
        return stuck;
    }

    @Override
    public void setStuck(Integer stuck) {
        this.stuck = stuck;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = "steuerung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk3"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = "motor_typ_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk4"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IMotorTyp getMotorTyp() {
        return motorTyp;
    }

    @Override
    public void setMotorTyp(IMotorTyp motorTyp) {
        this.motorTyp = motorTyp;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = "licht_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk5"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ILicht getLicht() {
        return licht;
    }

    @Override
    public void setLicht(ILicht licht) {
        this.licht = licht;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = "kupplung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk6"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IKupplung getKupplung() {
        return kupplung;
    }

    @Override
    public void setKupplung(IKupplung kupplung) {
        this.kupplung = kupplung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = "decoder_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "artikel_fk7"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    @Column(name = "anmerkung", length = 100, nullable = true)
    public String getAnmerkung() {
        return anmerkung;
    }

    @Override
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    @Column(name = "beladung", length = 100, nullable = true)
    public String getBeladung() {
        return beladung;
    }

    @Override
    public void setBeladung(String beladung) {
        this.beladung = beladung;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("Produkt", getProdukt())
                .append("kaufdatum", getKaufdatum())
                .append("Wahrung", getWahrung())
                .append("Preis", getPreis())
                .append("stuck", getStuck())
                .append("steuerung", getSteuerung())
                .append("motorTyp", getMotorTyp())
                .append("licht", getLicht())
                .append("kupplung", getKupplung())
                .append("decoder", getDecoder())
                .append("anmerkung", getAnmerkung())
                .append("beladung", getBeladung())
                .append("status", getStatus())
                .toString();
    }
}