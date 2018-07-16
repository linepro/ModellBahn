package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "unter_kategorien", indexes = { @Index(columnList = "kategorie_id,name", unique = true),
        @Index(columnList = "kategorie_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "kategorie_id", "name" }) })
public class UnterKategorie extends AbstractNamedItem implements IUnterKategorie {

    private static final long serialVersionUID = 5346529720680464691L;

    private IKategorie kategorie;

    public UnterKategorie() {
        super();
    }

    public UnterKategorie(Long id, String name, IKategorie kategorie, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        this.kategorie = kategorie;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kategorie.class)
    @JoinColumn(name = "kategorie_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "unterkategorie_fk1"))
    @JsonProperty("kategorie")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IKategorie getKategorie() {
        return kategorie;
    }

    @Override
    public void setKategorie(IKategorie kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
                .append("kategorie", getKategorie()).toString();
    }
}