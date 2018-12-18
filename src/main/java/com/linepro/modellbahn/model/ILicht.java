package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.NameKey;

import java.nio.file.Path;

/**
 * ILicht.
 * @author   $Author$
 * @version  $Id$
 */
public interface ILicht extends INamedItem<NameKey> {

    /**
     * Gets the abbildung.
     *
     * @return the abbildung
     */
    Path getAbbildung();

    /**
     * Sets the abbildung.
     *
     * @param abbildung
     *            the new abbildung
     */
    void setAbbildung(Path abbildung);
}