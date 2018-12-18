package com.linepro.modellbahn.model.impl;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Spurweite. Represents a NEM 010 Spurweite (track guage)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.SPURWEITE)
@Table(name = DBNames.SPURWEITE, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.SPURWEITE)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
public class Spurweite extends AbstractNamedItem<NameKey> implements ISpurweite {

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
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     */
    public Spurweite(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}