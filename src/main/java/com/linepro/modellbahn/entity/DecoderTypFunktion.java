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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTypFunktion. The Functions available for a DecoderTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_FUNKTION)
@Table(name = DBNames.DECODER_TYP_FUNKTION,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_FUNKTION + "_IX1", columnList = DBNames.DECODER_TYP_ID),
        @Index(name = DBNames.DECODER_TYP_FUNKTION + "_IX2", columnList = DBNames.FUNKTION)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_FUNKTION + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.FUNKTION })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="decoderTypFunktion",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "decoderTyp", subgraph = "decoderTypFunktion.decoderTyp")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderTypFunktion.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoderTypFunktion.hersteller"),
                    @NamedAttributeNode(value = "bestellNr")
            }),
            @NamedSubgraph(name = "decoderTypFunktion.hersteller",
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
@Unique(message = "{com.linepro.modellbahn.validator.constraints.decoderTypFunktion.notunique}")
public class DecoderTypFunktion extends ItemImpl implements Comparable<DecoderTypFunktion> {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class, optional = false)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_FUNKTION + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    @Column(name = DBNames.FUNKTION, length = 4)
    @Pattern(regexp = "^" + DBNames.FUNKTION_PATTERN + "$", message = "{com.linepro.modellbahn.validator.constraints.funktion.invalid}")
    private String funktion;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    private String bezeichnung;

    /** The programmable. */
    @Column(name = DBNames.PROGRAMMABLE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.programmable.notnull}")
    private Boolean programmable;

    @Override
    public int compareTo(DecoderTypFunktion other) {
        // if FUNKTION_PATTERN changes change this; shame we can't attach the comparator to it....
        return new CompareToBuilder()
            .append(getFunktion().substring(0, 1), other.getFunktion().substring(0, 1))
            .append(Integer.valueOf(getFunktion().substring(1)), Integer.valueOf(other.getFunktion().substring(1)))
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoderTyp().hashCode())
          .append(getFunktion())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderTypFunktion)) {
        return false;
      }

      DecoderTypFunktion other = (DecoderTypFunktion) obj;

      return new EqualsBuilder()
          .append(getDecoderTyp(), other.getDecoderTyp())
          .append(getFunktion(), other.getFunktion())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("decoderTyp", decoderTyp)
            .append("funktion", funktion)
            .append("bezeichnung", bezeichnung)
            .append("programmable", programmable)
            .toString();
    }
}