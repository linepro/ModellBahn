package com.linepro.modellbahn.model.util;

import com.linepro.modellbahn.model.refs.IDescribedEnum;

/**
 * AdressTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
public enum AdressTyp implements IDescribedEnum {

    DCC("A DCC address 0 - 10239."),
    
    DCC_SHORT("A DCC short address 1 - 27."),
    
    DELTA("A Märklin DELTA (locomotive) address 2,6,8,18,20,24,26,54,56,60,62,72,74,78,80."),
    
    MM("A Märklin Motorola (locomotive) address (version 1 or 2) 1 - 80."),
    
    DIGITAL("A Märklin Digital (locomotive) address (fx or mfx) 1 - 255."),
    
    WEICHE("A Märklin Motorola accessory address 1 - 256.");

    private final String description;
    
    AdressTyp(String description) {
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
