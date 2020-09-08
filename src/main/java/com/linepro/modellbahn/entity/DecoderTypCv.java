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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.CVValue;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTypCv.
 * The CVs available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_CV)
@Table(name = DBNames.DECODER_TYP_CV,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_CV + "_IX1", columnList = DBNames.DECODER_TYP_ID), 
        @Index(name = DBNames.DECODER_TYP_CV + "_IX2", columnList = DBNames.CV)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_CV + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.CV }) 
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="decoderTypCv",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "decoderTyp", subgraph = "decoderTypCv.decoderTyp")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderTypCv.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoderTypCv.hersteller"),
                    @NamedAttributeNode(value = "bestellNr")
            }),
            @NamedSubgraph(name = "decoderTypCv.hersteller",
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
@Unique(message = "{com.linepro.modellbahn.validator.constraints.decoderTypCv.notunique}")
public class DecoderTypCv extends ItemImpl implements Comparable<DecoderTypCv> {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class, optional = false)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_CV + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The cv. */
    @Column(name = DBNames.CV, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    @Range(min=1, max=255, message = "{com.linepro.modellbahn.validator.constraints.cv.range}")
    private Integer cv;

    /** The bezeichnung. */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The minimal. */
    @Column(name = DBNames.MINIMAL)
    @CVValue
    private Integer minimal;

    /** The maximal. */
    @Column(name = DBNames.MAXIMAL)
    @CVValue
    private Integer maximal;

    /** The werkseinstellung. */
    @Column(name = DBNames.WERKSEINSTELLUNG)
    @CVValue
    private Integer werkseinstellung;

    @Override
    public int compareTo(DecoderTypCv other) {
        return new CompareToBuilder()
            .append(getCv(), other.getCv())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoderTyp().hashCode())
          .append(getCv())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderTypCv)) {
        return false;
      }

      DecoderTypCv other = (DecoderTypCv) obj;

      return new EqualsBuilder()
          .append(getDecoderTyp(), other.getDecoderTyp())
          .append(getCv(), other.getCv())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("decoderTyp", decoderTyp)
            .append("cv", cv)
            .append("bezeichnung", bezeichnung)
            .append("minimal", minimal)
            .append("maximal", maximal)
            .append("werkseinstellung", werkseinstellung)
            .toString();
    }
}