package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity(name = "UNTER_KATEGORIEN")
@Table(name = "UNTER_KATEGORIEN", indexes = { @Index(columnList = "KATEGORIE_ID, NAME", unique = true) })
public class UnterKategorie extends AbstractNamedItem implements IUnterKategorie {

    private static final long serialVersionUID = 5346529720680464691L;

    private IKategorie kategorie;

	public UnterKategorie() {
		super();
	}

	public UnterKategorie(Long id, String name, IKategorie kategorie, String bezeichnung, Boolean deleted) {
		super(id, name, bezeichnung, deleted);

		this.kategorie = kategorie;
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Kategorie.class)
	@JoinColumn(name = "KATEGORIE_ID", referencedColumnName="ID")
	public IKategorie getKategorie() {
		return kategorie;
	}

	@Override
    public void setKategorie(IKategorie kategorie) {
		this.kategorie = kategorie;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
				.append("kategorie", getKategorie()).toString();
	}
}