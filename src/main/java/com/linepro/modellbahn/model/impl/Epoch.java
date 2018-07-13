package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "EPOCHEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Epoch extends AbstractNamedItem implements IEpoch {

    private static final long serialVersionUID = -1742959707816247906L;

    public Epoch() {
		super();
	}

    @JsonCreator
	public Epoch(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}