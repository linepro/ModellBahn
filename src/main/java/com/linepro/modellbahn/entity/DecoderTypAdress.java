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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.WithAdress;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Adress;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTypAdress.
 * The Adresss available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_ADRESS)
@Table(name = DBNames.DECODER_TYP_ADRESS,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX1", columnList = DBNames.DECODER_TYP_ID), 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX2", columnList = DBNames.POSITION)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_ADRESS + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.POSITION })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="decoderTypAdress",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "decoderTyp", subgraph = "decoderTypAdress.decoderTyp")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderTypAdress.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoderTypAdress.hersteller"),
                    @NamedAttributeNode(value = "bestellNr")
            }),
            @NamedSubgraph(name = "decoderTypAdress.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            })
        })
    })
//@formatter:on
@Adress
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DecoderTypAdress extends ItemImpl implements WithAdress, Comparable<DecoderTypAdress> {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_ADRESS + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The index. */
    @Column(name = DBNames.POSITION, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.index.notnull}")
    @Range(min=1, max=10, message = "{com.linepro.modellbahn.validator.constraints.index.range}")
    private Integer position;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The adressTyp. */
    @Column(name = DBNames.ADRESS_TYP, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private AdressTyp adressTyp;

    /** The span. */
    @Column(name = DBNames.SPAN)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.span.notnull}")
    @Range(min=1, max=32, message = "{com.linepro.modellbahn.validator.constraints.span.range}")
    private Integer span;

    /** The adress. */
    @Column(name = DBNames.WERKSEINSTELLUNG)
    private Integer adress;

    @Override
    public int compareTo(DecoderTypAdress other) {
        return new CompareToBuilder()
            .append(getAdress(), other.getAdress())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoderTyp().hashCode())
          .append(getAdress())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderTypAdress)) {
        return false;
      }

      DecoderTypAdress other = (DecoderTypAdress) obj;

      return new EqualsBuilder()
          .append(getDecoderTyp(), other.getDecoderTyp())
          .append(getAdress(), other.getAdress())
          .isEquals();
    }
}