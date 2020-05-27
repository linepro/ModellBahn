package com.linepro.modellbahn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
        @Index(name = DBNames.ZUG + "_IX1", columnList = DBNames.ZUG_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ZUG + "_UC1", columnNames = {DBNames.NAME})
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="zug.summary",
        attributeNodes = {
            @NamedAttributeNode(value = "id"),
            @NamedAttributeNode(value = "name"),
            @NamedAttributeNode(value = "zugTyp", subgraph = "zug.zugTyp"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "deleted")
        }, 
        subgraphs = {
            @NamedSubgraph(name = "zug.zugTyp", attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "bezeichnung")
            })
        }),
    @NamedEntityGraph(name="zug.detail",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "zugTyp", subgraph = "zug.zugType"),
            @NamedAttributeNode(value = "consist", subgraph = "zug.consist")
        },
        subgraphs = {
            @NamedSubgraph(name = "zug.zugType", attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "bezeichnung")
                }),
            @NamedSubgraph(name = "zug.consist", attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "position"),
                @NamedAttributeNode(value = "artikel", subgraph = "consist.artikel")
                }),
            @NamedSubgraph(name = "consist.artikel", attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "produkt", subgraph = "consist.produkt"),
                @NamedAttributeNode(value = "bezeichnung"),
                @NamedAttributeNode(value = "anmerkung"),
                @NamedAttributeNode(value = "beladung"),
                @NamedAttributeNode(value = "abbildung")
            }),
            @NamedSubgraph(name = "consist.produkt", attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "hersteller", subgraph = "consist.hersteller"),
                @NamedAttributeNode(value = "bestellNr"),
                @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "consist.hersteller", attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "bezeichnung"),
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
public class Zug extends NamedItemImpl {

    /** The zugTyp. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ZugTyp.class)
    @JoinColumn(name = DBNames.ZUG_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.zugTyp.notnull}")
    private ZugTyp zugTyp;

    /** The consist. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.ZUG, targetEntity = ZugConsist.class, orphanRemoval = true)
    @Builder.Default
    private Set<ZugConsist> consist = new HashSet<>();
    
    public void addConsist(ZugConsist fahrzeug) {
        if (consist == null) { 
            consist = new HashSet<>();
        }

        fahrzeug.setZug(this);
        fahrzeug.setPosition(getConsist().stream()
                                         .mapToInt(c -> c.getPosition())
                                         .max()
                                         .orElse(1));
        fahrzeug.setDeleted(false);
        consist.add(fahrzeug);
    }

    public void removeConsist(ZugConsist farhzeug) {
        consist.remove(farhzeug);
    }
}
