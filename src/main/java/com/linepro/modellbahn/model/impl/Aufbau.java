package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Aufbau. Represents a construction method.
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.AUFBAU)
@Table(name = DBNames.AUFBAU, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.AUFBAU)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAME, ApiNames.DESCRIPTION, ApiNames.DELETED, ApiNames.LINKS })
public class Aufbau extends AbstractNamedItem<NameKey> implements IAufbau {

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
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     */
    public Aufbau(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}