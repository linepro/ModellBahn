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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.util.WithAdress;
import com.linepro.modellbahn.validation.Adress;

/**
 * DecoderTypAdress.
 * The Adresss available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_ADRESS)
@Table(name = DBNames.DECODER_TYP_ADRESS,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX1", columnList = DBNames.DECODER_TYP_ID +", " + DBNames.INDEX, unique = true), 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX2", columnList = DBNames.DECODER_TYP_ID), 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX3", columnList = DBNames.INDEX)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_ADRESS + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.INDEX })
    })
@Adress
//@formatter:on
public class DecoderTypAdress extends ItemImpl implements WithAdress {

    /** The decoder typ. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The index. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.index.notnull}")
    @Range(min=1, max=10, message = "{com.linepro.modellbahn.validator.constraints.index.range}")
    private Integer index;

    /** The adressTyp. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private AdressTyp adressTyp;

    /** The span. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.span.notnull}")
    @Range(min=1, max=32, message = "{com.linepro.modellbahn.validator.constraints.span.range}")
    private Integer span;

    /** The adress. */
    private Integer adress;

    /**
     * Instantiates a new decoder typ Adress.
     */
    public DecoderTypAdress() {
        super();
    }

    /**
     * Instantiates a new decoder typ Adress.
     *
     * @param id the id
     * @param decoderTyp the decoder typ
     * @param index the index
     * @param adressTyp the adressTyp
     * @param span the span
     * @param adress the adress
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public DecoderTypAdress(Long id, DecoderTyp decoderTyp, Integer index, AdressTyp adressTyp, Integer span, 
            Integer adress, Boolean deleted) {
        super(id, deleted);

        setDecoderTyp(decoderTyp);
        setIndex(index);
        setAdressTyp(adressTyp);
        setSpan(span);
        setAdress(adress);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_ADRESS + "_fk1"))
    public DecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    
    public void setDecoderTyp(DecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    
    @BusinessKey
    @Column(name = DBNames.INDEX, nullable = false)
    public Integer getIndex() {
        return index;
    }

    
    public void setIndex(Integer index) {
        this.index = index;
    }

    
    @Column(name = DBNames.ADRESS_TYP, length = 100)
    public AdressTyp getAdressTyp() {
        return adressTyp;
    }

    
    public void setAdressTyp(AdressTyp adressTyp) {
        this.adressTyp = adressTyp;
    }

    
    @Column(name = DBNames.SPAN)
    public Integer getSpan() {
        return span;
    }

    
    public void setSpan(Integer span) {
        this.span = span;
    }

    @Column(name = DBNames.WERKSEINSTELLUNG)
    public Integer getAdress() {
        return adress;
    }

    
    public void setAdress(Integer adress) {
        this.adress = adress;
    }

    
    public int compareTo(Item other) {
        if (other instanceof DecoderTypAdress) {
            return new CompareToBuilder()
                    .append(getDecoderTyp(), ((DecoderTypAdress) other).getDecoderTyp())
                    .append(getIndex(), ((DecoderTypAdress) other).getIndex())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoderTyp())
                .append(getIndex())
                .hashCode();
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderTypAdress)) {
            return false;
        }

        DecoderTypAdress other = (DecoderTypAdress) obj;

        return new EqualsBuilder()
                .append(getDecoderTyp(), other.getDecoderTyp())
                .append(getIndex(), other.getIndex())
                .isEquals();
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.DECODER_TYP, getDecoderTyp())
                .append(DBNames.ADRESS_TYP, getAdressTyp())
                .append(DBNames.INDEX, getIndex())
                .append(DBNames.SPAN, getSpan())
                .toString();
    }
}