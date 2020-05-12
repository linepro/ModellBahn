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
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * ProduktTeil.
 * Part of a product (Bill of materials).
 * E.g. Locomotives and Carriages in a set
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PRODUKT_TEIL)
@Table(name = DBNames.PRODUKT_TEIL,
    indexes = { 
        @Index(name = DBNames.PRODUKT_TEIL + "_IX1", columnList = DBNames.PRODUKT_ID + "," + DBNames.TEIL_ID, unique = true),
        @Index(name = DBNames.PRODUKT_TEIL + "_IX2", columnList = DBNames.PRODUKT_ID),
        @Index(name = DBNames.PRODUKT_TEIL + "_IX3", columnList = DBNames.TEIL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PRODUKT_TEIL + "_UC1", columnNames = { DBNames.PRODUKT_ID, DBNames.TEIL_ID })
    })
//@formatter:on
public class ProduktTeil extends ItemImpl {
    
    /** The produkt. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The component produkt */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.teil.notnull}")
    private Produkt teil;
    
    /** The count of this component. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anzahl.positive}")
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
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public ProduktTeil(Long id, Produkt produkt, Produkt teil, Integer anzahl, Boolean deleted) {
        super(id, deleted);

        setProdukt(produkt);
        setTeil(teil);
        setAnzahl(anzahl);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk1"))
    public Produkt getProdukt() {
        return produkt;
    }

    
    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.TEIL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk2"))
    public Produkt getTeil() {
        return teil;
    }

    
    public void setTeil(Produkt teil) {
        this.teil = teil;
    }

    
    @Column(name = DBNames.ANZAHL, nullable = false)
    public Integer getAnzahl() {
        return anzahl;
    }

    
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }
 
    @Override
    public int compareTo(Item other) {
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
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.PRODUKT, getProdukt())
                .append(DBNames.TEIL, getTeil())
                .append(DBNames.ANZAHL, getAnzahl())
                .toString();
    }
}
