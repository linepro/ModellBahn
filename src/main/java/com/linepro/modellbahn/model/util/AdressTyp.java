package com.linepro.modellbahn.model.util;

/**
 * AdressTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
public enum AdressTyp {

    /** A DCC address. */
    DCC,
    
    /** A DCC short address. */
    DCC_SHORT,
    
    /** A Märklin DELTA (locomotive) address. */
    DELTA,
    
    /** A Märklin Motorola (locomotive) address (version 1 or 2). */
    MM,
    
    /** A Märklin Digital (locomotive) address (fx or mfx). */
    DIGITAL,
    
    /** A Märklin Motorola accessory address. */
    WEICHE;
}
