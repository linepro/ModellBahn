package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Licht. The NEM 006 / Märklin light configuration for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.LICHT)
@Table(name = DBNames.LICHT,
    indexes = { 
        @Index(name = DBNames.LICHT + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.LICHT + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Licht extends NamedWithAbbildungImpl {

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
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Licht(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}