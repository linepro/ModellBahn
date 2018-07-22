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
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;

@Entity
@Table(name = "decoder_typ_cv", indexes = { @Index(columnList = "decoder_typ_id,cv", unique = true),
        @Index(columnList = "decoder_typ_id"), @Index(columnList = "cv") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "decoder_typ_id", "cv" }) })
public class DecoderTypCV extends AbstractItem implements IDecoderTypCV {

    private static final long serialVersionUID = -5202372019371973750L;

    private IDecoderTyp decoderTyp;

    private Integer cv;

    private String bezeichnung;

    private Integer minimal;

    private Integer maximal;

    private Integer werkseinstellung;

    public DecoderTypCV() {
        super();
    }

    public DecoderTypCV(Long id, IDecoderTyp decoderTyp, Integer cv, String bezeichnung, Integer minimal, Integer maximal,
            Integer werkseinstellung, Boolean deleted) {
        super(id, deleted);

        this.decoderTyp = decoderTyp;
        this.cv = cv;
        this.bezeichnung = bezeichnung;
        this.minimal = minimal;
        this.maximal = maximal;
        this.werkseinstellung = werkseinstellung;
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_cv_fk1"))
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
    @Column(name = "cv", nullable = false)
    @Min(0)
    public Integer getCv() {
        return cv;
    }

    @Override
    public void setCv(Integer cv) {
        this.cv = cv;
    }

    @Override
    @Column(name = "bezeichnung", length = 100)
    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    @Column(name = "minimal", nullable = true)
    public Integer getMinimal() {
        return minimal;
    }

    @Override
    public void setMinimal(Integer minimal) {
        this.minimal = minimal;
    }

    @Override
    @Column(name = "maximal", nullable = true)
    public Integer getMaximal() {
        return maximal;
    }

    @Override
    public void setMaximal(Integer maximal) {
        this.maximal = maximal;
    }

    @Override
    @Column(name = "werkseinstellung", nullable = true)
    public Integer getWerkseinstellung() {
        return werkseinstellung;
    }

    @Override
    public void setWerkseinstellung(Integer werkseinstellung) {
        this.werkseinstellung = werkseinstellung;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
                .append("decoderTyp", getDecoderTyp())
                .append("cv", getCv())
                .append("bezeichnung", getBezeichnung())
                .append("minimal", getMinimal())
                .append("maximal", getMaximal())
                .append("werkseinstellung", getWerkseinstellung()).toString();
    }
}