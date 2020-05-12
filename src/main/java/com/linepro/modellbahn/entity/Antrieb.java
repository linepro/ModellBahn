package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Antrieb. Represents a drive method.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ANTRIEB)
@Table(name = DBNames.ANTRIEB,
    indexes = { 
        @Index(name = DBNames.ANTRIEB + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ANTRIEB + "_UC1", columnNames = { DBNames.NAME }) 
    })
//@formatter:on
public class Antrieb extends NamedItemImpl {

    /**
     * Instantiates a new antrieb.
     */
    public Antrieb() {
        super();
    }

    public Antrieb(String name) {
        super(name);
    }

    /**
     * Instantiates a new antrieb.
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
    public Antrieb(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}