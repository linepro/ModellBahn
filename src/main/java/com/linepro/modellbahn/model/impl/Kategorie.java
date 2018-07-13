package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "KATEGORIEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Kategorie extends AbstractNamedItem implements IKategorie {

    private static final long serialVersionUID = -2964561580499221297L;

    public Kategorie() {
		super();
	}

    @JsonCreator
	public Kategorie(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}