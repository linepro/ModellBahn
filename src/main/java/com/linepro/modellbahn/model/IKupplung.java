package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.NameKey;

import java.nio.file.Path;

/**
 * IKupplung.
 * @author   $Author$
 * @version  $Id$
 */
public interface IKupplung extends INamedItem<NameKey> {

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