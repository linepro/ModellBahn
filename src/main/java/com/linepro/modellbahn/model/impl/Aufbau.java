package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "aufbauten", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Aufbau extends AbstractNamedItem implements IAufbau {

    private static final long serialVersionUID = 233985591709407388L;

    public Aufbau() {
		super();
	}

	public Aufbau(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}