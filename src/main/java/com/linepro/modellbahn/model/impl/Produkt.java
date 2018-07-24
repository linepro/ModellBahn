package com.linepro.modellbahn.model.impl;

import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.util.FileConverter;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Produkt.
 * A product.
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Produkt")
@Table(name = "produkt", indexes = { @Index(columnList = "hersteller_id,bestellnr", unique = true),
        @Index(columnList = "epoch_id"),
        @Index(columnList = "gattung_id"),
        @Index(columnList = "bahnverwaltung_id"),
        @Index(columnList = "achsfolg_id"),
        @Index(columnList = "massstab_id"),
        @Index(columnList = "spurweite_id"),
        @Index(columnList = "unter_kategorie_id"),
        @Index(columnList = "sondermodel_id"),
        @Index(columnList = "aufbau_id"),
        @Index(columnList = "licht_id"),
        @Index(columnList = "kupplung_id"),
        @Index(columnList = "vorbild_id"),
        @Index(columnList = "steuerung_id"),
        @Index(columnList = "decoder_typ_id"),
        @Index(columnList = "motor_typ_id"),
        @Index(columnList = "hersteller_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "hersteller_id", "bestellnr" }) })
public class Produkt extends AbstractItem implements IProdukt {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8098838727023710484L;

    /** The hersteller. */
    private IHersteller hersteller;

    /** The bestell nr. */
    private String bestellNr;

    /** The unter kategorie. */
    private IUnterKategorie unterKategorie;

    /** The massstab. */
    private IMassstab massstab;

    /** The spurweite. */
    private ISpurweite spurweite;

    /** The bahnverwaltung. */
    private IBahnverwaltung bahnverwaltung;

    /** The gattung. */
    private IGattung gattung;

    /** The epoch. */
    private IEpoch epoch;

    /** The achsfolge. */
    private IAchsfolg achsfolge;

    /** The Sondermodel. */
    private ISonderModell sondermodel;

    /** The aufbau. */
    private IAufbau aufbau;

    /** The licht. */
    private ILicht licht;

    /** The kupplung. */
    private IKupplung kupplung;

    /** The vorbild. */
    private IVorbild vorbild;

    /** The steuerung. */
    private ISteuerung steuerung;

    /** The decoder typ. */
    private IDecoderTyp decoderTyp;

    /** The motor typ. */
    private IMotorTyp motorTyp;

    /** The anmerkung. */
    private String anmerkung;

    /** The betreibsnummer. */
    private String betreibsnummer;

    /** The bauzeit. */
    private Date bauzeit;

    /** The anleitungen. */
    private File anleitungen;

    /** The explosionszeichnung. */
    private File explosionszeichnung;

    /** The lange. */
    private BigDecimal lange;

    /** The abbildung. */
    private File abbildung;

    /** The teilen. */
    private List<IProduktTeil> teilen = new ArrayList<>();

    /**
     * Instantiates a new produkt.
     */
    public Produkt() {
        super();
    }

    /**
     * Instantiates a new produkt.
     *
     * @param id the id
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param abbildung the abbildung
     * @param deleted the deleted
     */
    public Produkt(Long id, String name, String bezeichnung, Image abbildung, Boolean deleted) {
        super(id, deleted);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Epoch.class)
    @JoinColumn(name = "epoch_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk1"))
    @JsonGetter("epoch")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IEpoch getEpoch() {
        return epoch;
    }

    @Override
    @JsonSetter("epoch")
    public void setEpoch(IEpoch epoch) {
        this.epoch = epoch;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = "gattung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk2"))
    @JsonGetter("gattung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IGattung getGattung() {
        return gattung;
    }

    @Override
    @JsonSetter("gattung")
    public void setGattung(IGattung gattung) {
        this.gattung = gattung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class)
    @JoinColumn(name = "bahnverwaltung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk3"))
    @JsonGetter("bahnverwaltung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IBahnverwaltung getBahnverwaltung() {
        return bahnverwaltung;
    }

    @Override
    @JsonSetter("bahnverwaltung")
    public void setBahnverwaltung(IBahnverwaltung bahnverwaltung) {
        this.bahnverwaltung = bahnverwaltung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = "achsfolg_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk4"))
    @JsonGetter("achsfolge")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IAchsfolg getAchsfolge() {
        return achsfolge;
    }

    @Override
    @JsonSetter("achsfolge")
    public void setAchsfolge(IAchsfolg achsfolge) {
        this.achsfolge = achsfolge;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Massstab.class)
    @JoinColumn(name = "massstab_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk5"))
    @JsonGetter("massstab")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IMassstab getMassstab() {
        return massstab;
    }

    @Override
    @JsonSetter("massstab")
    public void setMassstab(IMassstab massstab) {
        this.massstab = massstab;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Spurweite.class)
    @JoinColumn(name = "spurweite_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk6"))
    @JsonGetter("spurweite")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ISpurweite getSpurweite() {
        return spurweite;
    }

    @Override
    @JsonSetter("spurweite")
    public void setSpurweite(ISpurweite spurweite) {
        this.spurweite = spurweite;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = "unter_kategorie_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk7"))
    @JsonGetter("unterKategorie")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IUnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    @Override
    @JsonSetter("unterKategorie")
    public void setUnterKategorie(IUnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SonderModell.class)
    @JoinColumn(name = "sondermodel_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk8"))
    @JsonGetter("sondermodel")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ISonderModell getSondermodel() {
        return sondermodel;
    }

    @Override
    @JsonSetter("sondermodel")
    public void setSondermodel(ISonderModell sondermodel) {
        this.sondermodel = sondermodel;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Aufbau.class)
    @JoinColumn(name = "aufbau_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk9"))
    @JsonGetter("aufbau")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IAufbau getAufbau() {
        return aufbau;
    }

    @Override
    @JsonSetter("aufbau")
    public void setAufbau(IAufbau aufbau) {
        this.aufbau = aufbau;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = "licht_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk10"))
    @JsonGetter("licht")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ILicht getLicht() {
        return licht;
    }

    @Override
    @JsonSetter("licht")
    public void setLicht(ILicht licht) {
        this.licht = licht;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = "kupplung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk11"))
    @JsonGetter("kupplung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IKupplung getKupplung() {
        return kupplung;
    }

    @Override
    @JsonSetter("kupplung")
    public void setKupplung(IKupplung kupplung) {
        this.kupplung = kupplung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Vorbild.class)
    @JoinColumn(name = "vorbild_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk12"))
    @JsonGetter("vorbild")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IVorbild getVorbild() {
        return vorbild;
    }

    @Override
    @JsonSetter("vorbild")
    public void setVorbild(IVorbild vorbild) {
        this.vorbild = vorbild;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = "steuerung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk13"))
    @JsonGetter("steuerung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    @JsonSetter("steuerung")
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk14"))
    @JsonGetter("decoderTyp")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    @Override
    @JsonSetter("decoderTyp")
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = "motor_typ_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk15"))
    @JsonGetter("motorTyp")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IMotorTyp getMotorTyp() {
        return motorTyp;
    }

    @Override
    @JsonSetter("motorTyp")
    public void setMotorTyp(IMotorTyp motorTyp) {
        this.motorTyp = motorTyp;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class)
    @JoinColumn(name = "hersteller_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_fk16"))
    @JsonGetter("hersteller")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IHersteller getHersteller() {
        return hersteller;
    }

    @Override
    @JsonSetter("hersteller")
    public void setHersteller(IHersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @Column(name = "bestellnr", length = 30, nullable = true)
    @JsonGetter("bestellNr")
    public String getBestellNr() {
        return bestellNr;
    }

    @Override
    @JsonSetter("bestellNr")
    public void setBestellNr(String bestellNr) {
        this.bestellNr = bestellNr;
    }

    @Override
    @Column(name = "anmerkung", nullable = true, length = 100)
    @JsonGetter("anmerkung")
    public String getAnmerkung() {
        return anmerkung;
    }

    @Override
    @JsonSetter("anmerkung")
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    @Column(name = "betreibsnummer", nullable = true, length = 100)
    @JsonGetter("betreibsnummer")
    public String getBetreibsnummer() {
        return betreibsnummer;
    }

    @Override
    @JsonSetter("betreibsnummer")
    public void setBetreibsnummer(String betreibsnummer) {
        this.betreibsnummer = betreibsnummer;
    }

    @Override
    @Column(name = "bauzeit", nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter("bauzeit")
    public Date getBauzeit() {
        return bauzeit;
    }

    @Override
    @JsonSetter("bauzeit")
    public void setBauzeit(Date bauzeit) {
        this.bauzeit = bauzeit;
    }

    @Override
    @Column(name = "anleitungen", nullable = true, length = 512)
    @JsonGetter("anleitungen")
    public File getAnleitungen() {
        return anleitungen;
    }

    @Override
    @JsonSetter("anleitungen")
    public void setAnleitungen(File anleitungen) {
        this.anleitungen = anleitungen;
    }

    @Override
    @Column(name = "explosionszeichnung", nullable = true, length = 512)
    @JsonGetter("explosionszeichnung")
    public File getExplosionszeichnung() {
        return explosionszeichnung;
    }

    @Override
    @JsonSetter("explosionszeichnung")
    public void setExplosionszeichnung(File explosionszeichnung) {
        this.explosionszeichnung = explosionszeichnung;
    }

    @Override
    @Column(name = "lange", nullable = true, precision = 6, scale = 2)
    @JsonGetter("lange")
    public BigDecimal getLange() {
        return lange;
    }

    @Override
    @JsonSetter("lange")
    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    @Override
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Column(name = "abbildung", nullable = true)
    @Convert(converter = FileConverter.class)
    @JsonGetter("abbildung")
    public File getAbbildung() {
        return abbildung;
    }

    @Override
    @JsonSetter("abbildung")
    public void setAbbildung(File abbildung) {
        this.abbildung = abbildung;
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "produkt", targetEntity = ProduktTeil.class, orphanRemoval = true)
    @JsonGetter("teilen")
    @JsonSerialize()
    public List<IProduktTeil> getTeilen() {
        return teilen;
    }

    @Override
    @JsonGetter("teilen")
    public void setTeilen(List<IProduktTeil> teilen) {
        this.teilen = teilen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("hersteller", getHersteller())
                .append("bestellNr", getBestellNr())
                .append("betreibsnummer", getBetreibsnummer())
                .append("unterKategorie", getUnterKategorie())
                .append("epoch", getEpoch())
                .append("gattung", getGattung())
                .append("bahnverwaltung", getBahnverwaltung())
                .append("massstab", getMassstab())
                .append("spurweite", getSpurweite())
                .append("vorbild", getVorbild())
                .append("achsfolge", getAchsfolge())
                .append("sondermodel", getSondermodel())
                .append("aufbau", getAufbau())
                .append("licht", getLicht())
                .append("kupplung", getKupplung())
                .append("steuerung", getSteuerung())
                .append("decoderTyp", getDecoderTyp())
                .append("motorTyp", getMotorTyp())
                .append("anmerkung", getAnmerkung())
                .append("bauzeit", getBauzeit())
                .append("anleitungen", getAnleitungen())
                .append("explosionszeichnung", getExplosionszeichnung())
                .append("lange", getLange())
                .append("teilen", getTeilen())
                .toString();
    }
}