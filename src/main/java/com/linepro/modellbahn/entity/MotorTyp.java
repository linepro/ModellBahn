package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * MotorTyp. The (Märklin) motor type for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.MOTOR_TYP)
@Table(name = DBNames.MOTOR_TYP,
    indexes = { 
        @Index(name = DBNames.MOTOR_TYP + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.MOTOR_TYP + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class MotorTyp extends NamedItemImpl {

    /**
     * Instantiates a new motor typ.
     */
    public MotorTyp() {
        super();
    }

    public MotorTyp(String name) {
        super(name);
    }

    /**
     * Instantiates a new motor typ.
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
    public MotorTyp(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}