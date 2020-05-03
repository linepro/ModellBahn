package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.entity.base.ItemImpl;
import com.linepro.modellbahn.model.validation.CVValue;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTypCv.
 * The CVs available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.DECODER_TYP_CV)
@Table(name = DBNames.DECODER_TYP_CV, indexes = { @Index(columnList = DBNames.DECODER_TYP_ID +"," + DBNames.CV, unique = true),
        @Index(columnList = DBNames.DECODER_TYP_ID), @Index(columnList = DBNames.CV) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.DECODER_TYP_ID, DBNames.CV }) })
public class DecoderTypCv extends ItemImpl {

    /** The decoder typ. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The cv. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    @Range(min=1, max=255, message = "{com.linepro.modellbahn.validator.constraints.cv.range}")
    private Integer cv;

    /** The bezeichnung. */
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The minimal. */
    @CVValue
    private Integer minimal;

    /** The maximal. */
    @CVValue
    private Integer maximal;

    /** The werkseinstellung. */
    @CVValue
    private Integer werkseinstellung;

    /**
     * Instantiates a new decoder typ CV.
     */
    public DecoderTypCv() {
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
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public DecoderTypCv(Long id, DecoderTyp decoderTyp, Integer cv, String bezeichnung, Integer minimal, Integer maximal,
            Integer werkseinstellung, Boolean deleted) {
        super(id, deleted);

        setDecoderTyp(decoderTyp);
        setCv(cv);
        setBezeichnung(bezeichnung);
        setMinimal(minimal);
        setMaximal(maximal);
        setWerkseinstellung(werkseinstellung);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_CV + "_fk1"))
    public DecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    
    public void setDecoderTyp(DecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    
    @BusinessKey
    @Column(name = DBNames.CV, nullable = false)
    public Integer getCv() {
        return cv;
    }

    
    public void setCv(Integer cv) {
        this.cv = cv;
    }

    
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
        return bezeichnung;
    }

    
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    
    @Column(name = DBNames.MINIMAL)
    public Integer getMinimal() {
        return minimal;
    }

    
    public void setMinimal(Integer minimal) {
        this.minimal = minimal;
    }

    
    @Column(name = DBNames.MAXIMAL)
    public Integer getMaximal() {
        return maximal;
    }

    
    public void setMaximal(Integer maximal) {
        this.maximal = maximal;
    }

    
    @Column(name = DBNames.WERKSEINSTELLUNG)
    public Integer getWerkseinstellung() {
        return werkseinstellung;
    }

    
    public void setWerkseinstellung(Integer werkseinstellung) {
        this.werkseinstellung = werkseinstellung;
    }

    
    public int compareTo(Item other) {
        if (other instanceof DecoderTypCv) {
            return new CompareToBuilder()
                    .append(getDecoderTyp(), ((DecoderTypCv) other).getDecoderTyp())
                    .append(getCv(), ((DecoderTypCv) other).getCv())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoderTyp())
                .append(getCv())
                .hashCode();
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderTypCv)) {
            return false;
        }

        DecoderTypCv other = (DecoderTypCv) obj;

        return new EqualsBuilder()
                .append(getDecoderTyp(), other.getDecoderTyp())
                .append(getCv(), other.getCv())
                .isEquals();
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.DECODER_TYP, getDecoderTyp())
                .append(ApiNames.CV, getCv())
                .append(ApiNames.BEZEICHNUNG, getBezeichnung())
                .append(ApiNames.MINIMAL, getMinimal())
                .append(ApiNames.MAXIMAL, getMaximal())
                .append(ApiNames.WERKSEINSTELLUNG, getWerkseinstellung())
                .toString();
    }
}