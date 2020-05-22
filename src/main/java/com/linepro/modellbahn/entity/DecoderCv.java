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
import com.linepro.modellbahn.validation.CVValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderCv. Holds the CV values for a Decoder (when Konfiguration.CV is in
 * force)
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_CV)
@Table(name = DBNames.DECODER_CV, 
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_CV + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.CV_ID }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DecoderCv extends ItemImpl implements Comparable<DecoderCv>{

    /** The decoder. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_CV + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;

    /** The cv. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DecoderTypCv.class)
    @JoinColumn(name = DBNames.CV_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_CV + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    private DecoderTypCv cv;

    /** The wert. */
    @Column(name = DBNames.WERT)
    @CVValue
    private Integer wert;

    @Override
    public int compareTo(DecoderCv other) {
        return new CompareToBuilder()
            .append(getCv(), other.getCv())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getDecoder().hashCode())
          .append(getCv().hashCode())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderCv)) {
        return false;
      }

      DecoderCv other = (DecoderCv) obj;

      return new EqualsBuilder()
          .append(getDecoder(), other.getDecoder())
          .append(getCv(), other.getCv())
          .isEquals();
    }
}
