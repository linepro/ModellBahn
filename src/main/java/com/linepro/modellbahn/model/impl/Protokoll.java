package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "PROTOKOLLEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Protokoll extends AbstractNamedItem implements IProtokoll {

    private static final long serialVersionUID = -2601992994975232884L;

    public Protokoll() {
		super();
	}

	public Protokoll(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}