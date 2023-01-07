package com.linepro.modellbahn.entity;

import static com.linepro.modellbahn.util.ToStringBuilder.summary;

import java.io.Serializable;
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
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Unique;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @NamedEntityGraph(name="zug.noChildren",
        attributeNodes = {
            @NamedAttributeNode(value = "name"),
            @NamedAttributeNode(value = "zugTyp", subgraph = "zug.zugTyp"),
            @NamedAttributeNode(value = "consist", subgraph = "zug.consist"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "deleted")
        }, 
        subgraphs = {
            @NamedSubgraph(name = "zug.zugTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "zug.consist",
            attributeNodes = {
                @NamedAttributeNode(value = "position"),
                @NamedAttributeNode(value = "artikel", subgraph = "zug.artikel")
            }),
            @NamedSubgraph(name = "zug.artikel",
            attributeNodes = {
                @NamedAttributeNode(value = "produkt", subgraph = "zug.produkt")
            }),
            @NamedSubgraph(name = "zug.produkt",
                attributeNodes = {
                    @NamedAttributeNode(value = "lange")
            })
        }),
    @NamedEntityGraph(name="zug.withChildren",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "zugTyp", subgraph = "zug.zugTyp"),
            @NamedAttributeNode(value = "consist", subgraph = "zug.consist")
        },
        subgraphs = {
            @NamedSubgraph(name = "zug.zugType",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "zug.consist",
                attributeNodes = {
                    @NamedAttributeNode(value = "position"),
                    @NamedAttributeNode(value = "artikel", subgraph = "zug.artikel")
            }),
            @NamedSubgraph(name = "zug.artikel",
                attributeNodes = {
                    @NamedAttributeNode(value = "artikelId"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "produkt", subgraph = "zug.produkt"),
                    @NamedAttributeNode(value = "abbildung")
            }),
            @NamedSubgraph(name = "zug.produkt",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "zug.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "lange"),
                    @NamedAttributeNode(value = "unterKategorie", subgraph = "zug.unterkategorie"),
                    @NamedAttributeNode(value = "bahnverwaltung", subgraph = "zug.bahnverwaltung"),
                    @NamedAttributeNode(value = "gattung", subgraph = "zug.gattung"),
                    @NamedAttributeNode(value = "betreibsnummer"),
                    @NamedAttributeNode(value = "abbildung")
            }),
            @NamedSubgraph(name = "zug.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "zug.unterkategorie",
            attributeNodes = {
                @NamedAttributeNode(value = "kategorie", subgraph = "zug.kategorie"),
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "zug.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "zug.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "zug.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.zug.notunique}")
public class Zug extends NamedItemImpl implements Serializable {

    private static final long serialVersionUID = -8230873387981532381L;

    /** The zugTyp. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ZugTyp.class, optional = false)
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

        int position = getConsist().stream()
                                   .mapToInt(c -> c.getPosition())
                                   .max()
                                   .orElse(0);

        fahrzeug.setZug(this);
        fahrzeug.setPosition(position + 1);
        fahrzeug.setDeleted(false);
        consist.add(fahrzeug);
    }

    public void removeConsist(ZugConsist farhzeug) {
        consist.remove(farhzeug);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("zugTyp", summary(zugTyp))
            .append("consist", consist)
            .toString();
    }
}
