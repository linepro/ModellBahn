package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "sondermodell", indexes = { @Index(columnList = "name", unique = true) })
public class SonderModell extends AbstractNamedItem implements ISonderModell {

	/**
     * 
     */
    private static final long serialVersionUID = -1654429154794267608L;

    public SonderModell() {
		super();
	}

    @JsonCreator
	public SonderModell(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}