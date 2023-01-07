package com.linepro.modellbahn.entity;

import static com.linepro.modellbahn.util.ToStringBuilder.summary;

import java.io.Serializable;

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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * ProduktDecoderTyp.
 * Decoder Types used by a product (Bill of materials).
 * E.g. c80
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PRODUKT_DECODER_TYP)
@Table(name = DBNames.PRODUKT_DECODER_TYP,
    indexes = { 
        @Index(name = DBNames.PRODUKT_DECODER_TYP + "_IX1", columnList = DBNames.PRODUKT_ID),
        @Index(name = DBNames.PRODUKT_DECODER_TYP + "_IX2", columnList = DBNames.DECODER_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PRODUKT_DECODER_TYP + "_UC1", columnNames = { DBNames.PRODUKT_ID, DBNames.DECODER_TYP_ID })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="produktDecoderTyp",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "produkt", subgraph = "produktDecoderTyp.produkt"),
            @NamedAttributeNode(value = "decoderTyp", subgraph = "produktDecoderTyp.decoderTyp")
        }, subgraphs = {
            @NamedSubgraph(name = "produktDecoderTyp.produkt",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "produkt.hersteller"),
                    @NamedAttributeNode(value = "bestellNr")
            }),
            @NamedSubgraph(name = "produkt.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "produktDecoderTyp.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoderTyp.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "decoderTyp.hersteller",
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
public class ProduktDecoderTyp extends ItemImpl implements Comparable<ProduktDecoderTyp>,  Serializable {

    private static final long serialVersionUID = -7794241214945101524L;

    /** The produkt. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class, optional = false)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_DECODER_TYP + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The decoder typ */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class, optional = false)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_DECODER_TYP + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    @Override
    public int compareTo(ProduktDecoderTyp other) {
        return new CompareToBuilder()
            .append(getDecoderTyp(), other.getDecoderTyp())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getProdukt().hashCode())
          .append(getDecoderTyp().hashCode())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof ProduktDecoderTyp)) {
        return false;
      }

      ProduktDecoderTyp other = (ProduktDecoderTyp) obj;

      return new EqualsBuilder()
          .append(getProdukt(), other.getProdukt())
          .append(getDecoderTyp(), other.getDecoderTyp())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("produkt", summary(produkt))
            .append("decoderTyp", summary(decoderTyp))
            .toString();
    }
}
