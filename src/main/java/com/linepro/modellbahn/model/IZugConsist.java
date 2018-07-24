package com.linepro.modellbahn.model;

/**
 * IZugConsist.
 * @author   $Author$
 * @version  $Id$
 */
public interface IZugConsist extends IItem {

    /**
     * Gets the zug.
     *
     * @return the zug
     */
    IZug getZug();

    /**
     * Sets the zug.
     *
     * @param zug the new zug
     */
    void setZug(IZug zug);

    /**
     * Gets the position.
     *
     * @return the position
     */
    Integer getPosition();

    /**
     * Sets the position.
     *
     * @param position the new position
     */
    void setPosition(Integer position);

    /**
     * Gets the artikel.
     *
     * @return the artikel
     */
    IArtikel getArtikel();

    /**
     * Sets the artikel.
     *
     * @param artikel the new artikel
     */
    void setArtikel(IArtikel artikel);

}