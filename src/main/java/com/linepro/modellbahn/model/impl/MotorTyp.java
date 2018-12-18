package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * MotorTyp. The (Märklin) motor type for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.MOTOR_TYP)
@Table(name = DBNames.MOTOR_TYP, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.MOTOR_TYP)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
public class MotorTyp extends AbstractNamedItem<NameKey> implements IMotorTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4407612983152968677L;

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
     *            the deleted
     */
    public MotorTyp(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}