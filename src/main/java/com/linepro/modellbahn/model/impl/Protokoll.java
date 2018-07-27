package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Protokoll. The communications protocol used by a Decoder
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "Protokoll")
@Table(name = "Protokoll", indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.PROTOKOLL)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAME, ApiNames.DESCRIPTION, ApiNames.DELETED, ApiNames.LINKS })
public class Protokoll extends AbstractNamedItem implements IProtokoll {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2601992994975232884L;

    /**
     * Instantiates a new protokoll.
     */
    public Protokoll() {
        super();
    }

    public Protokoll(String name) {
        super(name);
    }

    /**
     * Instantiates a new protokoll.
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
    public Protokoll(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}