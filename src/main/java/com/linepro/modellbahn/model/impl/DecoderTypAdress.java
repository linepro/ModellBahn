package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.keys.DecoderTypAdressKey;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTypAdress.
 * The Adresss available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderTypAdress")
@Table(name = "DecoderTypAdress", indexes = { @Index(columnList = DBNames.DECODER_TYP_ID +"," + DBNames.INDEX, unique = true),
        @Index(columnList = DBNames.DECODER_TYP_ID), @Index(columnList = DBNames.INDEX) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.DECODER_TYP_ID, DBNames.INDEX }) })
@JsonRootName(value = ApiNames.ADRESS)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER_TYP,  ApiNames.INDEX,  ApiNames.ADRESS_TYP,  ApiNames.SPAN,  ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED, ApiNames.LINKS}) 
public class DecoderTypAdress extends AbstractItem<DecoderTypAdressKey> implements IDecoderTypAdress {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5202372019371973750L;

    /** The decoder typ. */
    private IDecoderTyp decoderTyp;

    /** The index. */
    @Range(min=1,max=255)
    private Integer index;

    /** The adressTyp. */
    @NotEmpty
    private AdressTyp adressTyp;

    /** The minimal. */
    @Range(min=0,max=255)
    private Integer minimal;

    /** The werkseinstellung. */
    @Range(min=0,max=255)
    private Integer werkseinstellung;

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
     * @param minimal the minimal
     * @param maximal the maximal
     * @param werkseinstellung the werkseinstellung
     * @param deleted the deleted
     */
    public DecoderTypAdress(Long id, IDecoderTyp decoderTyp, Integer index, AdressTyp adressTyp, Integer length,
            Integer werkseinstellung, Boolean deleted) {
        super(id, deleted);

        setDecoderTyp(decoderTyp);
        setIndex(index);
        setAdressTyp(adressTyp);
        setSpan(minimal);
        setWerkseinstellung(werkseinstellung);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "decoder_typ_adress_fk1"))
    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=DecoderTypSerializer.class)
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    @Override
    @JsonSetter(ApiNames.DECODER_TYP)
    @JsonDeserialize(as=DecoderTyp.class)
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    @Override
    @BusinessKey
    @Column(name = DBNames.INDEX, nullable = false)
    public Integer getIndex() {
        return index;
    }

    @Override
    @JsonSetter(ApiNames.INDEX)
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    @Column(name = DBNames.ADRESS_TYP, nullable = true, length = 100)
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
    @Column(name = DBNames.SPAN, nullable = true)
    @JsonGetter(ApiNames.SPAN)
    @JsonView(Views.Public.class)
    public Integer getSpan() {
        return minimal;
    }

    @Override
    @JsonSetter(ApiNames.SPAN)
    public void setSpan(Integer minimal) {
        this.minimal = minimal;
    }

    @Override
    @Column(name = DBNames.WERKSEINSTELLUNG, nullable = true)
    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @JsonView(Views.Public.class)
    public Integer getWerkseinstellung() {
        return werkseinstellung;
    }

    @Override
    @JsonSetter(ApiNames.WERKSEINSTELLUNG)
    public void setWerkseinstellung(Integer werkseinstellung) {
        this.werkseinstellung = werkseinstellung;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return getDecoderTyp().getLinkId();
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.DECODER_TYP_ADRESS_LINK, getParentId(), getIndex());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoderTyp())
                .append(getIndex())
                .hashCode();
    }

    @Override
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.DECODER_TYP, getDecoderTyp())
                .append(ApiNames.ADRESS_TYP, getAdressTyp())
                .append(ApiNames.INDEX, getIndex())
                .append(ApiNames.SPAN, getSpan())
                .toString();
    }
}