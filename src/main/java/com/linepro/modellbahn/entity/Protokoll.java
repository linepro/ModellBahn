package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Protokoll. The communications protocol used by a Decoder
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PROTOKOLL)
@Table(name = DBNames.PROTOKOLL,
    indexes = { 
        @Index(name = DBNames.PROTOKOLL + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PROTOKOLL + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Protokoll extends NamedItemImpl {

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
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Protokoll(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}