package com.linepro.modellbahn.entity;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.URLConverter;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Country;

/**
 * Hersteller. The manufacturer for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.HERSTELLER)
@Table(name = DBNames.HERSTELLER,
    indexes = { 
        @Index(name = DBNames.HERSTELLER + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.HERSTELLER + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
public class Hersteller extends NamedItemImpl {

    /** The url. */
    //@org.hibernate.validator.constraints.URL
    private URL url;

    /** The Telefon. */
    //@Telephone(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String Telefon;

    @Country(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String land;

    /**
     * Instantiates a new hersteller.
     */
    public Hersteller() {
        super();
    }

    public Hersteller(String name) {
        super(name);
    }

    /**
     * Instantiates a new hersteller.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param url
     *            the url
     * @param telefon
     *            the telefon
     * @param deleted
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Hersteller(Long id, String name, String bezeichnung, URL url, String telefon, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setUrl(url);
        setTelefon(telefon);
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    
    @Column(name = DBNames.URL)
    @Convert(converter = URLConverter.class)
    public URL getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param uRL
     *            the new url
     */
    
    public void setUrl(URL uRL) {
        url = uRL;
    }

    /**
     * Gets the telefon.
     *
     * @return the telefon
     */
    
    @Column(name = DBNames.TELEFON, length = 20)
    public String getTelefon() {
        return Telefon;
    }

    /**
     * Sets the telefon.
     *
     * @param telefon
     *            the new telefon
     */
    
    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    
    
    @Column(name = DBNames.LAND, length = 2)
    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.URL, getUrl())
                .append(DBNames.TELEFON, getTelefon())
                .toString();
    }
}