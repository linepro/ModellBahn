package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "PROTOKOLLEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Protokoll extends AbstractNamedItem implements IProtokoll {

    private static final long serialVersionUID = -2601992994975232884L;

    public Protokoll() {
		super();
	}

    @JsonCreator
	public Protokoll(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}