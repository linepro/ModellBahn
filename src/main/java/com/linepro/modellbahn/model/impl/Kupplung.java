package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "KUPPLUNGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Kupplung extends AbstractNamedItem implements IKupplung {

    private static final long serialVersionUID = -3158490202101950479L;

    public Kupplung() {
		super();
	}

	public Kupplung(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}