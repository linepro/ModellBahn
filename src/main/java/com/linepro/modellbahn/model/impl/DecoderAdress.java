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
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * DecoderAdress. An address for a decoder (several have more than one)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderAdress")
@Table(name = "DecoderAdress", indexes = { @Index(columnList = DBNames.DECODER_ID + "," + DBNames.ADRESS_TYP + "," + DBNames.ADRESS, unique = true) },
        uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.DECODER_ID, DBNames.ADRESS_TYP, DBNames.ADRESS }) })
@JsonRootName(value = ApiNames.ADRESS)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.DELETED, ApiNames.LINKS })
public class DecoderAdress extends AbstractItem implements IDecoderAdress {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1845658968133212205L;

    /** The decoder. */
    private IDecoder decoder;

    /** The typ. */
    private AdressTyp adressTyp;

    /** The adress. */
    private Integer adress;

    /**
     * Instantiates a new adress.
     */
    public DecoderAdress() {
    }

    /**
     * Instantiates a new adress.
     *
     * @param decoder
     *            the decoder
     * @param typ
     *            the typ
     * @param adress
     *            the adress
     */
    public DecoderAdress(IDecoder decoder, AdressTyp typ, Integer adress) {
        setDecoder(decoder);
        setAdressTyp(typ);
        setAdress(adress);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "decoder_address_fk1"))
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME)
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    @JsonSetter(ApiNames.DECODER)
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
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
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoder())
                .append(getAdressTyp())
                .append(getAdress())
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
                .append(getAdressTyp(), other.getAdressTyp())
                .append(getAdress(), other.getAdress())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(ApiNames.DECODER, getDecoder().getId())
                .append(ApiNames.ADRESS_TYP, getAdressTyp())
                .append(ApiNames.ADRESS, getAdress()).toString();
    }
}