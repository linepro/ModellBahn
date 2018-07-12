/*
 * AdressTyp
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.linepro.modellbahn.model.IAdressTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

/**
 * The Class AdressTyp represents an address type (DCC, MM, FX..)
 */
@Entity
@Table(name = "ADRESSTYPEN", indexes = { @Index(columnList = "NAME", unique = true) })
public class AdressTyp extends AbstractNamedItem implements IAdressTyp {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3760397596490333655L;

    /**
     * Instantiates a new adress typ.
     */
    public AdressTyp() {
		super();
	}

	/**
	 * Instantiates a new adress typ.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public AdressTyp(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}

    public AdressTyp(IAdressTyp typ) {
        this(typ.getId(), typ.getName(), typ.getBezeichnung(), typ.getDeleted());
    }
}