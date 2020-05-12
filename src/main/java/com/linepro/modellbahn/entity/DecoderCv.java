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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.CVValue;

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
    indexes = {
        @Index(name = DBNames.DECODER_CV + "_IX1", columnList = DBNames.DECODER_ID + "," + DBNames.CV_ID, unique = true) 
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_CV + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.CV_ID }) 
    })
//@formatter:on
public class DecoderCv extends ItemImpl {

    /** The decoder. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;

    /** The cv. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    private DecoderTypCv cv;

    /** The wert. */
    @CVValue
    private Integer wert;

    /**
     * Instantiates a new decoder CV.
     */
    public DecoderCv() {
        super();
    }

    public DecoderCv(Long id, Decoder decoder, DecoderTypCv cv, Integer wert, Boolean deleted) {
        super(id, deleted);

        setDecoder(decoder);
        setCv(cv);
        setWert(wert);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_CV + "_fk1"))
    public Decoder getDecoder() {
        return decoder;
    }

    
    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DecoderTypCv.class)
    @JoinColumn(name = DBNames.CV_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_CV + "_fk2"))
    public DecoderTypCv getCv() {
        return cv;
    }

    
    public void setCv(DecoderTypCv cv) {
        this.cv = cv;
    }

    
    @Column(name = DBNames.WERT)
    public Integer getWert() {
        return wert;
    }

    
    public void setWert(Integer wert) {
        this.wert = wert;
    }

    
    public int compareTo(Item other) {
        if (other instanceof DecoderCv) {
            return new CompareToBuilder()
                    .append(getDecoder(), ((DecoderCv) other).getDecoder())
                    .append(getCv(), ((DecoderCv) other).getCv())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoder())
                .append(getCv())
                .hashCode();
    }

    
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

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.DECODER, getDecoder())
                .append(DBNames.CV, getCv())
                .append("wert", getWert())
                .toString();
    }
}
