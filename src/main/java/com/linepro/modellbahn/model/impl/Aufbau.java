package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * Aufbau.
 * Represents a construction method.
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Aufbau")
@Table(name = "aufbauten", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonRootName(value = "construction")
public class Aufbau extends AbstractNamedItem implements IAufbau {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 233985591709407388L;

    /**
     * Instantiates a new aufbau.
     */
    public Aufbau() {
		super();
	}

	public Aufbau(String name) {
        super(name);
    }

    /**
	 * Instantiates a new aufbau.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public Aufbau(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}