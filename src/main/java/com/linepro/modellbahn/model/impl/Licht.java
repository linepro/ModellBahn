package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Licht. The NEM 006 / Märklin light configuration for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "Licht")
@Table(name = "Licht", indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.LICHT)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAME, ApiNames.DESCRIPTION, ApiNames.DELETED, ApiNames.LINKS })
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
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     */
    public Licht(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}