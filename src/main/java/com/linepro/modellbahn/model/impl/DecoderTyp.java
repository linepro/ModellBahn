package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.net.URI;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.keys.DecoderTypKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.model.util.Konfiguration;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.HerstellerResolver;
import com.linepro.modellbahn.rest.json.resolver.ProtokollResolver;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypAdressSerializer;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypCVSerializer;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypFunktionSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTyp. Represents a Decoder type (manufacturer : part numer)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderTyp")
@Table(name = "DecoderTyp", indexes = { @Index(columnList = DBNames.HERSTELLER_ID),
        @Index(columnList = DBNames.PROTOKOLL_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.HERSTELLER_ID, DBNames.NAME }) })
@AttributeOverride(name = DBNames.NAME, column = @Column(name = DBNames.NAME))
@JsonRootName(value = ApiNames.DECODER_TYP)
@JsonPropertyOrder({ApiNames.ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.PROTOKOLL, ApiNames.FAHRSTUFE, ApiNames.SOUND, ApiNames.I_MAX, ApiNames.KONFIGURATION, ApiNames.DELETED, ApiNames.ADRESSEN, ApiNames.CVS, ApiNames.FUNKTIONEN, ApiNames.LINKS})
public class DecoderTyp extends AbstractNamedItem<DecoderTypKey> implements IDecoderTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8503812316290492490L;

    /** The hersteller. */
    private IHersteller hersteller;

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
    private Set<IDecoderTypAdress> adressen = new HashSet<IDecoderTypAdress>();

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

    public DecoderTyp(Long id, IHersteller hersteller, IProtokoll protokoll, String name, String bezeichnung,
            Boolean sound, Konfiguration konfiguration, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setHersteller(hersteller);
        setProtokoll(protokoll);
        setSound(sound);
        setKonfiguration(konfiguration);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "decoder_typ_fk2"))
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=HerstellerResolver.class)
    public IHersteller getHersteller() {
        return hersteller;
    }

    @Override
    @JsonSetter(ApiNames.HERSTELLER)
    @JsonDeserialize(as=Hersteller.class)
    public void setHersteller(IHersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @BusinessKey
    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonSetter(ApiNames.BESTELL_NR)
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @Column(name = DBNames.I_MAX, nullable = true, precision = 6, scale = 2)
    @JsonGetter(ApiNames.I_MAX)
    @JsonView(Views.Public.class)
    public BigDecimal getiMax() {
        return iMax;
    }

    @Override
    @JsonSetter(ApiNames.I_MAX)
    public void setiMax(BigDecimal iMax) {
        this.iMax = iMax;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "decoder_typ_fk3"))
    @JsonGetter(ApiNames.PROTOKOLL)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=ProtokollResolver.class)
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    @Override
    @JsonSetter(ApiNames.PROTOKOLL)
    @JsonDeserialize(as=Protokoll.class)
    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    @Override
    @Column(name = DBNames.FAHRSTUFE, nullable = true)
    @JsonGetter(ApiNames.FAHRSTUFE)
    @JsonView(Views.Public.class)
    public Integer getFahrstufe() {
        return fahrstufe;
    }

    @Override
    @JsonView(Views.Public.class)
    @JsonSetter(ApiNames.FAHRSTUFE)
    public void setFahrstufe(Integer fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    @Override
    @Column(name = DBNames.SOUND, nullable = false)
    @JsonGetter(ApiNames.SOUND)
    @JsonView(Views.DropDown.class)
    public Boolean getSound() {
        return sound;
    }

    @Override
    @JsonSetter(ApiNames.SOUND)
    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.KONFIGURATION, nullable = false, length = 15)
    @JsonGetter(ApiNames.KONFIGURATION)
    @JsonView(Views.DropDown.class)
    public Konfiguration getKonfiguration() {
        return konfiguration;
    }

    @Override
    @JsonSetter(ApiNames.KONFIGURATION)
    public void setKonfiguration(Konfiguration konfiguration) {
        this.konfiguration = konfiguration;
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypAdress.class, orphanRemoval = true)
    @JsonGetter(ApiNames.ADRESSEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = DecoderTypAdressSerializer.class)
    public Set<IDecoderTypAdress> getAdressen() {
        return adressen;
    }

    @Override
    @JsonSetter(ApiNames.ADRESSEN)
    @JsonDeserialize(contentAs = DecoderTypAdress.class)
    public void setAdressen(Set<IDecoderTypAdress> adressen) {
        this.adressen = adressen;
    }

    @Override
    public void addAdress(IDecoderTypAdress adress) {
        adress.setDecoderTyp(this);
        getAdressen().add(adress);
    }

    @Override
    public void removeAdress(IDecoderTypAdress adress) {
        getAdressen().remove(adress);
    }


    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypCV.class, orphanRemoval = true)
    @JsonGetter(ApiNames.CVS)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = DecoderTypCVSerializer.class)
    public Set<IDecoderTypCV> getCVs() {
        return CVs;
    }

    @Override
    @JsonSetter(ApiNames.CVS)
    @JsonDeserialize(contentAs = DecoderTypCV.class)
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypFunktion.class, orphanRemoval = true)
    @JsonGetter(ApiNames.FUNKTIONEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = DecoderTypFunktionSerializer.class)
    public Set<IDecoderTypFunktion> getFunktionen() {
        return funktionen;
    }

    @Override
    @JsonSetter(ApiNames.FUNKTIONEN)
    @JsonDeserialize(contentAs = DecoderTypFunktion.class)
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
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.DECODER_TYP_LINK, getHersteller().getLinkId(), getName());
    }

    @Override
    protected void addChildLinks(URI root) {
        addLinks(root, getCVs(), false, false);
        addLinks(root, getFunktionen(), false, false);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getHersteller())
                .append(getName())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof DecoderTyp)) {
            return false;
        }

        DecoderTyp other = (DecoderTyp) obj;
        
        return new EqualsBuilder()
                .append(getHersteller(), other.getHersteller())
                .append(getName(), other.getName())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.HERSTELLER, getHersteller())
                .append(ApiNames.PROTOKOLL, getProtokoll())
                .append(ApiNames.I_MAX, getiMax())
                .append(ApiNames.FAHRSTUFE, getFahrstufe())
                .append(ApiNames.SOUND, getSound())
                .append(ApiNames.KONFIGURATION, getKonfiguration())
                .append(ApiNames.ADRESSEN, getAdressen())
                .append(ApiNames.CVS, getCVs())
                .append(ApiNames.FUNKTIONEN, getFunktionen())
                .toString();
    }
}