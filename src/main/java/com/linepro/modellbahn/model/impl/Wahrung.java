package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.model.keys.NameKey;

/**
 * Wahrung.
 * Currency for a price.
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Wahrung")
@Table(name = "Wahrung", indexes = { @Index(columnList = DBNames.NAME, unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.WAHRUNG)
@JsonPropertyOrder({ApiNames.ID, ApiNames.NAME,ApiNames.DESCRIPTION,ApiNames.DECIMALS, ApiNames.DELETED, ApiNames.LINKS})
public class Wahrung extends AbstractNamedItem<NameKey> implements IWahrung {
	
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
    @Column(name=DBNames.DECIMALS, nullable = true)
	@JsonGetter(ApiNames.DECIMALS)
    @JsonView(Views.Public.class)
	public Integer getDecimals() {
		return decimals;
	}

	@Override
    @JsonSetter(ApiNames.DECIMALS)
    public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		        .appendSuper(super.toString())
		        .append(ApiNames.DECIMALS, getDecimals())
		        .toString();
	}
}
