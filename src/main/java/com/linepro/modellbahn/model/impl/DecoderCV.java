package com.linepro.modellbahn.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderTypCV;

@Table(name = "DECODER_CV", indexes = { @Index(columnList = "DECODER_ID,CV_ID", unique = true) })
public class DecoderCV implements Serializable, IDecoderCV {

    private static final long serialVersionUID = 2660599652146536110L;

    private DecoderCVId id;
	
	private Integer wert;

	public DecoderCV() {
		super();
	}

	public DecoderCV(IDecoder decoder, IDecoderTypCV cv, Integer wert) {
		setId(new DecoderCVId(decoder, cv));
	}

	@Override
    @EmbeddedId
	public DecoderCVId getId() {
		return id;
	}

	@Override
    public void setId(DecoderCVId id) {
		this.id = id;
	}

	@Override
    @Column(name="WERT")
	public Integer getWert() {
		return wert;
	}

	@Override
    public void setWert(Integer wert) {
		this.wert = wert;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		IDecoderCV other = (IDecoderCV) obj;

		return new EqualsBuilder()
				.append(getId(), other.getId())
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", getId())
				.append("wert", getWert())
				.toString();
	}
}
