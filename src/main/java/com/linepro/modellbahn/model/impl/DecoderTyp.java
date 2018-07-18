package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IAdressTyp;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "decoder_typ", indexes = { @Index(columnList = "adress_typ_id"), @Index(columnList = "hersteller_id"),
        @Index(columnList = "protokoll_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "hersteller_id", "name" }) })
@AttributeOverride(name = "name", column = @Column(name = "name"))
public class DecoderTyp extends AbstractNamedItem implements IDecoderTyp {

    private static final long serialVersionUID = 8503812316290492490L;

    private IAdressTyp typ;

    private IHersteller hersteller;

    private Integer adressen;

    private BigDecimal iMax;

    private IProtokoll protokoll;

    private Integer fahrstufe;

    private Boolean sound;

    private Set<IDecoderTypCV> cv = new HashSet<IDecoderTypCV>();

    private Set<IDecoderTypFunktion> funktion = new HashSet<IDecoderTypFunktion>();

    public DecoderTyp() {
        super();
    }

    public DecoderTyp(Long id, IHersteller hersteller, IProtokoll protokoll, String name, String bezeichnung,
            Integer adressen, Boolean sound, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setHersteller(hersteller);
        setProtokoll(protokoll);
        setAdressen(adressen);
        setSound(sound);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AdressTyp.class)
    @JoinColumn(name = "adress_typ_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fk1"))
    @JsonBackReference
    public IAdressTyp getTyp() {
        return typ;
    }

    @Override
    public void setTyp(IAdressTyp typ) {
        this.typ = typ;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class)
    @JoinColumn(name = "hersteller_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fk2"))
    @JsonProperty("hersteller")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IHersteller getHersteller() {
        return hersteller;
    }

    @Override
    public void setHersteller(IHersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @Column(name = "adressen", nullable = false, columnDefinition = "SMALLINT DEFAULT 1 NOT NULL")
    @Min(1)
    public Integer getAdressen() {
        return adressen;
    }

    @Override
    public void setAdressen(Integer adressen) {
        this.adressen = adressen;
    }

    @Override
    @Column(name = "i_max", nullable = true, precision = 6, scale = 3)
    public BigDecimal getiMax() {
        return iMax;
    }

    @Override
    public void setiMax(BigDecimal iMax) {
        this.iMax = iMax;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = "protokoll_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fk3"))
    @JsonProperty("protokoll")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    @Override
    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    @Override
    @Column(name = "fahrstufe", nullable = true)
    @Min(1)
    public Integer getFahrstufe() {
        return fahrstufe;
    }

    @Override
    public void setFahrstufe(Integer fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    @Override
    @Column(name = "sound", nullable = false)
    public Boolean getSound() {
        return sound;
    }

    @Override
    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decoderTyp", targetEntity = DecoderTypCV.class)
    @JsonManagedReference
    @JsonProperty("cvs")
    public Set<IDecoderTypCV> getCv() {
        return cv;
    }

    @Override
    public void setCv(Set<IDecoderTypCV> cv) {
        this.cv.clear();
        this.cv.addAll(cv);
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decoderTyp", targetEntity = DecoderTypFunktion.class)
    @JsonManagedReference
    public Set<IDecoderTypFunktion> getFunktion() {
        return funktion;
    }

    @Override
    public void setFunktion(Set<IDecoderTypFunktion> funktion) {
        this.funktion.clear();
        this.funktion.addAll(funktion);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
                .append("typ", getTyp()).append("hersteller", getHersteller()).append("iMax", getiMax())
                .append("protokoll", getProtokoll()).append("fahrstufe", getFahrstufe())
                .append("sound", getSound()).append("adressen", getAdressen()).append("cv", getCv())
                .append("funktion", getFunktion()).toString();
    }
}