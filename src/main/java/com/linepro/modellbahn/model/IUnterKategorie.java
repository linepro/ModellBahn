package com.linepro.modellbahn.model;

/**
 * IUnterKategorie.
 * @author   $Author$
 * @version  $Id$
 */
public interface IUnterKategorie extends INamedItem {

    /**
     * Gets the kategorie.
     *
     * @return the kategorie
     */
    IKategorie getKategorie();

    /**
     * Sets the kategorie.
     *
     * @param kategorie the new kategorie
     */
    void setKategorie(IKategorie kategorie);
}