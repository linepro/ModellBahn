package com.linepro.modellbahn.model.impl;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderFunktionId;
import com.linepro.modellbahn.model.IDecoderTypFunktion;

@Embeddable
public class DecoderFunktionId implements Serializable, IDecoderFunktionId {

    private static final long serialVersionUID = -8385022852840681266L;

    private IDecoder decoder;
	
    private IDecoderTypFunktion funktion;

	public DecoderFunktionId() {
		super();
	}

	public DecoderFunktionId(IDecoder decoder, IDecoderTypFunktion funktion) {
		this.decoder = decoder;
		this.funktion = funktion;
	}

    @Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
	@JoinColumn(name="DECODER_ID", referencedColumnName="ID")
	public IDecoder getDecoder() {
		return decoder;
	}

	@Override
    public void setDecoder(IDecoder decoder) {
		this.decoder = decoder;
	}

    @Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypFunktion.class)
	@JoinColumn(name="FUNKTION_ID", referencedColumnName="ID")
	public IDecoderTypFunktion getFunktion() {
		return funktion;
	}

	@Override
    public void setFunktion(IDecoderTypFunktion funktion) {
		this.funktion = funktion;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getDecoder())
				.append(getFunktion())
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

		IDecoderFunktionId other = (IDecoderFunktionId) obj;

		return new EqualsBuilder()
				.append(getDecoder(), other.getDecoder())
				.append(getFunktion(), other.getFunktion())
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("decoder", getDecoder())
				.append("funktion", getFunktion())
				.toString();
	}
}