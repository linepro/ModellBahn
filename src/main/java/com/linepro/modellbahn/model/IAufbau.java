package com.linepro.modellbahn.model;
import java.nio.file.Path;

import com.linepro.modellbahn.model.keys.NameKey;

/**
 * IAufbau.
 * @author   $Author$
 * @version  $Id$
 */
public interface IAufbau extends INamedItem<NameKey> {

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