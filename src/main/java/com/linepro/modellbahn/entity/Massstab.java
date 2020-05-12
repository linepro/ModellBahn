package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Massstab. The NEM 010 Maßstäb (scale) of a product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.MASSSTAB)
@Table(name = DBNames.MASSSTAB,
    indexes = { 
        @Index(name = DBNames.MASSSTAB + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.MASSSTAB + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Massstab extends NamedItemImpl {

    /**
     * Instantiates a new massstab.
     */
    public Massstab() {
        super();
    }

    public Massstab(String name) {
        super(name);
    }

    /**
     * Instantiates a new massstab.
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
    public Massstab(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}