package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.base.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Kupplung. The coupling configuration for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.KUPPLUNG)
@Table(name = DBNames.KUPPLUNG, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
public class Kupplung extends NamedWithAbbildungImpl {

    /**
     * Instantiates a new kupplung.
     */
    public Kupplung() {
        super();
    }

    public Kupplung(String name) {
        super(name);
    }

    /**
     * Instantiates a new kupplung.
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
    public Kupplung(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}