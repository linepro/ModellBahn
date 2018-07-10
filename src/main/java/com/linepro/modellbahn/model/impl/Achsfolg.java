/*
 * Achsfolg
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * The Class Achsfolg represents Axle configuration using German nomenclature.
 */
@Entity
@Table(name = "ACHSFOLGEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class Achsfolg extends AbstractNamedItem implements IAchsfolg {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 210058293067560474L;

    /**
     * Instantiates a new achsfolg.
     */
    public Achsfolg() {
		super();
	}

	/**
	 * Instantiates a new achsfolg.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the description
	 * @param deleted if true this achsfold is soft deleted
	 */
	public Achsfolg(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}