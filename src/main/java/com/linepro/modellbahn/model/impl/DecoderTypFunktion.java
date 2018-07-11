package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "DECODER_TYP_FUNKTIONEN", indexes = { @Index(columnList = "DECODER_TYP_ID,REIHE,NR", unique = true) })
public class DecoderTypFunktion extends AbstractItem implements IDecoderTypFunktion {

    private static final long serialVersionUID = -9194895557054214626L;

    private IDecoderTyp decoderTyp;

	private Long reihe;

	private String funktionNr;

	public DecoderTypFunktion() {
		super();
	}

	public DecoderTypFunktion(Long id, IDecoderTyp decoderTyp, Long reihe, String funktionNr, Boolean deleted) {
		super(id, deleted);
		
		this.decoderTyp = decoderTyp;
		this.reihe = reihe;
		this.funktionNr = funktionNr;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=DecoderTyp.class)
	@JoinColumn(name = "DECODER_TYP_ID", referencedColumnName="ID")
	public IDecoderTyp getDecoderTyp() {
		return decoderTyp;
	}

	@Override
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
		this.decoderTyp = decoderTyp;
	}

	@Override
    @Column(name="REIHE", nullable = false)
	public Long getReihe() {
		return reihe;
	}

	@Override
    public void setReihe(Long reihe) {
		this.reihe = reihe;
	}

	@Override
    @Column(name="NR", nullable=false, length=4)
	public String getFunktionNr() {
		return funktionNr;
	}

	@Override
    public void setFunktionNr(String funktionNr) {
		this.funktionNr = funktionNr;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append("decoderTyp",decoderTyp)
				.append("reihe", reihe)
				.append("funktionNr", funktionNr)
				.toString();
	}
}