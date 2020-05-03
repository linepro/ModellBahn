package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.base.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Bahnverwaltung. Represents a Railway company.
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.BAHNVERWALTUNG)
@Table(name = DBNames.BAHNVERWALTUNG, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
public class Bahnverwaltung extends NamedItemImpl {

    /**
     * Instantiates a new bahnverwaltung.
     */
    public Bahnverwaltung() {
        super();
    }

    public Bahnverwaltung(String name) {
        super(name);
    }

    /**
     * Instantiates a new bahnverwaltung.
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
    public Bahnverwaltung(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}