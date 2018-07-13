package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "LICHTEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Licht extends AbstractNamedItem implements ILicht {

    private static final long serialVersionUID = 6288751316098975414L;

    public Licht() {
		super();
	}

    @JsonCreator
	public Licht(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}