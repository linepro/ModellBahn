package com.linepro.modellbahn.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Kategorie. The category for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.KATEGORIE)
@Table(name = DBNames.KATEGORIE, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
public class Kategorie extends NamedItemImpl {

    private Set<UnterKategorie> unterKategorien;

    /**
     * Instantiates a new kategorie.
     */
    public Kategorie() {
        super();
    }

    public Kategorie(String name) {
        super(name);
    }

    /**
     * Instantiates a new kategorie.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Kategorie(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.KATEGORIE, targetEntity = UnterKategorie.class, orphanRemoval = true)
    public Set<UnterKategorie> getUnterKategorien() {
        return unterKategorien;
    }

    
    @Transient
    public Set<UnterKategorie> getSortedUnterKategorien() {
        return new TreeSet<UnterKategorie>(getUnterKategorien());
    }

    
    public void setUnterKategorien(Set<UnterKategorie> unterKategorien) {
        this.unterKategorien = unterKategorien;
    }

    
    public void addUnterKategorie(UnterKategorie unterKategorie) {
        unterKategorie.setKategorie(this);
        getUnterKategorien().add(unterKategorie);
    }

    
    public void removeUnterKategorie(UnterKategorie unterKategorie) {
        getUnterKategorien().remove(unterKategorie);
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.UNTER_KATEGORIEN, getUnterKategorien())
                .toString();
    }
}