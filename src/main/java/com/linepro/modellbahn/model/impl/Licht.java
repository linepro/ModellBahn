package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * Licht.
 * The NEM 006 / Märklin light configuration for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Licht")
@Table(name = "lichten", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Licht extends AbstractNamedItem implements ILicht {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6288751316098975414L;

    /**
     * Instantiates a new licht.
     */
    public Licht() {
		super();
	}

    public Licht(String name) {
        super(name);
    }

    /**
	 * Instantiates a new licht.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public Licht( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}