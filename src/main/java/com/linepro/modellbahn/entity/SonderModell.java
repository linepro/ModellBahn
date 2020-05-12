package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * SonderModell. Special category for a product. E.g. MHI, Einmaligeserien
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.SONDERMODELL)
@Table(name = DBNames.SONDERMODELL,
    indexes = { 
        @Index(name = DBNames.SONDERMODELL + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.SONDERMODELL + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class SonderModell extends NamedItemImpl {

    /**
     * Instantiates a new sonder modell.
     */
    public SonderModell() {
        super();
    }

    public SonderModell(String name) {
        super(name);
    }

    /**
     * Instantiates a new sonder modell.
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
    public SonderModell(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}