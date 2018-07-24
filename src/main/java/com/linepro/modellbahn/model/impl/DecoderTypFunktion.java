package com.linepro.modellbahn.model.impl;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
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
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTypFunktion. The Functions available for a DecoderTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderTypFunktion")
@Table(name = "decoder_typ_funktionen", indexes = { @Index(columnList = "decoder_typ_id,reihe,name", unique = true),
        @Index(columnList = "decoder_typ_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "decoder_typ_id", "reihe", "name" }) })
@AttributeOverride(name = "name", column = @Column(name = "name", unique = false, length = 4))
public class DecoderTypFunktion extends AbstractNamedItem implements IDecoderTypFunktion {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9194895557054214626L;

    /** The decoder typ. */
    private IDecoderTyp decoderTyp;

    /** The reihe. */
    private Integer reihe;

    /** The programmable. */
    private Boolean programmable;

    /**
     * Instantiates a new decoder typ funktion.
     */
    public DecoderTypFunktion() {
        super();
    }

    public DecoderTypFunktion(IDecoderTyp decoderTyp, Integer reihe, String funktionNr) {
        super(funktionNr);

        setDecoderTyp(decoderTyp);
        setReihe(reihe);
    }

    /**
     * Instantiates a new decoder typ funktion.
     *
     * @param id
     *            the id
     * @param decoderTyp
     *            the decoder typ
     * @param reihe
     *            the reihe
     * @param funktionNr
     *            the funktion nr
     * @param bezeichnung
     *            the bezeichnung
     * @param programmable
     *            the programmable
     * @param deleted
     *            the deleted
     */
    public DecoderTypFunktion(Long id, IDecoderTyp decoderTyp, Integer reihe, String funktionNr, String bezeichnung,
            Boolean programmable, Boolean deleted) {
        super(id, funktionNr, bezeichnung, deleted);

        setDecoderTyp(decoderTyp);
        setReihe(reihe);
        setProgrammable(programmable);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fn_fk1"))
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
    @BusinessKey
    @Column(name = "reihe", nullable = false)
    @JsonGetter("reihe")
    public Integer getReihe() {
        return reihe;
    }

    @Override
    @JsonSetter("reihe")
    public void setReihe(Integer reihe) {
        this.reihe = reihe;
    }

    @Override
    @Column(name = "programmable", nullable = false)
    @JsonGetter("programmable")
    public Boolean getProgrammable() {
        return programmable;
    }

    @Override
    @JsonSetter("programmable")
    public void setProgrammable(Boolean programmable) {
        this.programmable = programmable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("decoderTyp", getDecoderTyp())
                .append("reihe", getReihe())
                .append("programmable", getProgrammable())
                .toString();
    }
}