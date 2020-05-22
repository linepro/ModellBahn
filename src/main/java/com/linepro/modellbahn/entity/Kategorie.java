package com.linepro.modellbahn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.Builder;
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
@NamedEntityGraphs({
    @NamedEntityGraph(name="withChildren", includeAllAttributes = true, subgraphs = {
         @NamedSubgraph(name = "unterkategorien", attributeNodes = {
             @NamedAttributeNode(DBNames.ID),
             @NamedAttributeNode(DBNames.KATEGORIE_ID),
             @NamedAttributeNode(DBNames.NAME),
             @NamedAttributeNode(DBNames.BEZEICHNUNG),
             @NamedAttributeNode(DBNames.DELETED)
         })
    })
})
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Cacheable
public class Kategorie extends NamedItemImpl {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.KATEGORIE, targetEntity = UnterKategorie.class, orphanRemoval = true)
    @Builder.Default
    private Set<UnterKategorie> unterKategorien = new HashSet<>();

    public void addUnterKategorie(UnterKategorie unterKategorie) {
        if (unterKategorien == null) {
            unterKategorien = new HashSet<>();
        }
        unterKategorie.setKategorie(this);
        unterKategorie.setDeleted(false);
        unterKategorien.add(unterKategorie);
    }

    
    public void removeUnterKategorie(UnterKategorie unterKategorie) {
        unterKategorien.remove(unterKategorie);
    }
}