package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "SPURWEITEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Spurweite extends AbstractNamedItem implements ISpurweite {

    private static final long serialVersionUID = 3673395807313729165L;

    public Spurweite() {
		super();
	}

	public Spurweite(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}