package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Bahnverwaltung.
 * Represents a Railway company. 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Bahnverwaltung")
@Table(name = "Bahnverwaltung", indexes = { @Index(columnList = ApiNames.NAME, unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { ApiNames.NAME }) })
@JsonRootName(value = ApiNames.BAHNVERWALTUNG)
@JsonPropertyOrder({ApiNames.ID,ApiNames.NAME,ApiNames.DESCRIPTION,ApiNames.DELETED, ApiNames.LINKS})
public class Bahnverwaltung extends AbstractNamedItem implements IBahnverwaltung {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1665511590535290700L;

    /**
     * Instantiates a new bahnverwaltung.
     */
    public Bahnverwaltung() {
		super();
	}

    public Bahnverwaltung(String name) {
        super(name);
    }

    /**
	 * Instantiates a new bahnverwaltung.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public Bahnverwaltung( Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}
}