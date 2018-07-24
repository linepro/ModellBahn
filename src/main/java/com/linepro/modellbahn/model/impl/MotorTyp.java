package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * MotorTyp.
 * The (Märklin) motor type for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "MotorTyp")
@Table(name = "motortypen", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class MotorTyp extends AbstractNamedItem implements IMotorTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4407612983152968677L;

    /**
     * Instantiates a new motor typ.
     */
    public MotorTyp() {
		super();
	}

    public MotorTyp(String name) {
        super(name);
    }

    /**
	 * Instantiates a new motor typ.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public MotorTyp( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}