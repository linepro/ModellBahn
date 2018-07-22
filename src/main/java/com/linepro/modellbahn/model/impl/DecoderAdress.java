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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.model.util.AdressTyp;

@Entity
@Table(name = "decoder_adressen", indexes = { @Index(columnList = "decoder_id,typ,adress", unique = true) },
       uniqueConstraints = { @UniqueConstraint(columnNames = { "decoder_id", "typ","adress" })})
public class DecoderAdress extends AbstractItem implements IDecoderAdress {

    private static final long serialVersionUID = -1845658968133212205L;

    private IDecoder decoder;

    /** The typ. */
    private AdressTyp typ;

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
     * @param id
     *            the id
     * @param poles
     *            the poles
     * @param switches
     *            the switches
     */
    public DecoderAdress(IDecoder decoder, AdressTyp typ, Integer adress) {
        setDecoder(decoder);
        setTyp(typ);
        setAdress(adress);
    }

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name="decoder_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_address_fk1"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "typ", length=10)
    @JsonGetter("typ")
    public AdressTyp getTyp() {
        return typ;
    }

    public void setTyp(AdressTyp typ) {
        this.typ = typ;
    }

    @Column(name = "adress")
    public Integer getAdress() {
        return adress;
    }

    public void setAdress(Integer adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("decoder", getDecoder())
                .append("typ", getTyp())
                .append("adress", getAdress()).toString();
    }
}