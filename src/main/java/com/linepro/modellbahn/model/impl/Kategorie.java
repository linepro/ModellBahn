package com.linepro.modellbahn.model.impl;

import java.net.URI;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
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
@JsonRootName(value = ApiNames.KATEGORIE)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.UNTER_KATEGORIEN,
        ApiNames.LINKS })
public class Kategorie extends AbstractNamedItem<NameKey> implements IKategorie {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2964561580499221297L;

    private Set<IUnterKategorie> unterKategorien = new TreeSet<>();

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
     *            the deleted
     */
    public Kategorie(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }

    @Override
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.KATEGORIE, targetEntity = UnterKategorie.class, orphanRemoval = true)
    @JsonGetter(ApiNames.UNTER_KATEGORIEN)
    @JsonView(Views.Public.class)
    @JsonSerialize(contentUsing = UnterKategorieSerializer.class)
    public Set<IUnterKategorie> getUnterKategorien() {
        return unterKategorien;
    }

    @Override
    @JsonSetter(ApiNames.UNTER_KATEGORIEN)
    @JsonDeserialize(contentAs = UnterKategorie.class)
    public void setUnterKategorien(Set<IUnterKategorie> unterKategorien) {
        this.unterKategorien = unterKategorien;
    }

    @Override
    public void addUnterKategorie(IUnterKategorie unterKategorie) {
        unterKategorie.setKategorie(this);
        getUnterKategorien().add(unterKategorie);
    }

    @Override
    public void removeUnterKategorie(IUnterKategorie unterKategorie) {
        getUnterKategorien().remove(unterKategorie);
    }

    @Override
    protected void addChildLinks(URI root, boolean update, boolean delete) {
        addLinks(root, getUnterKategorien(), update, delete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append(ApiNames.UNTER_KATEGORIEN, getUnterKategorien())
                .toString();
    }
}