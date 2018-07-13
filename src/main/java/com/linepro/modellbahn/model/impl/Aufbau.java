package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "AUFBAUTEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Aufbau extends AbstractNamedItem implements IAufbau {

    private static final long serialVersionUID = 233985591709407388L;

    public Aufbau() {
		super();
	}

    @JsonCreator
	public Aufbau(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}