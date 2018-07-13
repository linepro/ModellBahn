package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "BAHNVERWALTUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Bahnverwaltung extends AbstractNamedItem implements IBahnverwaltung {

    private static final long serialVersionUID = 1665511590535290700L;

    public Bahnverwaltung() {
		super();
	}

    @JsonCreator
	public Bahnverwaltung(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}