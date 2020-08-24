/*
 * Adress
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.WithAdress;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Adress;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * DecoderAdress. An address for a decoder (several have more than one)
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_ADRESS)
@Table(name =  DBNames.DECODER_ADRESS,
    uniqueConstraints = { 
        @UniqueConstraint(name = DBNames.DECODER_ADRESS + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.ADRESS_ID })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="decoderAdress",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "decoder", subgraph = "decoderAdress.decoder"),
            @NamedAttributeNode(value = "typ", subgraph = "decoderAdress.typ")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderAdress.decoder",
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "decoderId")
            }),
            @NamedSubgraph(name = "decoderAdress.typ",
            attributeNodes = {
                @NamedAttributeNode(value = "position"),
                @NamedAttributeNode(value = "bezeichnung"),
                @NamedAttributeNode(value = "span"),
                @NamedAttributeNode(value = "adressTyp"),
                @NamedAttributeNode(value = "adress")
            })
        })
    })
//@formatter:on
@Adress
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class DecoderAdress extends ItemImpl implements WithAdress, Comparable<DecoderAdress> {

    /** The decoder. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class, optional = false)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_ADRESS + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;

    /** The adress. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTypAdress.class, optional = false)
    @JoinColumn(name = DBNames.ADRESS_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_ADRESS + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    private DecoderTypAdress typ;

    /** The adress. */
    @Column(name = DBNames.ADRESS, nullable = false)
    private Integer adress;

    @Transient
    public AdressTyp getAdressTyp() {
        return typ.getAdressTyp();
    }

    @Override
    public int compareTo(DecoderAdress other) {
        return new CompareToBuilder()
            .append(getTyp(), other.getTyp())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoder().hashCode())
          .append(getTyp().hashCode())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderAdress)) {
        return false;
      }

      DecoderAdress other = (DecoderAdress) obj;

      return new EqualsBuilder()
          .append(getDecoder(), other.getDecoder())
          .append(getTyp(), other.getTyp())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("decoder", decoder)
            .append("typ", typ)
            .append("adress",  adress)
            .toString();
    }
}