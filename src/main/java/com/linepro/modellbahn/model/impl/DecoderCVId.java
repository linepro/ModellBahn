package com.linepro.modellbahn.model.impl;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderCVId;
import com.linepro.modellbahn.model.IDecoderTypCV;

@Embeddable
public class DecoderCVId implements Serializable, IDecoderCVId {

    private static final long serialVersionUID = 5219977072350797685L;

    private IDecoder decoder;

	private IDecoderTypCV cv;

	public DecoderCVId() {
		super();
	}

	public DecoderCVId(IDecoder decoder, IDecoderTypCV cv) {
		this.decoder = decoder;
		this.cv = cv;
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
	@JoinColumn(name="decoder_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_cv_fk1"))
    @JsonBackReference
	public IDecoder getDecoder() {
		return decoder;
	}

	@Override
    public void setDecoder(IDecoder decoder) {
		this.decoder = decoder;
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypCV.class)
	@JoinColumn(name="cv_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_cv_fk2"))
    @JsonBackReference
	public IDecoderTypCV getCV() {
		return cv;
	}

	@Override
    public void setCV(IDecoderTypCV cv) {
		this.cv = cv;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getDecoder())
				.append(getCV())
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

		IDecoderCVId other = (IDecoderCVId) obj;

		return new EqualsBuilder()
				.append(getDecoder(), other.getDecoder())
				.append(getCV(), other.getCV())
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("decoder", getDecoder())
				.append("cv", getCV())
				.toString();
	}
}
