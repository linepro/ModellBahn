package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "STEUERUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Steuerung extends AbstractNamedItem implements ISteuerung {

    private static final long serialVersionUID = 6787896300087581256L;

    public Steuerung() {
		super();
	}

	public Steuerung(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}