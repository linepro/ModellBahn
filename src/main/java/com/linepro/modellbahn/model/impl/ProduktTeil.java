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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.rest.json.ProduktSerializer;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * ProduktTeil.
 * Part of a product (Bill of materials).
 * E.g. Locomotives and Carriages in a set
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "ProduktTeil")
@Table(name = "ProduktTeil", indexes = { @Index(columnList = "produkt_id,teil_id", unique = true),
        @Index(columnList = "produkt_id"),
        @Index(columnList = "teil_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "produkt_id", "teil_id" }) })
@JsonRootName("part")
public class ProduktTeil extends AbstractItem implements IProduktTeil {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7684916028825247336L;
    
    /** The produkt. */
    private IProdukt produkt;

    /** The component produkt */
    private IProdukt teil;
    
    /** The count of this component. */
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
    @JoinColumn(name = "produkt_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "produkt_teil_fk1"))
    @JsonGetter("produkt")
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentUsing=ProduktSerializer.class)
    public IProdukt getProdukt() {
        return produkt;
    }

    @Override
    @JsonSetter("produkt")
    public void setProdukt(IProdukt produkt) {
        this.produkt = produkt;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = "teil_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "produkt_teil_fk2"))
    @JsonGetter("teil")
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentUsing=ProduktSerializer.class)
    public IProdukt getTeil() {
        return teil;
    }

    @Override
    @JsonSetter("teil")
    public void setTeil(IProdukt teil) {
        this.teil = teil;
    }

    @Override
    @Column(name = "anzahl", nullable = false)
    @JsonGetter("anzahl")
    @JsonView(Views.DropDown.class)
    public Integer getAnzahl() {
        return anzahl;
    }

    @Override
    @JsonSetter("anzahl")
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getProdukt())
                .append(getTeil())
                .hashCode();
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return getProdukt().getLinkId() + "/" + getTeil().getLinkId();
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
                .append("produkt", getProdukt())
                .append("teil", getTeil())
                .append("anzahl", getAnzahl())
                .toString();
    }
}
