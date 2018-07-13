/*
 * Achsfolg
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * The Class Achsfolg represents Axle configuration using German nomenclature.
 */
@Entity
@Table(name = "achsfolgen", indexes = { @Index(columnList = "name", unique = true) })
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
    @JsonCreator
	public Achsfolg(@JsonProperty(value="id", required=false) Long id, 
	                @JsonProperty(value="name", required=false) String name, 
	                @JsonProperty(value="description", required=false) String bezeichnung, 
	                @JsonProperty(value="deleted", required=false) Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}