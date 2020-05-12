package com.linepro.modellbahn.entity;

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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * UnterKategorie.
 * The sub category for a product
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.UNTER_KATEGORIE)
@Table(name = DBNames.UNTER_KATEGORIE,
    indexes = { 
        @Index(name = DBNames.UNTER_KATEGORIE + "_IX1", columnList = DBNames.KATEGORIE_ID +"," + DBNames.NAME, unique = true),
        @Index(name = DBNames.UNTER_KATEGORIE + "_IX2", columnList = DBNames.KATEGORIE_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.UNTER_KATEGORIE + "_UC1", columnNames = { DBNames.KATEGORIE_ID, DBNames.NAME })
    })
@AttributeOverride(name = DBNames.NAME, column = @Column(name = DBNames.NAME, length = 50))
//@formatter:on
public class UnterKategorie extends NamedItemImpl {

    /** The kategorie. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.kategorie.notnull}")
    private Kategorie kategorie;

    /**
     * Instantiates a new unter kategorie.
     */
    public UnterKategorie() {
        super();
    }

    public UnterKategorie(Kategorie kategorie, String name) {
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
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public UnterKategorie(Long id, Kategorie kategorie, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setKategorie(kategorie);
    }

    @BusinessKey
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Kategorie.class)
    @JoinColumn(name = DBNames.KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.UNTER_KATEGORIE + "_fk1"))
    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getKategorie())
                .append(getName())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UnterKategorie)) {
            return false;
        }

        UnterKategorie other = (UnterKategorie) obj;

        return new EqualsBuilder()
                .append(getKategorie(), other.getKategorie())
                .append(getName(), other.getName())
                .isEquals();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.KATEGORIE, getKategorie())
                .toString();
    }

    public int compareTo(NamedItemImpl other) {
        if (other instanceof UnterKategorie) {
            return new CompareToBuilder()
                .append(getKategorie().getName(), ((UnterKategorie) other).getKategorie().getName())
                .append(getName(), ((UnterKategorie) other).getName())
                .toComparison();
        }
        
        return super.compareTo(other);
    }
}