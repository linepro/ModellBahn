package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Kupplung.
 * The coupling configuration for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Kupplung")
@Table(name = "Kupplung", indexes = { @Index(columnList = ApiNames.NAME, unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { ApiNames.NAME }) })
@JsonRootName(value = ApiNames.KUPPLUNG)
@JsonPropertyOrder({ApiNames.ID, ApiNames.NAME, ApiNames.DESCRIPTION, ApiNames.DELETED, ApiNames.LINKS})
public class Kupplung extends AbstractNamedItem implements IKupplung {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3158490202101950479L;

    /**
     * Instantiates a new kupplung.
     */
    public Kupplung() {
		super();
	}

    public Kupplung(String name) {
        super(name);
    }

    /**
	 * Instantiates a new kupplung.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public Kupplung( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}