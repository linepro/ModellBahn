package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "STEUERUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Steuerung extends AbstractNamedItem implements ISteuerung {

    private static final long serialVersionUID = 6787896300087581256L;

    public Steuerung() {
		super();
	}

    @JsonCreator
	public Steuerung(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}