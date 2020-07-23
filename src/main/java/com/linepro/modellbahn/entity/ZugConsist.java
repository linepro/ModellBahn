package com.linepro.modellbahn.entity;

import javax.persistence.Column;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * ZugConsist. A component of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ZUG_CONSIST)
@Table(name = DBNames.ZUG_CONSIST,
    indexes = {
        @Index(name = DBNames.ZUG_CONSIST + "_IX1", columnList = DBNames.ZUG_ID),
        @Index(name = DBNames.ZUG_CONSIST + "_IX2", columnList = DBNames.ARTIKEL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ZUG_CONSIST + "_UC1", columnNames = { DBNames.ZUG_ID, DBNames.POSITION })
        })
@NamedEntityGraphs({
    @NamedEntityGraph(name="zugConsist",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "zug", subgraph = "zugConsist.zug"),
            @NamedAttributeNode(value = "artikel", subgraph = "zugConsist.artikel")
        }, subgraphs = {
            @NamedSubgraph(name = "zugConsist.zug",
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "zugConsist.artikel",
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "artikelId"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "produkt", subgraph = "zugConsist.produkt"),
                    @NamedAttributeNode(value = "abbildung")
            }),
            @NamedSubgraph(name = "zugConsist.produkt",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "zugConsist.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "lange"),
                    @NamedAttributeNode(value = "bahnverwaltung", subgraph = "zugConsist.bahnverwaltung"),
                    @NamedAttributeNode(value = "gattung", subgraph = "zugConsist.gattung"),
                    @NamedAttributeNode(value = "betreibsnummer"),
                    @NamedAttributeNode(value = "abbildung")
            }),
            @NamedSubgraph(name = "zugConsist.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "zugConsist.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "zugConsist.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Unique
public class ZugConsist extends ItemImpl implements Comparable<ZugConsist> {

    /** The zug. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zug.class)
    @JoinColumn(name = DBNames.ZUG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG_CONSIST + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.zug.notnull}")
    private Zug zug;

    /** The position. */
    @Column(name = DBNames.POSITION, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.position.notnull}")
    @Min(value=1, message = "{com.linepro.modellbahn.validator.constraints.position.positive}")
    private Integer position;

    /** The artikel. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG_CONSIST + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.artikel.notnull}")
    private Artikel artikel;

    @Override
    public int compareTo(ZugConsist other) {
        return new CompareToBuilder()
            .append(getZug().getName(), other.getZug().getName())
            .append(getPosition(), other.getPosition())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getPosition())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof ZugConsist)) {
        return false;
      }

      ZugConsist other = (ZugConsist) obj;

      return new EqualsBuilder()
          .append(getZug(), other.getZug())
          .append(getPosition(), other.getPosition())
          .isEquals();
    }
}
