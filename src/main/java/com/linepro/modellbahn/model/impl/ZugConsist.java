package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "consist", indexes = { @Index(columnList = "zug_id"),
		@Index(columnList = "artikel_id", unique = true) }, 
        uniqueConstraints = { @UniqueConstraint(columnNames = { "zug_id", "position" }) })
public class ZugConsist extends AbstractItem implements IZugConsist {

    private static final long serialVersionUID = 3941436184732408563L;

    private IZug zug;
	
    private Integer position;
	
    private IArtikel artikel;

	public ZugConsist() {
		super();
	}

	public ZugConsist(Long id, IZug zug, Integer position, IArtikel artikel, Boolean deleted) {
		super(id, deleted);

		this.zug = zug;
		this.position = position;
		this.artikel = artikel;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Zug.class)
	@JoinColumn(name = "zug_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "consist_fk1"))
	@JsonBackReference
	public IZug getZug() {
		return zug;
	}

	@Override
    public void setZug(IZug zug) {
		this.zug = zug;
	}

	@Override
    @OrderColumn
	@Column(name = "position")
	public Integer getPosition() {
		return position;
	}

	@Override
    public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity=Artikel.class)
	@JoinColumn(name = "artikel_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "consist_fk2"))
    @JsonBackReference
	public IArtikel getArtikel() {
		return artikel;
	}

	@Override
    public void setArtikel(IArtikel artikel) {
		this.artikel = artikel;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
				.append("Zug", getZug()).append("Position", getPosition())
				.append("artikel", getArtikel()).toString();
	}
}
