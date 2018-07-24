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

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTypCV.
 * The CVs available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderTypCV")
@Table(name = "decoder_typ_cv", indexes = { @Index(columnList = "decoder_typ_id,cv", unique = true),
        @Index(columnList = "decoder_typ_id"), @Index(columnList = "cv") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "decoder_typ_id", "cv" }) })
public class DecoderTypCV extends AbstractItem implements IDecoderTypCV {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5202372019371973750L;

    /** The decoder typ. */
    private IDecoderTyp decoderTyp;

    /** The cv. */
    private Integer cv;

    /** The bezeichnung. */
    private String bezeichnung;

    /** The minimal. */
    private Integer minimal;

    /** The maximal. */
    private Integer maximal;

    /** The werkseinstellung. */
    private Integer werkseinstellung;

    /**
     * Instantiates a new decoder typ CV.
     */
    public DecoderTypCV() {
        super();
    }

    /**
     * Instantiates a new decoder typ CV.
     *
     * @param id the id
     * @param decoderTyp the decoder typ
     * @param cv the cv
     * @param bezeichnung the bezeichnung
     * @param minimal the minimal
     * @param maximal the maximal
     * @param werkseinstellung the werkseinstellung
     * @param deleted the deleted
     */
    public DecoderTypCV(Long id, IDecoderTyp decoderTyp, Integer cv, String bezeichnung, Integer minimal, Integer maximal,
            Integer werkseinstellung, Boolean deleted) {
        super(id, deleted);

        setDecoderTyp(decoderTyp);
        setCV(cv);
        setBezeichnung(bezeichnung);
        setMinimal(minimal);
        setMaximal(maximal);
        setWerkseinstellung(werkseinstellung);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_cv_fk1"))
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
    @Column(name = "cv", nullable = false)
    @JsonGetter("cv")
    public Integer getCV() {
        return cv;
    }

    @Override
    @JsonSetter("cv")
    public void setCV(Integer cv) {
        this.cv = cv;
    }

    @Override
    @Column(name = "bezeichnung", nullable = true, length = 100)
    @JsonGetter("bezeichnung")
    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    @JsonSetter("bezeichnung")
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    @Column(name = "minimal", nullable = true)
    @JsonGetter("minimal")
    public Integer getMinimal() {
        return minimal;
    }

    @Override
    @JsonSetter("minimal")
    public void setMinimal(Integer minimal) {
        this.minimal = minimal;
    }

    @Override
    @Column(name = "maximal", nullable = true)
    @JsonGetter("maximal")
    public Integer getMaximal() {
        return maximal;
    }

    @Override
    @JsonSetter("maximal")
    public void setMaximal(Integer maximal) {
        this.maximal = maximal;
    }

    @Override
    @Column(name = "werkseinstellung", nullable = true)
    @JsonGetter("werkseinstellung")
    public Integer getWerkseinstellung() {
        return werkseinstellung;
    }

    @Override
    @JsonSetter("werkseinstellung")
    public void setWerkseinstellung(Integer werkseinstellung) {
        this.werkseinstellung = werkseinstellung;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("decoderTyp", getDecoderTyp())
                .append("cv", getCV())
                .append("bezeichnung", getBezeichnung())
                .append("minimal", getMinimal())
                .append("maximal", getMaximal())
                .append("werkseinstellung", getWerkseinstellung())
                .toString();
    }
}