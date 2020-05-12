package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Country;

/**
 * Bahnverwaltung. Represents a Railway company.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.BAHNVERWALTUNG)
@Table(name = DBNames.BAHNVERWALTUNG, 
    indexes = { 
        @Index(name = DBNames.BAHNVERWALTUNG + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.BAHNVERWALTUNG + "_UC1", columnNames = { DBNames.NAME }) 
    })
//@formatter:on
public class Bahnverwaltung extends NamedItemImpl {

    @Country(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String land;

    /**
     * Instantiates a new bahnverwaltung.
     */
    public Bahnverwaltung() {
        super();
    }

    public Bahnverwaltung(String name) {
        super(name);
    }

    /**
     * Instantiates a new bahnverwaltung.
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
    public Bahnverwaltung(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
    
    @Column(name = DBNames.LAND, length = 2)
    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}