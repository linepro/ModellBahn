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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "decoder_cv", indexes = { @Index(columnList = "decoder_id,cv_id", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "decoder_id", "cv_id" }) })
public class DecoderCV extends AbstractItem implements IDecoderCV {

    private static final long serialVersionUID = 2660599652146536110L;

    private IDecoder decoder;

    private IDecoderTypCV cv;

    private Integer wert;

	public DecoderCV() {
		super();
	}

	public DecoderCV(IDecoder decoder, IDecoderTypCV cv, Integer wert) {
        this.decoder = decoder;
        this.cv = cv;
        this.wert = wert;
	}


    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name="decoder_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_cv_fk1"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypCV.class)
    @JoinColumn(name="cv_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_cv_fk2"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoderTypCV getCV() {
        return cv;
    }

    public void setCV(IDecoderTypCV cv) {
        this.cv = cv;
    }

	@Override
    @Column(name="wert")
	public Integer getWert() {
		return wert;
	}

	@Override
    public void setWert(Integer wert) {
		this.wert = wert;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("decoder", getDecoder())
                .append("cv", getCV())
				.append("wert", getWert())
				.toString();
	}
}
