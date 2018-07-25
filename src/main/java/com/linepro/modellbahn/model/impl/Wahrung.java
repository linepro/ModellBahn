package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Wahrung.
 * Currency for a price.
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Wahrung")
@Table(name = "wahrungen", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonRootName(value = "currency")
public class Wahrung extends AbstractNamedItem implements IWahrung {
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 9168067747478159138L;

    /** The decimals. */
    private Integer decimals;
	
	/**
	 * Instantiates a new wahrung.
	 */
	public Wahrung() {
		super();
	}

    public Wahrung(String name) {
        super(name);
    }

    /**
	 * Instantiates a new wahrung.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param decimals the decimals
	 * @param deleted the deleted
	 */
	public Wahrung(Long id, String name, String bezeichnung, Integer decimals, Boolean deleted) {
		super(id, name, bezeichnung, deleted);

		setDecimals(decimals);
	}

	@Override
    @Column(name="decimals", nullable = true)
	@JsonGetter("decimals")
    @JsonView(Views.Public.class)
	public Integer getDecimals() {
		return decimals;
	}

	@Override
    @JsonSetter("decimals")
    public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		        .appendSuper(super.toString())
		        .append("decimals", getDecimals())
		        .toString();
	}
}
