package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.ILand;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Land.
 * The country of a product.
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Land")
@Table(name = "lander", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Land extends AbstractNamedItem implements ILand {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5352015940349871580L;

    /** The wahrung. */
    private IWahrung wahrung;

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
     * @param id the id
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param wahrung the wahrung
     * @param deleted the deleted
     */
    public Land(Long id, String name, String bezeichnung, IWahrung wahrung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setWahrung(wahrung);
    }

    @Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Wahrung.class)
    @JsonGetter("wahrung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IWahrung getWahrung() {
        return wahrung;
    }

    @Override
    @JsonSetter("wahrung")
    public void setWahrung(IWahrung wahrung) {
        this.wahrung = wahrung;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("wahrung", getWahrung())
                .toString();
    }
}