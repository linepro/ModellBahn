package com.linepro.modellbahn.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Decoder.
 * Represents a decoder (with its settings).
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Decoder")
@Table(name = "decoder", indexes = { @Index(columnList = "name", unique = true), @Index(columnList = "decoder_typ_id"),
        @Index(columnList = "protokoll_id") }, uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Decoder extends AbstractNamedItem implements IDecoder {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 44440227704021482L;

    /** The typ. */
    private IDecoderTyp decoderTyp;

    /** The protokoll. */
    private IProtokoll protokoll;

    /** The fahrstufe. */
    private Long fahrstufe;

    /** The adressen. */
    private List<DecoderAdress> adressen = new ArrayList<>();

    /** The cvs. */
    private List<DecoderCV> cvs = new ArrayList<>();

    /** The funktionen. */
    private List<DecoderFunktion> funktionen = new ArrayList<>();

    /**
     * Instantiates a new decoder.
     */
    public Decoder() {
        super();
    }

    public Decoder(String decoderId) {
        super(decoderId);
    }

    /**
     * Instantiates a new decoder.
     *
     * @param id the id
     * @param typ the typ
     * @param protokoll the protokoll
     * @param decoderId the decoder id
     * @param beschreibung the beschreibung
     * @param fahrstufe the fahrstufe
     * @param deleted the deleted
     */
    public Decoder(Long id, IDecoderTyp typ, IProtokoll protokoll, String decoderId, String beschreibung,
            Long fahrstufe,
            Boolean deleted) {
        super(id, decoderId, beschreibung, deleted);

        setDecoderTyp(typ);
        setProtokoll(protokoll);
        setFahrstufe(fahrstufe);
    }

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_fk1"))
    @JsonGetter("decoderTyp")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    @Override
    @JsonSetter("decoderTyp")
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    /**
     * Gets the protokoll.
     *
     * @return the protokoll
     */
    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = "protokoll_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_fk2"))
    @JsonGetter("protokoll")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    /**
     * Sets the protokoll.
     *
     * @param protokoll the new protokoll
     */
    @Override
    @JsonSetter("protokoll")
    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    /**
     * Gets the fahrstufe.
     *
     * @return the fahrstufe
     */
    @Override
    @Column(name = "fahrstufe", nullable = true)
    @JsonGetter("fahrstufe")
    public Long getFahrstufe() {
        return fahrstufe;
    }

    /**
     * Sets the fahrstufe.
     *
     * @param fahrstufe the new fahrstufe
     */
    @Override
    @JsonSetter("fahrstufe")
    public void setFahrstufe(Long fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    /**
     * Gets the adressen.
     *
     * @return the adressen
     */
    @Override
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "adressen")
    @CollectionTable(name = "decoder_adressen", joinColumns = @JoinColumn(name = "decoder_id", nullable = false, referencedColumnName = "id"), foreignKey = @ForeignKey(name = "decoder_fk3"))
    @JsonGetter("adressen")
    @JsonSerialize()
    public List<DecoderAdress> getAdressen() {
        return adressen;
    }

    /**
     * Sets the adressen.
     *
     * @param adressen the new adressen
     */
    @Override
    @JsonSetter("adressen")
    public void setAdressen(List<DecoderAdress> adressen) {
        this.adressen.clear();
        this.adressen.addAll(adressen);
    }

    /**
     * Gets the cvs.
     *
     * @return the cvs
     */
    @Override
    @ElementCollection(fetch = FetchType.LAZY, targetClass=DecoderCV.class)
    @CollectionTable(name = "decoder_cv", joinColumns = @JoinColumn(name = "decoder_id", nullable = false, referencedColumnName = "id"), foreignKey = @ForeignKey(name = "decoder_fk4"))
    @JsonGetter("cvs")
    @JsonSerialize()
    public List<DecoderCV> getCVs() {
        return cvs;
    }

    /**
     * Sets the cvs.
     *
     * @param cvs the new cvs
     */
    @Override
    @JsonSetter("cvs")
    public void setCVs(List<DecoderCV> cvs) {
        this.cvs.clear();
        this.cvs.addAll(cvs);
    }

    /**
     * Gets the funktionen.
     *
     * @return the funktionen
     */
    @Override
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "decoder_funktionen", joinColumns = @JoinColumn(name = "decoder_id", nullable = false, referencedColumnName = "id"), foreignKey = @ForeignKey(name = "decoder_fk5"))
    @JsonGetter("funktionen")
    @JsonSerialize()
    public List<DecoderFunktion> getFunktionen() {
        return funktionen;
    }

    /**
     * Sets the funktionen.
     *
     * @param funktionen the new funktionen
     */
    @Override
    @JsonSetter("funktionen")
    public void setFunktionen(List<DecoderFunktion> funktionen) {
        this.funktionen.clear();
        this.funktionen.addAll(funktionen);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("decoderTyp", getDecoderTyp())
                .append("protokoll", getProtokoll())
                .append("fahrstufe", getFahrstufe())
                .append("adressen", getAdressen())
                .append("cvs", getCVs())
                .append("funktionen", getFunktionen())
                .toString();
    }
}