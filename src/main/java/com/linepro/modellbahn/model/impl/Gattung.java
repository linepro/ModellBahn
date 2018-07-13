package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "GATTUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Gattung extends AbstractNamedItem implements IGattung {

    private static final long serialVersionUID = -6746062683096738117L;

    public Gattung() {
		super();
	}

    @JsonCreator
	public Gattung(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}