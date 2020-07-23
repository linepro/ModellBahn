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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
 * ProduktTeil.
 * Part of a product (Bill of materials).
 * E.g. Locomotives and Carriages in a set
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PRODUKT_TEIL)
@Table(name = DBNames.PRODUKT_TEIL,
    indexes = { 
        @Index(name = DBNames.PRODUKT_TEIL + "_IX1", columnList = DBNames.PRODUKT_ID),
        @Index(name = DBNames.PRODUKT_TEIL + "_IX2", columnList = DBNames.TEIL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PRODUKT_TEIL + "_UC1", columnNames = { DBNames.PRODUKT_ID, DBNames.TEIL_ID })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="produktTeil",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "produkt", subgraph = "produktTeil.produkt"),
            @NamedAttributeNode(value = "teil", subgraph = "produktTeil.teil")
        }, subgraphs = {
            @NamedSubgraph(name = "produktTeil.produkt",
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "hersteller", subgraph = "produkt.hersteller"),
                    @NamedAttributeNode(value = "bestellNr")
            }),
            @NamedSubgraph(name = "produkt.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "produktTeil.teil",
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "hersteller", subgraph = "teil.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "unterKategorie", subgraph = "teil.unterKategorie")
            }),
            @NamedSubgraph(name = "teil.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "teil.unterKategorie",
                attributeNodes = {
                        @NamedAttributeNode(value = "kategorie", subgraph = "teil.kategorie"),
                        @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "teil.kategorie",
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
public class ProduktTeil extends ItemImpl implements Comparable<ProduktTeil> {
    
    /** The produkt. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The component produkt */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.TEIL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.teil.notnull}")
    private Produkt teil;
    
    /** The count of this component. */
    @Column(name = DBNames.ANZAHL, nullable = false)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anzahl.positive}")
    private Integer anzahl;

    @Override
    public int compareTo(ProduktTeil other) {
        return new CompareToBuilder()
            .append(getTeil(), other.getTeil())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getProdukt().hashCode())
          .append(getTeil().hashCode())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof ProduktTeil)) {
        return false;
      }

      ProduktTeil other = (ProduktTeil) obj;

      return new EqualsBuilder()
          .append(getProdukt(), other.getProdukt())
          .append(getTeil(), other.getTeil())
          .isEquals();
    }
}
