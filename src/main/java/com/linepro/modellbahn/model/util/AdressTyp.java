package com.linepro.modellbahn.model.util;

import com.linepro.modellbahn.model.IDescribedEnum;

/**
 * AdressTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
public enum AdressTyp implements IDescribedEnum {

    DCC("A DCC address."),
    
    DCC_SHORT("A DCC short address."),
    
    DELTA("A Märklin DELTA (locomotive) address."),
    
    MM("A Märklin Motorola (locomotive) address (version 1 or 2)."),
    
    DIGITAL("A Märklin Digital (locomotive) address (fx or mfx)."),
    
    WEICHE("A Märklin Motorola accessory address.");

    protected final String description;
    
    private AdressTyp(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
