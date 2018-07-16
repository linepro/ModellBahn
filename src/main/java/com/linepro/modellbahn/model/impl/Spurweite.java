package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "spurweiten", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Spurweite extends AbstractNamedItem implements ISpurweite {

    private static final long serialVersionUID = 3673395807313729165L;

    public Spurweite() {
		super();
	}

	public Spurweite( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}