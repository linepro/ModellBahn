package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "KATEGORIEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Kategorie extends AbstractNamedItem implements IKategorie {

    private static final long serialVersionUID = -2964561580499221297L;

    public Kategorie() {
		super();
	}

	public Kategorie(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}