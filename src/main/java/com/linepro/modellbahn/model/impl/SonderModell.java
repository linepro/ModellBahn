package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "sondermodell", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class SonderModell extends AbstractNamedItem implements ISonderModell {

	/**
     * 
     */
    private static final long serialVersionUID = -1654429154794267608L;

    public SonderModell() {
		super();
	}

    public SonderModell( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}