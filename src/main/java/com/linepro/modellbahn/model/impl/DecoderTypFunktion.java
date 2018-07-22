package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;

@Entity
@Table(name = "decoder_typ_funktionen", indexes = { @Index(columnList = "decoder_typ_id,reihe,name", unique = true),
        @Index(columnList = "decoder_typ_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "decoder_typ_id", "reihe", "name" }) })
public class DecoderTypFunktion extends AbstractNamedItem implements IDecoderTypFunktion {

    private static final long serialVersionUID = -9194895557054214626L;

    private IDecoderTyp decoderTyp;

    private Integer reihe;

    private Boolean programmable;

    public DecoderTypFunktion() {
        super();
    }

    public DecoderTypFunktion(Long id, IDecoderTyp decoderTyp, Integer reihe, String funktionNr, String bezeichnung, Boolean programmable, Boolean deleted) {
        super(id, funktionNr, bezeichnung, deleted);

        setDecoderTyp(decoderTyp);
        setReihe(reihe);
        setProgrammable(programmable);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fn_fk1"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    @Override
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    @Override
    @BusinessKey
    @Column(name = "reihe", nullable = false)
    public Integer getReihe() {
        return reihe;
    }

    @Override
    public void setReihe(Integer reihe) {
        this.reihe = reihe;
    }

    @Override
    @Column(name = "programmable", nullable = false)
    public Boolean getProgrammable() {
        return programmable;
    }

    @Override
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