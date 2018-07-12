package com.linepro.modellbahn.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity(name = "ZUGEN")
@Table(name = "DECODER", indexes = { @Index(columnList = "NAME", unique = true) })
public class Zug extends AbstractNamedItem implements IZug {

    private static final long serialVersionUID = 7391674754023907975L;

    private IZugTyp typ;

	private List<IZugConsist> consist = new ArrayList<IZugConsist>();

	public Zug() {
		super();
	}

	public Zug(Long id, String name, String bezeichnung, IZugTyp typ, Boolean deleted) {
		super(id, name, bezeichnung, deleted);

		this.typ = typ;
	}

	@Override
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=ZugTyp.class)
	@JoinColumn(name = "TYP_ID")
	public IZugTyp getTyp() {
		return typ;
	}

    @Override
	public void setTyp(IZugTyp typ) {
		this.typ = typ;
	}

    @Override
	@OneToMany(mappedBy = "zug", cascade=CascadeType.ALL, targetEntity=ZugConsist.class)
	public List<IZugConsist> getConsist() {
		return consist;
	}

    @Override
	public void setConsist(List<IZugConsist> consist) {
		this.consist.clear();
		this.consist.addAll(consist);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
				.append("typ", getTyp()).append("Consist", getConsist()).toString();
	}
}