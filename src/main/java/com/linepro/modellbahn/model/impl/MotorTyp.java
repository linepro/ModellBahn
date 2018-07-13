package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "MOTORTYPEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class MotorTyp extends AbstractNamedItem implements IMotorTyp {

    private static final long serialVersionUID = -4407612983152968677L;

    public MotorTyp() {
		super();
	}

    @JsonCreator
	public MotorTyp(@JsonProperty(value="id", required=false) Long id, @JsonProperty(value="name", required=false) String name, @JsonProperty(value="description", required=false) String bezeichnung, @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}