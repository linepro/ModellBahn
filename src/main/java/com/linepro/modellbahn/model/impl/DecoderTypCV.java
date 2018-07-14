package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "DECODER_TYP_CV", indexes = { @Index(columnList = "DECODER_TYP_ID,CV", unique = true) })
public class DecoderTypCV extends AbstractItem implements IDecoderTypCV {

    private static final long serialVersionUID = -5202372019371973750L;

    private IDecoderTyp decoderTyp;

	private Integer cv;
	
	private String bezeichnung;
	
	private Long minimal;

	private Long maximal;

	private Long werkseinstellung;

	public DecoderTypCV() {
		super();
	}

	public DecoderTypCV(IDecoderTyp decoderTyp, Long id, Integer cv, String bezeichnung, Long minimal, Long maximal, Long werkseinstellung, Boolean deleted) {
		super(id, deleted);

		this.cv = cv;
		this.bezeichnung = bezeichnung;
		this.decoderTyp = decoderTyp;
		this.minimal = minimal;
		this.maximal = maximal;
		this.werkseinstellung = werkseinstellung;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=DecoderTyp.class)
	@JoinColumn(name = "DECODER_TYP_ID", referencedColumnName="ID")
    @JsonBackReference
	public IDecoderTyp getDecoderTyp() {
		return decoderTyp;
	}

	@Override
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
		this.decoderTyp = decoderTyp;
	}

	@Override
    @Column(name="CV", nullable=false)
	@Min(0)
	public Integer getCv() {
		return cv;
	}

	@Override
    public void setCv(Integer cv) {
		this.cv = cv;
	}

	@Override
    @Column(name="BEZEICHNUNG", length=100)
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
    public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
    @Column(name="MINIMAL", nullable=true)
	public Long getMinimal() {
		return minimal;
	}

	@Override
    public void setMinimal(Long minimal) {
		this.minimal = minimal;
	}

	@Override
    @Column(name="MAXIMAL", nullable=true)
	public Long getMaximal() {
		return maximal;
	}

	@Override
    public void setMaximal(Long maximal) {
		this.maximal = maximal;
	}

	@Override
    @Column(name="WERKSEINSTELLUNG", nullable=true)
	public Long getWerkseinstellung() {
		return werkseinstellung;
	}

	@Override
    public void setWerkseinstellung(Long werkseinstellung) {
		this.werkseinstellung = werkseinstellung;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
				.append("cv", getCv())
				.append("bezeichnung", getBezeichnung())
				.append("minimal", getMinimal())
				.append("maximal", getMaximal())
				.append("werkseinstellung", getWerkseinstellung()).toString();
	}
}