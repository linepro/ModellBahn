package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderFunktion.
 * The functions supported by a Decoder (Key : description) 
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_FUNKTION)
@Table(name = DBNames.DECODER_FUNKTION,
    uniqueConstraints = { 
        @UniqueConstraint(name = DBNames.DECODER_FUNKTION + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.FUNKTION_ID })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DecoderFunktion extends ItemImpl implements Comparable<DecoderFunktion> {

    /** The decoder. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_FUNKTION + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;
    
    /** The funktion. */
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypFunktion.class)
    @JoinColumn(name = DBNames.FUNKTION_ID, nullable = false, referencedColumnName=DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_FUNKTION + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.funktion.notnull}")
    private DecoderTypFunktion funktion;

	/** The wert. */
    @Column(name=DBNames.BEZEICHNUNG, length=100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.wert.notnull}")
	private String bezeichnung;

    @Override
    public int compareTo(DecoderFunktion other) {
        return new CompareToBuilder()
            .append(getFunktion(), other.getFunktion())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoder().hashCode())
          .append(getFunktion().hashCode())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderFunktion)) {
        return false;
      }

      DecoderFunktion other = (DecoderFunktion) obj;

      return new EqualsBuilder()
          .append(getDecoder(), other.getDecoder())
          .append(getFunktion(), other.getFunktion())
          .isEquals();
    }
}
