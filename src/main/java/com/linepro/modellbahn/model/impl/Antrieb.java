package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "ANTRIEBEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Antrieb extends AbstractNamedItem implements IAntrieb {

    private static final long serialVersionUID = 6791703187859778429L;

    public Antrieb() {
		super();
	}

    @JsonCreator
	public Antrieb(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}