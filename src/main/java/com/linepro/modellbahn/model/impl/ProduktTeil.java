package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "CONSIST", indexes = { @Index(columnList = "ZUG_ID,ARTIKEL_ID", unique = true),
		@Index(columnList = "ZUG_ID,POSITION", unique = true) })
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
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Produkt.class)
	@JoinColumn(name = "PRODUKT_ID")
	public IProdukt getProdukt() {
		return produkt;
	}

	@Override
    public void setProdukt(IProdukt produkt) {
		this.produkt = produkt;
	}

	@Override
    @OrderColumn
	@Column(name = "ANZAHL")
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
