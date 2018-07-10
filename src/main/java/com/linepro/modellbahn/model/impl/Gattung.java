package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "GATTUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Gattung extends AbstractNamedItem implements IGattung {

    private static final long serialVersionUID = -6746062683096738117L;

    public Gattung() {
		super();
	}

	public Gattung(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}