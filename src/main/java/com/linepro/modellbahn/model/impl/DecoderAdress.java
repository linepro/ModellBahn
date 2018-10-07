/*
 * Adress
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.impl;

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.DecoderAdressKey;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.model.validation.Adress;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderAdress. An address for a decoder (several have more than one)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.DECODER_ADRESS)
@Table(name =  DBNames.DECODER_ADRESS, indexes = { @Index(columnList = DBNames.DECODER_ID + "," + DBNames.INDEX, unique = true) },
        uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.DECODER_ID, DBNames.INDEX }) })
@JsonRootName(value = ApiNames.ADRESS)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.INDEX, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.DELETED, ApiNames.LINKS })
@Adress
public class DecoderAdress extends AbstractItem<DecoderAdressKey> implements IDecoderAdress {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1845658968133212205L;

    /** The decoder. */
    @NotNull
    private IDecoder decoder;

    /** The adress. */
    @Range(min=0,max=5)
    private Integer index;

    /** The typ. */
    @NotNull
    private AdressTyp adressTyp;

    /** The adress. */
    private Integer adress;

    public DecoderAdress() {
    }

    public DecoderAdress(Long id, IDecoder decoder, Integer index, AdressTyp typ, Integer adress, Boolean deleted) {
        super(id, deleted);
        setDecoder(decoder);
        setIndex(index);
        setAdressTyp(typ);
        setAdress(adress);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_ADRESS + "_fk1"))
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=DecoderResolver.class)
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(as=Decoder.class)
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    @Column(name = DBNames.INDEX, nullable = false)
    @JsonGetter(ApiNames.INDEX)
    @JsonView(Views.DropDown.class)
    public Integer getIndex() {
        return index;
    }

    @Override
    @JsonSetter(ApiNames.INDEX)
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    @BusinessKey
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.ADRESS_TYP, nullable = false, length = 10)
    @JsonGetter(ApiNames.ADRESS_TYP)
    @JsonView(Views.DropDown.class)
    public AdressTyp getAdressTyp() {
        return adressTyp;
    }

    @Override
    @JsonSetter(ApiNames.ADRESS_TYP)
    public void setAdressTyp(AdressTyp adressTyp) {
        this.adressTyp = adressTyp;
    }

    @Override
    @Column(name = DBNames.ADRESS, nullable = false)
    @JsonGetter(ApiNames.ADRESS)
    @JsonView(Views.DropDown.class)
    public Integer getAdress() {
        return adress;
    }

    @Override
    @JsonSetter(ApiNames.ADRESS)
    public void setAdress(Integer adress) {
        this.adress = adress;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return decoder.getLinkId();
     }

     @Override
     @Transient
     @JsonIgnore
     public String getLinkId() {
         return String.format(ApiPaths.DECODER_ADRESS_LINK, getParentId(), getIndex());
     }

     @Override
     public int compareTo(IItem<?> other) {
         if (other instanceof DecoderAdress) {
             return new CompareToBuilder()
                     .append(getDecoder(), ((DecoderAdress) other).getDecoder())
                     .append(getIndex(), ((DecoderAdress) other).getIndex())
                     .toComparison();
         }
         
         return super.compareTo(other);
     }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoder())
                .append(getIndex())
                .hashCode();
    }

    @Override
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.DECODER, getDecoder())
                .append(ApiNames.INDEX, getIndex())
                .append(ApiNames.ADRESS_TYP, getAdressTyp())
                .append(ApiNames.ADRESS, getAdress())
                .toString();
    }
}