package com.linepro.modellbahn.model.impl;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * UnterKategorie.
 * The sub category for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "UnterKategorie")
@Table(name = "unter_kategorien", indexes = { @Index(columnList = "kategorie_id,name", unique = true),
        @Index(columnList = "kategorie_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "kategorie_id", "name" }) })
@AttributeOverride(name = "name", column = @Column(name = "name", unique = false, length = 50))
public class UnterKategorie extends AbstractNamedItem implements IUnterKategorie {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5346529720680464691L;

    /** The kategorie. */
    private IKategorie kategorie;

    /**
     * Instantiates a new unter kategorie.
     */
    public UnterKategorie() {
        super();
    }

    public UnterKategorie(IKategorie kategorie, String name) {
        super(name);
       
        setKategorie(kategorie);
    }

    /**
     * Instantiates a new unter kategorie.
     *
     * @param id the id
     * @param kategorie the kategorie
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param deleted the deleted
     */
    public UnterKategorie(Long id, IKategorie kategorie, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setKategorie(kategorie);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kategorie.class)
    @JoinColumn(name = "kategorie_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "unterkategorie_fk1"))
    @JsonView(Views.DropDown.class)
    @JsonGetter("kategorie")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IKategorie getKategorie() {
        return kategorie;
    }

    @Override
    @JsonSetter("kategorie")
    public void setKategorie(IKategorie kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("kategorie", getKategorie())
                .toString();
    }
}