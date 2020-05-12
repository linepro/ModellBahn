package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Epoch. The NEM 800 Epoch for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.EPOCH)
@Table(name = DBNames.EPOCH,
    indexes = { 
        @Index(name = DBNames.EPOCH + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.EPOCH + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Epoch extends NamedItemImpl {

    /**
     * Instantiates a new epoch.
     */
    public Epoch() {
        super();
    }

    public Epoch(String name) {
        super(name);
    }

    /**
     * Instantiates a new epoch.
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
    public Epoch(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}