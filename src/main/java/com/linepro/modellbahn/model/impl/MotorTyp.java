package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "MOTORTYPEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class MotorTyp extends AbstractNamedItem implements IMotorTyp {

    private static final long serialVersionUID = -4407612983152968677L;

    public MotorTyp() {
		super();
	}

	public MotorTyp(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}