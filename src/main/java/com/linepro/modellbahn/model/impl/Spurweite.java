package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * Spurweite.
 * Represents a NEM 010 Spurweite (track guage)
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Spurweite")
@Table(name = "spurweiten", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Spurweite extends AbstractNamedItem implements ISpurweite {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3673395807313729165L;

    /**
     * Instantiates a new spurweite.
     */
    public Spurweite() {
		super();
	}

    public Spurweite(String name) {
        super(name);
    }

    /**
	 * Instantiates a new spurweite.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public Spurweite( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}