package com.linepro.modellbahn.model.util;

import com.linepro.modellbahn.model.refs.IDescribedEnum;

/**
 * Status.
 * The status of an article
 * @author   $Author$
 * @version  $Id$
 */
public enum Status implements IDescribedEnum {
	
    WUNSCHMODEL("A wanted model."),
	
	GEKAUFT("Purchased."),
	
	BASTLER("For spares and repairs."),
	
   VERKAUFT("Sold.");

    private final String description;
    
    Status(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getBezeichnung() {
        return description;
    }
}