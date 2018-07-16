package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "epochen", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Epoch extends AbstractNamedItem implements IEpoch {

    private static final long serialVersionUID = -1742959707816247906L;

    public Epoch() {
		super();
	}

	public Epoch( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}