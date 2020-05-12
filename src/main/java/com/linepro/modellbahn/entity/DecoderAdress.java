/*
 * Adress
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * DecoderAdress. An address for a decoder (several have more than one)
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_ADRESS)
@Table(name =  DBNames.DECODER_ADRESS,
    indexes = { 
        @Index(name = DBNames.DECODER_ADRESS + "_IX1", columnList = DBNames.DECODER_ID + "," + DBNames.INDEX, unique = true) 
    }, uniqueConstraints = { 
        @UniqueConstraint(name = DBNames.DECODER_ADRESS + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.INDEX }) 
        })
@Adress
//@formatter:on
public class DecoderAdress extends ItemImpl implements WithAdress {

    /** The decoder. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;

    /** The adress. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.index.notnull}")
    @Range(min=1, max=6, message = "{com.linepro.modellbahn.validator.constraints.index.range}")
    private Integer index;

    /** The typ. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.adressTyp.notnull}")
    private AdressTyp adressTyp;

    /** The adress. */
    private Integer adress;

    public DecoderAdress() {
    }

    public DecoderAdress(Long id, Decoder decoder, Integer index, AdressTyp typ, Integer adress, Boolean deleted) {
        super(id, deleted);
        setDecoder(decoder);
        setIndex(index);
        setAdressTyp(typ);
        setAdress(adress);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_ADRESS + "_fk1"))
    public Decoder getDecoder() {
        return decoder;
    }

    
    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    
    @Column(name = DBNames.INDEX, nullable = false)
    public Integer getIndex() {
        return index;
    }

    
    public void setIndex(Integer index) {
        this.index = index;
    }

    
    @BusinessKey
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.ADRESS_TYP, nullable = false, length = 10)
    public AdressTyp getAdressTyp() {
        return adressTyp;
    }

    
    public void setAdressTyp(AdressTyp adressTyp) {
        this.adressTyp = adressTyp;
    }

    
    @Column(name = DBNames.ADRESS, nullable = false)
    public Integer getAdress() {
        return adress;
    }

    
    public void setAdress(Integer adress) {
        this.adress = adress;
    }

    
    public int compareTo(Item other) {
         if (other instanceof DecoderAdress) {
             return new CompareToBuilder()
                     .append(getDecoder(), ((DecoderAdress) other).getDecoder())
                     .append(getIndex(), ((DecoderAdress) other).getIndex())
                     .toComparison();
         }
         
         return super.compareTo(other);
     }

    
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoder())
                .append(getIndex())
                .hashCode();
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderAdress)) {
            return false;
        }

        DecoderAdress other = (DecoderAdress) obj;

        return new EqualsBuilder()
                .append(getDecoder(), other.getDecoder())
                .append(getIndex(), other.getIndex())
                .isEquals();
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.DECODER, getDecoder())
                .append(DBNames.INDEX, getIndex())
                .append(DBNames.ADRESS_TYP, getAdressTyp())
                .append(DBNames.ADRESS, getAdress())
                .toString();
    }
}