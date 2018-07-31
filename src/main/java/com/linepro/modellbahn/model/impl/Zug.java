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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.ZugTypResolver;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Zug.
 * A train 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Zug")
@Table(name = "Zug", indexes = { @Index(columnList = DBNames.NAME, unique = true), @Index(columnList = DBNames.ZUG_TYP_ID) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.ZUG)
@JsonPropertyOrder({ApiNames.ID, ApiNames.ZUG_TYP, ApiNames.NAME,ApiNames.DESCRIPTION,ApiNames.DELETED, ApiNames.CONSIST, ApiNames.LINKS})
public class Zug extends AbstractNamedItem<NameKey> implements IZug {

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
	@JoinColumn(name = DBNames.ZUG_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "zug_fk1"))
	@JsonGetter(ApiNames.ZUG_TYP)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=ZugTypResolver.class)
	public IZugTyp getZugTyp() {
		return zugTyp;
	}

    @Override
    @JsonSetter(ApiNames.ZUG_TYP)
    @JsonDeserialize(as=ZugTyp.class)
	public void setZugTyp(IZugTyp zugTyp) {
		this.zugTyp = zugTyp;
	}

    @Override
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.ZUG, targetEntity=ZugConsist.class, orphanRemoval = true)
    @JsonGetter(ApiNames.CONSIST)
    @JsonView(Views.Public.class)
	public List<IZugConsist> getConsist() {
		return consist;
	}

    @Override
    @JsonSetter(ApiNames.CONSIST)
	public void setConsist(List<IZugConsist> consist) {
		this.consist = consist;
	}
   
    @Override
    public void addConsist(IZugConsist consist) {
        consist.setZug(this);
        getConsist().add(consist);
    }

    @Override
    public void removeConsist(IZugConsist consist) {
        getConsist().remove(consist);
    }


	@Override
	public String toString() {
		return new ToStringBuilder(this)
		        .appendSuper(super.toString())
				.append(ApiNames.ZUG_TYP, getZugTyp())
				.append(ApiNames.CONSIST, getConsist())
				.toString();
	}
}