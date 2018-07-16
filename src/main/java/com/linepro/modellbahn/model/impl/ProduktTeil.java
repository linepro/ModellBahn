package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "consist", indexes = { @Index(columnList = "zug_id,position", unique = true),
        @Index(columnList = "zug_id"),
        @Index(columnList = "artikel_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "zug_id", "position" }) })
public class ProduktTeil extends AbstractItem implements IProduktTeil {

    /**
     * 
     */
    private static final long serialVersionUID = 7684916028825247336L;
    private IProdukt produkt;
    private Integer anzahl;

    public ProduktTeil() {
        super();
    }

    public ProduktTeil(Long id, IProdukt produkt, Integer anzahl, Boolean deleted) {
        super(id, deleted);

        this.produkt = produkt;
        this.anzahl = anzahl;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = "produkt_id", foreignKey = @ForeignKey(name = "produkt_teil_fk1"))
    @JsonProperty(value = "produktId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public IProdukt getProdukt() {
        return produkt;
    }

    @Override
    public void setProdukt(IProdukt produkt) {
        this.produkt = produkt;
    }

    @Override
    @OrderColumn
    @Column(name = "anzahl")
    public Integer getAnzahl() {
        return anzahl;
    }

    @Override
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
                .append("Produkt", getProdukt()).append("anzahl", getAnzahl()).toString();
    }
}
