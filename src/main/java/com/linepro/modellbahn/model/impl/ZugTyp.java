package com.linepro.modellbahn.model.impl;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ZugTyp. The type of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.ZUG_TYP)
@Table(name = DBNames.ZUG_TYP, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.ZUG_TYP)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
public class ZugTyp extends AbstractNamedItem<NameKey> implements IZugTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2290449046107280442L;

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
     *            the deleted
     */
    public ZugTyp(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}