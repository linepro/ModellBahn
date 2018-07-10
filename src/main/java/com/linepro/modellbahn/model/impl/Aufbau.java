package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "AUFBAUTEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Aufbau extends AbstractNamedItem implements IAufbau {

    private static final long serialVersionUID = 233985591709407388L;

    public Aufbau() {
		super();
	}

	public Aufbau(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}