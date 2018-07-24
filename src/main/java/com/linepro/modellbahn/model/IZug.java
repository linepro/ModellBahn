package com.linepro.modellbahn.model;

import java.util.List;

/**
 * IZug.
 * @author   $Author$
 * @version  $Id$
 */
public interface IZug extends INamedItem {

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
    List<IZugConsist> getConsist();

    /**
     * Sets the consist.
     *
     * @param consist the new consist
     */
    void setConsist(List<IZugConsist> consist);

}