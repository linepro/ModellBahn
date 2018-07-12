package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.ISondermodel;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "SONDERMODEL", indexes = { @Index(columnList = "NAME", unique = true) })
public class Sondermodel extends AbstractNamedItem implements ISondermodel {

	/**
     * 
     */
    private static final long serialVersionUID = -1654429154794267608L;

    public Sondermodel() {
		super();
	}

	public Sondermodel(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}