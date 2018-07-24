package com.linepro.modellbahn.model.util;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
public enum Konfiguration {
    
    /** The Decoder cannot be configured. */
    NONE,
    
    /** The Decoder can be configured by link (solder or jumper). */
    LINK,
    
    /** The Decoder can be configured by switches. */
    SWITCH,
    
    /** The Decoder can be configured by CV values. */
    CV;
}
