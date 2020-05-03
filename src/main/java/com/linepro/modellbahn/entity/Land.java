package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Land. The country of a product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.LAND)
@Table(name = DBNames.LAND, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
public class Land extends NamedItemImpl {

    /** The wahrung. */
    private Wahrung wahrung;

    /**
     * Instantiates a new land.
     */
    public Land() {
        super();
    }

    public Land(String name) {
        super(name);
    }

    /**
     * Instantiates a new land.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param wahrung
     *            the wahrung
     * @param deleted
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Land(Long id, String name, String bezeichnung, Wahrung wahrung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setWahrung(wahrung);
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Wahrung.class)
    public Wahrung getWahrung() {
        return wahrung;
    }

    
    public void setWahrung(Wahrung wahrung) {
        this.wahrung = wahrung;
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.WAHRUNG, getWahrung())
                .toString();
    }
}