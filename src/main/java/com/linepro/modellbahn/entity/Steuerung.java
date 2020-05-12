package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Steuerung. The steering method for a product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.STEUERUNG)
@Table(name = DBNames.STEUERUNG,
    indexes = { 
        @Index(name = DBNames.STEUERUNG + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.STEUERUNG + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Steuerung extends NamedItemImpl {

    /**
     * Instantiates a new steuerung.
     */
    public Steuerung() {
        super();
    }

    public Steuerung(String name) {
        super(name);
    }

    /**
     * Instantiates a new steuerung.
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
    public Steuerung(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}