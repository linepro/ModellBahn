package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "zug_typen", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class ZugTyp extends AbstractNamedItem implements IZugTyp {

    private static final long serialVersionUID = 2290449046107280442L;

    public ZugTyp() {
		super();
	}

	public ZugTyp( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}