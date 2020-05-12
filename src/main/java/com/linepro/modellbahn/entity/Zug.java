package com.linepro.modellbahn.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Zug.
 * A train
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ZUG)
@Table(name = DBNames.ZUG,
    indexes = {
        @Index(name = DBNames.ZUG + "_IX1", columnList = DBNames.NAME, unique = true),
        @Index(name = DBNames.ZUG + "_IX2", columnList = DBNames.ZUG_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ZUG + "_UC1", columnNames = {DBNames.NAME})
    })
//@formatter:on
public class Zug extends NamedItemImpl {

    /** The zugTyp. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.zugTyp.notnull}")
    private ZugTyp zugTyp;

    /** The consist. */
    private Set<ZugConsist> consist;

    /**
     * Instantiates a new zug.
     */
    public Zug() {
        super();
    }

    public Zug(String name) {
        super(name);
    }

    /**
     * Instantiates a new zug.
     * @param id the id
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param zugTyp the zugTyp
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public Zug(Long id, String name, String bezeichnung, ZugTyp zugTyp, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        setZugTyp(zugTyp);
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ZugTyp.class)
    @JoinColumn(name = DBNames.ZUG_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG + "_fk1"))
    public ZugTyp getZugTyp() {
        return zugTyp;
    }

    
    public void setZugTyp(ZugTyp zugTyp) {
        this.zugTyp = zugTyp;
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.ZUG, targetEntity = ZugConsist.class, orphanRemoval = true)
    public Set<ZugConsist> getConsist() {
        return consist;
    }

    
    @Transient
    public Set<ZugConsist> getSortedConsist() {
        return new TreeSet<>(getConsist());
    }

    
    public void setConsist(Set<ZugConsist> consist) {
        this.consist = consist;
    }

    
    public void addConsist(ZugConsist consist) {
        // Add at end semantics
        consist.setZug(this);
        consist.setPosition(getConsist().size() + 1);
        consist.setDeleted(false);

        getConsist().add(consist);
    }

    
    public void removeConsist(ZugConsist consist) {
        getConsist().remove(consist);

        // Just renumber the whole lot; don't try and work out from where - it's just as expensive
        /*
         * int position = 1;
         * for (ZugConsist zc : getConsist()) {
         * zc.setPosition(position++);
         * }
         */
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.ZUG_TYP, getZugTyp())
                .append(DBNames.ZUG_CONSIST, getConsist())
                .toString();
    }
}
