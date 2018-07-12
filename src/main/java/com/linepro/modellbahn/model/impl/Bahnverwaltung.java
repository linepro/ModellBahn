package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "BAHNVERWALTUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Bahnverwaltung extends AbstractNamedItem implements IBahnverwaltung {

    private static final long serialVersionUID = 1665511590535290700L;

    public Bahnverwaltung() {
		super();
	}

	public Bahnverwaltung(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}