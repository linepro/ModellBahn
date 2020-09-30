package com.linepro.modellbahn.util;

/**
 * Views.
 * Marker interfaces to control Json field serialization
 * @author   $Author$
 * @version  $Id$
 */
public class Views {

    /**
     * DropDown.
     * Marks fields for basic serialization.
     */
    public interface DropDown {
    }

    /**
     * Public.
     * Marks fields for Public serialization; includes DropDown
     */
    public interface Public extends DropDown {
    }

    /**
     * Internal.
     * Marks fields for Internal (within the application) serialization; includes Public
     */
    public interface Internal extends Public {
    }

    public interface Csv {
    }
}