/*
 * IKategorie
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

import java.util.Set;

/**
 * IKategorie.
 * @author   $Author$
 * @version  $Id$
 */
public interface IKategorie extends INamedItem {
    
    /**
     * Gets the unter kategorien.
     *
     * @return the unter kategorien
     */
    Set<IUnterKategorie> getUnterKategorien();

    /**
     * Sets the unter kategorie.
     *
     * @param unterKategorien the new unter kategorie
     */
    void setUnterKategorien(Set<IUnterKategorie> unterKategorien);

    void addUnterKategorie(IUnterKategorie unterKategorie);

    void removeUnterKategorie(IUnterKategorie unterKategorie);
}