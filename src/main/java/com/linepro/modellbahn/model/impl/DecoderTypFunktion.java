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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "decoder_typ_funktionen", indexes = { @Index(columnList = "decoder_typ_id,reihe,nr", unique = true),
        @Index(columnList = "decoder_typ_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "decoder_typ_id", "reihe", "nr" }) })
public class DecoderTypFunktion extends AbstractItem implements IDecoderTypFunktion {

    private static final long serialVersionUID = -9194895557054214626L;

    private IDecoderTyp decoderTyp;

    private Long reihe;

    private String funktionNr;

    public DecoderTypFunktion() {
        super();
    }

    public DecoderTypFunktion(Long id, IDecoderTyp decoderTyp, Long reihe, String funktionNr, Boolean deleted) {
        super(id, deleted);

        this.decoderTyp = decoderTyp;
        this.reihe = reihe;
        this.funktionNr = funktionNr;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fn_fk1"))
    @JsonBackReference
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    @Override
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    @Override
    @Column(name = "reihe", nullable = false)
    public Long getReihe() {
        return reihe;
    }

    @Override
    public void setReihe(Long reihe) {
        this.reihe = reihe;
    }

    @Override
    @Column(name = "nr", nullable = false, length = 4)
    public String getFunktionNr() {
        return funktionNr;
    }

    @Override
    public void setFunktionNr(String funktionNr) {
        this.funktionNr = funktionNr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("decoderTyp", decoderTyp)
                .append("reihe", reihe)
                .append("funktionNr", funktionNr)
                .toString();
    }
}