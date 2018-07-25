package com.linepro.modellbahn.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Zug.
 * A train 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Zug")
@Table(name = "zugen", indexes = { @Index(columnList = "name", unique = true), @Index(columnList = "zug_typ_id") }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonRootName(value = "train")
public class Zug extends AbstractNamedItem implements IZug {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7391674754023907975L;

    /** The zugTyp. */
    private IZugTyp zugTyp;

	/** The consist. */
	private List<IZugConsist> consist = new ArrayList<IZugConsist>();

	/**
	 * Instantiates a new zug.
	 */
	public Zug() {
		super();
	}

	public Zug(String name) {
        super(name);
    }

	/**
	 * Instantiates a new zug.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param zugTyp the zugTyp
	 * @param deleted the deleted
	 */
	public Zug(Long id, String name, String bezeichnung, IZugTyp zugTyp, Boolean deleted) {
		super(id, name, bezeichnung, deleted);

		setZugTyp(zugTyp);
	}

	@Override
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=ZugTyp.class)
	@JoinColumn(name = "zug_typ_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "zug_fk1"))
	@JsonGetter("zugTyp")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
	public IZugTyp getZugTyp() {
		return zugTyp;
	}

    @Override
    @JsonSetter("zugTyp")
	public void setZugTyp(IZugTyp zugTyp) {
		this.zugTyp = zugTyp;
	}

    @Override
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zug", targetEntity=ZugConsist.class, orphanRemoval = true)
    @JsonGetter("consist")
    @JsonView(Views.Public.class)
	public List<IZugConsist> getConsist() {
		return consist;
	}

    @Override
    @JsonSetter("consist")
	public void setConsist(List<IZugConsist> consist) {
		this.consist = consist;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		        .appendSuper(super.toString())
				.append("zugTyp", getZugTyp())
				.append("Consist", getConsist())
				.toString();
	}
}