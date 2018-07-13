package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "SPURWEITEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Spurweite extends AbstractNamedItem implements ISpurweite {

    private static final long serialVersionUID = 3673395807313729165L;

    public Spurweite() {
		super();
	}

    @JsonCreator
	public Spurweite(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}