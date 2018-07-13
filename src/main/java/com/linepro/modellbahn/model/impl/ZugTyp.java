package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "ZUG_TYPEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class ZugTyp extends AbstractNamedItem implements IZugTyp {

    private static final long serialVersionUID = 2290449046107280442L;

    public ZugTyp() {
		super();
	}

    @JsonCreator
	public ZugTyp(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}