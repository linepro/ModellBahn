package com.linepro.modellbahn.model;

import java.util.Set;

import com.linepro.modellbahn.model.keys.NameKey;

/**
 * IZug.
 * @author   $Author$
 * @version  $Id$
 */
public interface IZug extends INamedItem<NameKey> {

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    IZugTyp getZugTyp();

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    void setZugTyp(IZugTyp typ);

    /**
     * Gets the consist.
     *
     * @return the consist
     */
    Set<IZugConsist> getConsist();

    /**
     * Sets the consist.
     *
     * @param consist the new consist
     */
    void setConsist(Set<IZugConsist> consist);

    void addConsist(IZugConsist consist);

    void removeConsist(IZugConsist consist);

}