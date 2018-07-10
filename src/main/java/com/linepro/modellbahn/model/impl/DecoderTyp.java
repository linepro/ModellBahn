package com.linepro.modellbahn.model.impl;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IAdressTyp;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "DECODER_TYP", uniqueConstraints = { @UniqueConstraint(columnNames = { "HERSTELLER_ID", "BESTELL_NR" }) })
public class DecoderTyp extends AbstractNamedItem implements IDecoderTyp {

    private static final long serialVersionUID = 8503812316290492490L;

    private IAdressTyp typ;
	
    private IHersteller hersteller;
	
    private String bestellNr;
	
    private Integer adressen;
	
    private BigDecimal iMax;
	
    private IProtokoll protokoll;
	
    private Integer fahrstufe;
	
    private String sound;

	private Set<IDecoderTypCV> cv = new HashSet<IDecoderTypCV>();
	
	private Set<IDecoderTypFunktion> funktion = new HashSet<IDecoderTypFunktion>();

	public DecoderTyp() {
		super();
	}

	public DecoderTyp(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=AdressTyp.class)
	@JoinColumn(name = "ADRESS_TYP_ID", referencedColumnName="ID")
	public IAdressTyp getTyp() {
		return typ;
	}

	@Override
    public void setTyp(IAdressTyp typ) {
		this.typ = typ;
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Hersteller.class)
	@JoinColumn(name = "HERSTELLER_ID", referencedColumnName="ID")
	public IHersteller getHersteller() {
		return hersteller;
	}

	@Override
    public void setHersteller(IHersteller hersteller) {
		this.hersteller = hersteller;
	}

	@Override
    @Column(name = "BESTELL_NR")
	public String getBestellNr() {
		return bestellNr;
	}

	@Override
    public void setBestellNr(String bestellNr) {
		this.bestellNr = bestellNr;
	}

	@Override
    @Column(name = "ADRESSEN", nullable = false, columnDefinition = "SMALLINT DEFAULT 1 NOT NULL")
	@Min(1)
	public Integer getAdressen() {
		return adressen;
	}

	@Override
    public void setAdressen(Integer adressen) {
		this.adressen = adressen;
	}

	@Override
    @Column(name = "I_MAX", nullable = true, precision = 6, scale = 3)
	public BigDecimal getiMax() {
		return iMax;
	}

	@Override
    public void setiMax(BigDecimal iMax) {
		this.iMax = iMax;
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Protokoll.class)
	@JoinColumn(name = "PROTOKOLL_ID", referencedColumnName="ID")
	public IProtokoll getProtokoll() {
		return protokoll;
	}

	@Override
    public void setProtokoll(IProtokoll protokoll) {
		this.protokoll = protokoll;
	}

	@Override
    @Column(name = "FAHRSTUFE", nullable = true)
	@Min(1)
	public Integer getFahrstufe() {
		return fahrstufe;
	}

	@Override
    public void setFahrstufe(Integer fahrstufe) {
		this.fahrstufe = fahrstufe;
	}

	@Override
    @Transient
	public Boolean getSound() {
		return Boolean.valueOf(getSound());
	}

	@Override
    public void setSound(Boolean sound) {
		setSoundStr(sound.toString());
	}

	@Column(name = "SOUND", nullable = false)
	protected String getSoundStr() {
		return sound;
	}

	protected void setSoundStr(String sound) {
		this.sound = sound;
	}

	@Override
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="decoderTyp", targetEntity=DecoderTypCV.class)
	public Set<IDecoderTypCV> getCv() {
		return cv;
	}

	@Override
    public void setCv(Set<IDecoderTypCV> cv) {
		this.cv.clear();
		this.cv.addAll(cv);
	}

	@Override
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="decoderTyp", targetEntity=DecoderTypFunktion.class)
	public Set<IDecoderTypFunktion> getFunktion() {
		return funktion;
	}

	@Override
    public void setFunktion(Set<IDecoderTypFunktion> funktion) {
		this.funktion.clear();
		this.funktion.addAll(funktion);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
				.append("typ", getTyp()).append("hersteller", getHersteller()).append("bestellNr", getBestellNr())
				.append("iMax", getiMax()).append("protokoll", getProtokoll()).append("fahrstufe", getFahrstufe())
				.append("sound", getSound()).append("adressen", getAdressen()).append("cv", getCv())
				.append("funktion", getFunktion()).toString();
	}
}