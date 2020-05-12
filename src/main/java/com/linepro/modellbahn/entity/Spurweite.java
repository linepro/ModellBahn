package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Spurweite. Represents a NEM 010 Spurweite (track guage)
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.SPURWEITE)
@Table(name = DBNames.SPURWEITE,
    indexes = { 
        @Index(name = DBNames.SPURWEITE + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.SPURWEITE + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Spurweite extends NamedItemImpl {

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
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Spurweite(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}