package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderCV.
 * Holds the CV values for a Decoder (when Konfiguration.CV is in force) 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderCV")
@Table(name = "decoder_cv", indexes = { @Index(columnList = "decoder_id,cv_id", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "decoder_id", "cv_id" }) })
public class DecoderCV extends AbstractItem implements IDecoderCV {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2660599652146536110L;

    /** The decoder. */
    private IDecoder decoder;

    /** The cv. */
    private IDecoderTypCV cv;

    /** The wert. */
    private Integer wert;

	/**
	 * Instantiates a new decoder CV.
	 */
	public DecoderCV() {
		super();
	}

	/**
	 * Instantiates a new decoder CV.
	 *
	 * @param decoder the decoder
	 * @param cv the cv
	 * @param wert the wert
	 */
	public DecoderCV(IDecoder decoder, IDecoderTypCV cv, Integer wert) {
        setDecoder(decoder);
        setCV(cv);
        setWert(wert);
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name="decoder_id", nullable = false, referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_cv_fk1"))
	@JsonGetter("decoder")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    @JsonSetter("decoder")
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypCV.class)
    @JoinColumn(name="cv_id", nullable = false, referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_cv_fk2"))
    @JsonGetter("cv")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoderTypCV getCV() {
        return cv;
    }

    @Override
    @JsonSetter("cv")
    public void setCV(IDecoderTypCV cv) {
        this.cv = cv;
    }

	@Override
    @Column(name="wert", nullable = true)
    @JsonGetter("wert")
	public Integer getWert() {
		return wert;
	}

	@Override
    @JsonSetter("wert")
    public void setWert(Integer wert) {
		this.wert = wert;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("decoder", getDecoder().getId())
                .append("cv", getCV())
				.append("wert", getWert())
				.toString();
	}
}
