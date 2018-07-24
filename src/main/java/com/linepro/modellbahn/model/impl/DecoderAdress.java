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

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderAdress.
 * An address for a decoder (several have more than one) 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderAdress")
@Table(name = "decoder_adressen", indexes = { @Index(columnList = "decoder_id,typ,adress", unique = true) },
       uniqueConstraints = { @UniqueConstraint(columnNames = { "decoder_id", "typ","adress" })})
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
     * @param decoder the decoder
     * @param typ the typ
     * @param adress the adress
     */
    public DecoderAdress(IDecoder decoder, AdressTyp typ, Integer adress) {
        setDecoder(decoder);
        setAdressTyp(typ);
        setAdress(adress);
    }

    @Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name="decoder_id", nullable = false, referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_address_fk1"))
    @JsonGetter("decoder")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    @JsonSetter("decoder")
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(name = "typ", nullable = false, length=10)
    @JsonGetter("adressTyp")
    public AdressTyp getAdressTyp() {
        return adressTyp;
    }

    @Override
    @JsonSetter("adressTyp")
    public void setAdressTyp(AdressTyp adressTyp) {
        this.adressTyp = adressTyp;
    }

    @Override
    @Column(name = "adress", nullable = false)
    @JsonGetter("adress")
    public Integer getAdress() {
        return adress;
    }

    @Override
    @JsonSetter("adress")
    public void setAdress(Integer adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("decoder", getDecoder().getId())
                .append("typ", getAdressTyp())
                .append("adress", getAdress()).toString();
    }
}