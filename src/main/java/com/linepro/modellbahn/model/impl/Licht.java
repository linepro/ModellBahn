package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "LICHTEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Licht extends AbstractNamedItem implements ILicht {

    private static final long serialVersionUID = 6288751316098975414L;

    public Licht() {
		super();
	}

	public Licht(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}