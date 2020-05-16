package com.linepro.modellbahn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Kategorie. The category for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.KATEGORIE)
@Table(name = DBNames.KATEGORIE,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.KATEGORIE + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class Kategorie extends NamedItemImpl {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.KATEGORIE, targetEntity = UnterKategorie.class, orphanRemoval = true)
    private Set<UnterKategorie> unterKategorien;

    public void addUnterKategorie(UnterKategorie unterKategorie) {
        unterKategorie.setKategorie(this);
        unterKategorie.setDeleted(false);
        if (unterKategorien == null) { unterKategorien = new HashSet<>(); }
        unterKategorien.add(unterKategorie);
    }

    
    public void removeUnterKategorie(UnterKategorie unterKategorie) {
        unterKategorien.remove(unterKategorie);
    }

}