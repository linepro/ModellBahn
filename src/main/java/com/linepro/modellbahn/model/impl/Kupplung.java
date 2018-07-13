package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "KUPPLUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Kupplung extends AbstractNamedItem implements IKupplung {

    private static final long serialVersionUID = -3158490202101950479L;

    public Kupplung() {
		super();
	}

    @JsonCreator
	public Kupplung(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}