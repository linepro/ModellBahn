package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "MASSSTABEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Massstab extends AbstractNamedItem implements IMassstab {

    private static final long serialVersionUID = 3531688695840325563L;

    public Massstab() {
		super();
	}

	public Massstab(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}