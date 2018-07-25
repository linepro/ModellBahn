package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.model.util.Konfiguration;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.DecoderTypCVSerializer;
import com.linepro.modellbahn.rest.json.DecoderTypFunktionSerializer;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTyp. Represents a Decoder type (manufacturer : part numer)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderTyp")
@Table(name = "decoder_typ", indexes = { @Index(columnList = "hersteller_id"),
        @Index(columnList = "protokoll_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "hersteller_id", "name" }) })
@AttributeOverride(name = "name", column = @Column(name = "name"))
@JsonRootName(value = "decoderType")
@JsonPropertyOrder({"id", "hersteller", "bestellNr", "protokoll", "adressTyp", "adressen", "fahrstufe", "sound", "iMax", "konfiguration", "deleted", "cvs", "funktionen", "links"})
public class DecoderTyp extends AbstractNamedItem implements IDecoderTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8503812316290492490L;

    /** The typ. */
    private AdressTyp adressTyp;

    /** The hersteller. */
    private IHersteller hersteller;

    /** The adressen. */
    private Integer adressen;

    /** The i max. */
    private BigDecimal iMax;

    /** The protokoll. */
    private IProtokoll protokoll;

    /** The fahrstufe. */
    private Integer fahrstufe;

    /** The sound. */
    private Boolean sound;

    /** The konfiguration. */
    private Konfiguration konfiguration;

    /** The cv. */
    private Set<IDecoderTypCV> CVs = new HashSet<IDecoderTypCV>();

    /** The funktion. */
    private Set<IDecoderTypFunktion> funktionen = new HashSet<IDecoderTypFunktion>();

    /**
     * Instantiates a new decoder typ.
     */
    public DecoderTyp() {
        super();
    }

    /**
     * Convienience method for lookups
     * 
     * @param hersteller
     * @param name
     */
    public DecoderTyp(IHersteller hersteller, String name) {
        super(name);

        setHersteller(hersteller);
    }

    /**
     * Instantiates a new decoder typ.
     *
     * @param id
     *            the id
     * @param hersteller
     *            the hersteller
     * @param protokoll
     *            the protokoll
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param adressen
     *            the adressen
     * @param sound
     *            the sound
     * @param konfiguration
     *            the konfiguration
     * @param deleted
     *            the deleted
     */
    public DecoderTyp(Long id, IHersteller hersteller, IProtokoll protokoll, String name, String bezeichnung,
            Integer adressen, Boolean sound, Konfiguration konfiguration, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setHersteller(hersteller);
        setProtokoll(protokoll);
        setAdressen(adressen);
        setSound(sound);
        setKonfiguration(konfiguration);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class)
    @JoinColumn(name = "hersteller_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fk2"))
    @JsonGetter("hersteller")
    @JsonView(Views.DropDown.class)
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
    @JsonGetter("bestellNr")
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonSetter("bestellNr")
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @Column(name = "adressen", nullable = false)
    @JsonGetter("adressen")
    @JsonView(Views.Public.class)
    public Integer getAdressen() {
        return adressen;
    }

    @Override
    @JsonSetter("adressen")
    public void setAdressen(Integer adressen) {
        this.adressen = adressen;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = "adress_typ", length = 15, nullable = true)
    @JsonGetter("adressTyp")
    @JsonView(Views.Public.class)
    public AdressTyp getAdressTyp() {
        return adressTyp;
    }

    @Override
    @JsonSetter("adressTyp")
    public void setAdressTyp(AdressTyp typ) {
        this.adressTyp = typ;
    }

    @Override
    @Column(name = "i_max", nullable = true, precision = 6, scale = 2)
    @JsonGetter("iMax")
    @JsonView(Views.Public.class)
    public BigDecimal getiMax() {
        return iMax;
    }

    @Override
    @JsonSetter("iMax")
    public void setiMax(BigDecimal iMax) {
        this.iMax = iMax;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = "protokoll_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fk3"))
    @JsonGetter("protokoll")
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    @Override
    @JsonSetter("protokoll")
    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    @Override
    @Column(name = "fahrstufe", nullable = true)
    @JsonGetter("fahrstufe")
    @JsonView(Views.Public.class)
    public Integer getFahrstufe() {
        return fahrstufe;
    }

    @Override
    @JsonView(Views.Public.class)
    @JsonSetter("fahrstufe")
    public void setFahrstufe(Integer fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    @Override
    @Column(name = "sound", nullable = false)
    @JsonGetter("sound")
    @JsonView(Views.DropDown.class)
    public Boolean getSound() {
        return sound;
    }

    @Override
    @JsonSetter("sound")
    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = "konfiguration", nullable = false, length = 15)
    @JsonGetter("konfiguration")
    @JsonView(Views.DropDown.class)
    public Konfiguration getKonfiguration() {
        return konfiguration;
    }

    @Override
    @JsonSetter("konfiguration")
    public void setKonfiguration(Konfiguration konfiguration) {
        this.konfiguration = konfiguration;
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decoderTyp", targetEntity = DecoderTypCV.class, orphanRemoval = true)
    @JsonGetter("cvs")
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = DecoderTypCVSerializer.class)
    public Set<IDecoderTypCV> getCVs() {
        return CVs;
    }

    @Override
    @JsonSetter("cvs")
    public void setCVs(Set<IDecoderTypCV> CVs) {
        this.CVs = CVs;
    }

    @Override
    public void addCV(IDecoderTypCV cv) {
        cv.setDecoderTyp(this);
        getCVs().add(cv);
    }

    @Override
    public void removeCV(IDecoderTypCV cv) {
        getCVs().remove(cv);
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decoderTyp", targetEntity = DecoderTypFunktion.class, orphanRemoval = true)
    @JsonGetter("funktionen")
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = DecoderTypFunktionSerializer.class)
    public Set<IDecoderTypFunktion> getFunktionen() {
        return funktionen;
    }

    @Override
    @JsonSetter("funktionen")
    @JsonSerialize()
    public void setFunktionen(Set<IDecoderTypFunktion> funktionen) {
        this.funktionen = funktionen;
    }

    @Override
    public void addFunktion(IDecoderTypFunktion funktion) {
        funktion.setDecoderTyp(this);
        getFunktionen().add(funktion);
    }

    @Override
    public void removeFunktion(IDecoderTypFunktion funktion) {
        getFunktionen().remove(funktion);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("hersteller", getHersteller())
                .append("protokoll", getProtokoll())
                .append("iMax", getiMax())
                .append("adresTyp", getAdressTyp())
                .append("fahrstufe", getFahrstufe())
                .append("sound", getSound())
                .append("konfiguration", getKonfiguration())
                .append("adressen", getAdressen())
                .append("cvs", getCVs())
                .append("funktionen", getFunktionen())
                .toString();
    }
}