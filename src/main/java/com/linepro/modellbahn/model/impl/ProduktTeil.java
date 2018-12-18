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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.keys.ProduktTeilKey;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.ProduktSerializer;
import com.linepro.modellbahn.rest.json.serialization.ProduktTeilSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * ProduktTeil.
 * Part of a product (Bill of materials).
 * E.g. Locomotives and Carriages in a set
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.PRODUKT_TEIL)
@Table(name = DBNames.PRODUKT_TEIL, indexes = { @Index(columnList = DBNames.PRODUKT_ID + "," + DBNames.TEIL_ID, unique = true),
        @Index(columnList = DBNames.PRODUKT_ID),
        @Index(columnList = DBNames.TEIL_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.PRODUKT_ID, DBNames.TEIL_ID }) })
@JsonRootName(value = ApiNames.TEIL)
@JsonPropertyOrder({ApiNames.ID, ApiNames.PRODUKT,ApiNames.TEIL, ApiNames.ANZAHL, ApiNames.DELETED, ApiNames.LINKS})
public class ProduktTeil extends AbstractItem<ProduktTeilKey> implements IProduktTeil {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7684916028825247336L;
    
    /** The produkt. */
    @NotNull
    private IProdukt produkt;

    /** The component produkt */
    @NotNull
    private IProdukt teil;
    
    /** The count of this component. */
    @Positive
    private Integer anzahl;

    /**
     * Instantiates a new produkt teil.
     */
    public ProduktTeil() {
        super();
    }

    /**
     * Instantiates a new produkt teil.
     *
     * @param id the id
     * @param produkt the produkt
     * @param anzahl the anzahl
     * @param deleted the deleted
     */
    public ProduktTeil(Long id, IProdukt produkt, IProdukt teil, Integer anzahl, Boolean deleted) {
        super(id, deleted);

        setProdukt(produkt);
        setTeil(teil);
        setAnzahl(anzahl);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk1"))
    @JsonGetter(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=ProduktSerializer.class)
    public IProdukt getProdukt() {
        return produkt;
    }

    @Override
    @BusinessKey
    @JsonSetter(ApiNames.PRODUKT)
    @JsonDeserialize(as=Produkt.class)
    public void setProdukt(IProdukt produkt) {
        this.produkt = produkt;
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.TEIL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk2"))
    @JsonGetter(ApiNames.TEIL)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=ProduktTeilSerializer.class)
    public IProdukt getTeil() {
        return teil;
    }

    @Override
    @JsonSetter(ApiNames.TEIL)
    @JsonDeserialize(as=ProduktTeil.class)
    public void setTeil(IProdukt teil) {
        this.teil = teil;
    }

    @Override
    @Column(name = DBNames.ANZAHL, nullable = false)
    @JsonGetter(ApiNames.ANZAHL)
    @JsonView(Views.DropDown.class)
    public Integer getAnzahl() {
        return anzahl;
    }

    @Override
    @JsonSetter(ApiNames.ANZAHL)
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }
    
    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return getProdukt().getLinkId();
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.PRODUKT_TEIL_LINK, getParentId(), getTeil().getLinkId());
    }

    @Override
    public int compareTo(IItem<?> other) {
        if (other instanceof ProduktTeil) {
            return new CompareToBuilder()
                    .append(getProdukt(), ((ProduktTeil) other).getProdukt())
                    .append(getTeil(), ((ProduktTeil) other).getTeil())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getProdukt())
                .append(getTeil())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProduktTeil)) {
            return false;
        }

        ProduktTeil other = (ProduktTeil) obj;

        return new EqualsBuilder()
                .append(getProdukt(), other.getProdukt())
                .append(getTeil(), other.getTeil())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append(ApiNames.PRODUKT, getProdukt())
                .append(ApiNames.TEIL, getTeil())
                .append(ApiNames.ANZAHL, getAnzahl())
                .toString();
    }
}
