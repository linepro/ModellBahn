package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * ZugTyp. The type of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ZUG_TYP)
@Table(name = DBNames.ZUG_TYP,
    indexes = { 
            @Index(name = DBNames.ZUG_TYP + "_IX1", columnList = DBNames.NAME, unique = true) 
        }, uniqueConstraints = {
            @UniqueConstraint(name = DBNames.ZUG_TYP + "_UC1", columnNames = { DBNames.NAME }) 
        })
//@formatter:on
public class ZugTyp extends NamedItemImpl {

    /**
     * Instantiates a new zug typ.
     */
    public ZugTyp() {
        super();
    }

    public ZugTyp(String name) {
        super(name);
    }

    /**
     * Instantiates a new zug typ.
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
    public ZugTyp(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}