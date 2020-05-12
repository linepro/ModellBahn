package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Gattung. The Gattung (or Baureihe) for product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.GATTUNG)
@Table(name = DBNames.GATTUNG,
    indexes = { 
        @Index(name = DBNames.GATTUNG + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.GATTUNG + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Gattung extends NamedItemImpl {

    /**
     * Instantiates a new gattung.
     */
    public Gattung() {
        super();
    }

    public Gattung(String name) {
        super(name);
    }

    /**
     * Instantiates a new gattung.
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
    public Gattung(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}