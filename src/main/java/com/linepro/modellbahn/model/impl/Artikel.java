package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "ARTIKELEN", indexes = { @Index(columnList = "ID", unique = true) })
public class Artikel extends AbstractItem implements IArtikel {

    private static final long serialVersionUID = 8652624782179487496L;

    private IProdukt produkt;
	
    private Date Kaufdatum;
	
    private IWahrung wahrung;
	
    private BigDecimal preis;
	
    private Integer stuck;
	
    private ISteuerung steuerung;
	
    private IMotorTyp motorTyp;
	
    private ILicht licht;
	
    private IKupplung kupplung;
	
    private IDecoder decoder;
	
    private String anmerkung;
	
    private String beladung;
	
    private Status status;

	public Artikel() {
		super();
	}

	public Artikel(Long id, IProdukt produkt, Date kaufdatum, IWahrung wahrung, BigDecimal preis, Integer stuck,
			ISteuerung steuerung, IMotorTyp motorTyp, ILicht licht, IKupplung kupplung, IDecoder decoder, String anmerkung,
			String beladung, Status status, Boolean deleted) {
		super(id, deleted);
		this.produkt = produkt;
		this.Kaufdatum = kaufdatum;
		this.wahrung = wahrung;
		this.preis = preis;
		this.stuck = stuck;
		this.steuerung = steuerung;
		this.motorTyp = motorTyp;
		this.licht = licht;
		this.kupplung = kupplung;
		this.decoder = decoder;
		this.anmerkung = anmerkung;
		this.beladung = beladung;
		this.status = status;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Produkt.class)
    @JoinColumn(name = "PRODUKT_ID", referencedColumnName="ID")
	public IProdukt getProdukt() {
		return produkt;
	}

	@Override
    public void setProdukt(IProdukt produkt) {
		this.produkt = produkt;
	}

	@Override
    @Column(name = "KAUFDATUM", nullable=true)
	@Temporal(TemporalType.DATE)
	public Date getKaufdatum() {
		return Kaufdatum;
	}
	
	@Override
    public void setKaufdatum(Date kaufdatum) {
		Kaufdatum = kaufdatum;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Wahrung.class)
    @JoinColumn(name = "WAHRUNG_ID", nullable = true, referencedColumnName="ID")
	public IWahrung getWahrung() {
		return wahrung;
	}

	@Override
    public void setWahrung(IWahrung wahrung) {
		this.wahrung = wahrung;
	}

	@Override
    @Column(name = "PREIS", nullable=true)
	public BigDecimal getPreis() {
		return preis;
	}

	@Override
    public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	@Override
    @Column(name = "STUCK")
	public Integer getStuck() {
		return stuck;
	}

	@Override
    public void setStuck(Integer stuck) {
		this.stuck = stuck;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Steuerung.class)
    @JoinColumn(name = "STEUERUNG_ID", nullable = true, referencedColumnName="ID")
	public ISteuerung getSteuerung() {
		return steuerung;
	}

	@Override
    public void setSteuerung(ISteuerung steuerung) {
		this.steuerung = steuerung;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=MotorTyp.class)
    @JoinColumn(name = "MOTOR_TYP_ID", nullable = true, referencedColumnName="ID")
	public IMotorTyp getMotorTyp() {
		return motorTyp;
	}

	@Override
    public void setMotorTyp(IMotorTyp motorTyp) {
		this.motorTyp = motorTyp;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Licht.class)
    @JoinColumn(name = "LICHT_ID", nullable = true, referencedColumnName="ID")
	public ILicht getLicht() {
		return licht;
	}

	@Override
    public void setLicht(ILicht licht) {
		this.licht = licht;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Kupplung.class)
    @JoinColumn(name = "KUPPLUNG_ID", nullable = true, referencedColumnName="ID")
	public IKupplung getKupplung() {
		return kupplung;
	}

	@Override
    public void setKupplung(IKupplung kupplung) {
		this.kupplung = kupplung;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name = "DECODER_ID", nullable = true, referencedColumnName="ID")
	public IDecoder getDecoder() {
		return decoder;
	}

	@Override
    public void setDecoder(IDecoder decoder) {
		this.decoder = decoder;
	}

	@Override
    @Column(name = "ANMERKUNG", nullable=true)
	public String getAnmerkung() {
		return anmerkung;
	}

	@Override
    public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}

	@Override
    @Column(name = "BELADUNG", nullable=true)
	public String getBeladung() {
		return beladung;
	}

	@Override
    public void setBeladung(String beladung) {
		this.beladung = beladung;
	}

	@Override
    @Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public Status getStatus() {
		return status;
	}

	@Override
    public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
				.append("Produkt", getProdukt()).append("kaufdatum", getKaufdatum()).append("Wahrung", getWahrung())
				.append("Preis", getPreis()).append("stuck", getStuck()).append("steuerung", getSteuerung())
				.append("motorTyp", getMotorTyp()).append("licht", getLicht()).append("kupplung", getKupplung())
				.append("decoder", getDecoder()).append("anmerkung", getAnmerkung()).append("beladung", getBeladung())
				.append("status", getStatus()).toString();
	}
}