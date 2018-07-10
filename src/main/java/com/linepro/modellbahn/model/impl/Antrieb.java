package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "ANTRIEBEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Antrieb extends AbstractNamedItem implements IAntrieb {

    private static final long serialVersionUID = 6791703187859778429L;

    public Antrieb() {
		super();
	}

	public Antrieb(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}