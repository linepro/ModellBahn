package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Set;
import java.util.TreeSet;

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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
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
import com.linepro.modellbahn.model.IItem;
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
@Entity(name = DBNames.DECODER_TYP)
@Table(name = DBNames.DECODER_TYP, indexes = { @Index(columnList = DBNames.HERSTELLER_ID),
        @Index(columnList = DBNames.PROTOKOLL_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.HERSTELLER_ID, DBNames.NAME }) })
@AttributeOverride(name = DBNames.NAME, column = @Column(name = DBNames.NAME))
public class DecoderTyp extends AbstractNamedItem<DecoderTypKey> implements IDecoderTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8503812316290492490L;

    /** The hersteller. */
    @NotNull
    private IHersteller hersteller;

    /** The i max. */
    //@IMax
    private BigDecimal iMax;

    /** The protokoll. */
    @NotNull
    private IProtokoll protokoll;

    /** The fahrstufe. */
    //@Fahrstufe
    private Integer fahrstufe;

    /** The sound. */
    @NotNull
    private Boolean sound;

    /** The konfiguration. */
    @NotNull
    private Konfiguration konfiguration;

    /** The cv. */
    private Set<IDecoderTypAdress> adressen = new TreeSet<>();

    /** The cv. */
    private Set<IDecoderTypCV> CVs = new TreeSet<>();

    /** The funktion. */
    private Set<IDecoderTypFunktion> funktionen = new TreeSet<>();

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
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk2"))
    public IHersteller getHersteller() {
        return hersteller;
    }

    @Override
    public void setHersteller(IHersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @BusinessKey
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @Column(name = DBNames.I_MAX, precision = 6, scale = 2)
    public BigDecimal getiMax() {
        return iMax;
    }

    @Override
    public void setiMax(BigDecimal iMax) {
        this.iMax = iMax;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk3"))
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    @Override
    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    @Override
    @Column(name = DBNames.FAHRSTUFE)
    public Integer getFahrstufe() {
        return fahrstufe;
    }

    @Override
    public void setFahrstufe(Integer fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    @Override
    @Column(name = DBNames.SOUND, nullable = false)
    public Boolean getSound() {
        return sound;
    }

    @Override
    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.KONFIGURATION, nullable = false, length = 15)
    public Konfiguration getKonfiguration() {
        return konfiguration;
    }

    @Override
    public void setKonfiguration(Konfiguration konfiguration) {
        this.konfiguration = konfiguration;
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypAdress.class, orphanRemoval = true)
    public Set<IDecoderTypAdress> getAdressen() {
        return adressen;
    }

    @Transient
    public Set<IDecoderTypAdress> getSortedAdressen() {
        return new TreeSet<>(getAdressen());
    }

    @Override
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
    public Set<IDecoderTypCV> getCVs() {
        return CVs;
    }

    @Transient
    public Set<IDecoderTypCV> getSortedCVs() {
        return new TreeSet<>(getCVs());
    }

    @Override
    public void setCVs(Set<IDecoderTypCV> CVs) {
        this.CVs = CVs;
    }

    @Override
    public void addCV(IDecoderTypCV cv) {
        cv.setDecoderTyp(this);
        CVs.add(cv);
    }

    @Override
    public void removeCV(IDecoderTypCV cv) {
        CVs.remove(cv);
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypFunktion.class, orphanRemoval = true)
    public Set<IDecoderTypFunktion> getFunktionen() {
        return funktionen;
    }

    @Transient
    public Set<IDecoderTypFunktion> getSortedFunktionen() {
        return new TreeSet<>(getFunktionen());
    }

    @Override
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
    public String getLinkId() {
        return String.format(ApiPaths.DECODER_TYP_LINK, getHersteller().getLinkId(), super.getLinkId());
    }

    @Override
    protected void addChildLinks(URI root, boolean update, boolean delete) {
        addLinks(root, getAdressen(), update, delete);
        addLinks(root, getCVs(), update, delete);
        addLinks(root, getFunktionen(), update, delete);
    }
    
    @Override
    public int compareTo(IItem<?> other) {
        if (other instanceof DecoderTyp) {
            return new CompareToBuilder()
                    .append(getHersteller(), ((DecoderTyp) other).getHersteller())
                    .append(getName(), ((DecoderTyp) other).getName())
                    .toComparison();
        }
        
        return super.compareTo(other);
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
                .append(ApiNames.GERAUSCH, getSound())
                .append(ApiNames.KONFIGURATION, getKonfiguration())
                .append(ApiNames.ADRESSEN, getAdressen())
                .append(ApiNames.CVS, getCVs())
                .append(ApiNames.FUNKTIONEN, getFunktionen())
                .toString();
    }
}