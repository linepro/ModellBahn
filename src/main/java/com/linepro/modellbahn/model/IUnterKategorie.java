package com.linepro.modellbahn.model;
import com.linepro.modellbahn.model.keys.UnterKategorieKey;

/**
 * IUnterKategorie.
 * @author   $Author$
 * @version  $Id$
 */
public interface IUnterKategorie extends INamedItem<UnterKategorieKey> {

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