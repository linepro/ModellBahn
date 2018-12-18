package com.linepro.modellbahn.model.impl;

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
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.TreeSet;

/**
 * Zug.
 * A train 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.ZUG)
@Table(name = DBNames.ZUG, indexes = { @Index(columnList = DBNames.NAME, unique = true), @Index(columnList = DBNames.ZUG_TYP_ID) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.ZUG)
@JsonPropertyOrder({ApiNames.ID, ApiNames.ZUG_TYP, ApiNames.NAMEN,ApiNames.BEZEICHNUNG,ApiNames.DELETED, ApiNames.CONSIST, ApiNames.LINKS})
public class Zug extends AbstractNamedItem<NameKey> implements IZug {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7391674754023907975L;

    /** The zugTyp. */
    @NotNull
    private IZugTyp zugTyp;

	/** The consist. */
	private Set<IZugConsist> consist = new TreeSet<>();

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
	@JoinColumn(name = DBNames.ZUG_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG + "_fk1"))
	@JsonGetter(ApiNames.ZUG_TYP)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver=ZugTypResolver.class)
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
	public Set<IZugConsist> getConsist() {
		return consist;
	}

    @Override
    @JsonSetter(ApiNames.CONSIST)
	public void setConsist(Set<IZugConsist> consist) {
		this.consist = consist;
	}
   
    @Override
    public void addConsist(IZugConsist consist) {
        // Add at end semantics
        consist.setZug(this);
        consist.setPosition(getConsist().size());
        getConsist().add(consist);
    }

    @Override
    public void removeConsist(IZugConsist consist) {
        getConsist().remove(consist);
        
        // Just renumber the whole lot; don't try and work out from where - it's just as expensive
        int position = 0;

        for (IZugConsist zc : getConsist()) {
            zc.setPosition(position++);
        }
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