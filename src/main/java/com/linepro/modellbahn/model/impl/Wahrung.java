package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "wahrungen", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Wahrung extends AbstractNamedItem implements IWahrung {
	
    private static final long serialVersionUID = 9168067747478159138L;

    private Integer decimals;
	
	public Wahrung() {
		super();
	}

	public Wahrung(Long id, String name, String bezeichnung, Integer decimals, Boolean deleted) {
		super(id, name, bezeichnung, deleted);

		this.decimals = decimals;
	}

	@Override
    @Column(name="decimals")
	public Integer getDecimals() {
		return decimals;
	}

	@Override
    public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("decimals", getDecimals()).toString();
	}
}
