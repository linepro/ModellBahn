package com.linepro.modellbahn.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTypFunktion;

@Entity
@Table(name = "decoder_funktion", indexes = { @Index(columnList = "decoder_id,funktion_id", unique = true) },
       uniqueConstraints = { @UniqueConstraint(columnNames = { "decoder_id", "funktion_id" })})
public class DecoderFunktion implements Serializable, IDecoderFunktion {

    private static final long serialVersionUID = -3254516717556070251L;

    private DecoderFunktionId id;

	private String wert;

	public DecoderFunktion() {
		super();
	}

	public DecoderFunktion(IDecoder decoder, IDecoderTypFunktion funktion, String wert) {
		setId(new DecoderFunktionId(decoder, funktion));
		this.wert = wert;
	}

	@Override
	@EmbeddedId
	public DecoderFunktionId getId() {
		return id;
	}

    @Override
	public void setId(DecoderFunktionId id) {
		this.id = id;
	}

    @Override
	@Column(name="wert", nullable=true, length=100)
	public String getWert() {
		return wert;
	}

    @Override
	public void setWert(String wert) {
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

		DecoderFunktion other = (DecoderFunktion) obj;

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
