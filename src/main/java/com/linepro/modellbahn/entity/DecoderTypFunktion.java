package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
        @Index(name = DBNames.DECODER_TYP_FUNKTION + "_IX1", columnList = DBNames.DECODER_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_FUNKTION + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.REIHE, DBNames.FUNKTION })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DecoderTypFunktion extends ItemImpl implements Comparable<DecoderTypFunktion> {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_FUNKTION + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The reihe. */
    @Column(name = DBNames.REIHE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.reihe.notnull}")
    @Range(min=1, max=2, message = "{com.linepro.modellbahn.validator.constraints.reihe.range}")
    private Integer reihe;

    @Column(name = DBNames.FUNKTION, length = 4)
    @Pattern(regexp = "^F([12]\\d|3[012]|\\d)$|^K(1[012345]|\\d)$|^S[0123456]$", message = "{com.linepro.modellbahn.validator.constraints.funktion.invalid}")
    private String funktion;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;
    
    /** The programmable. */
    @Column(name = DBNames.PROGRAMMABLE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.programmable.notnull}")
    private Boolean programmable;

    @Override
    public int compareTo(DecoderTypFunktion other) {
        return new CompareToBuilder()
            .append(getReihe(), other.getReihe())
            .append(getFunktion(), other.getFunktion())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoderTyp().hashCode())
          .append(getReihe())
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
}