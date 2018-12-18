package com.linepro.modellbahn.model;

import java.nio.file.Path;

import com.linepro.modellbahn.model.keys.NameKey;

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