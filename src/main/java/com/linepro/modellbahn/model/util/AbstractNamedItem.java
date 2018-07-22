/*
 * AbstractNamedItem
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;

/**
 * The Class AbstractNamedItem.
 */
@MappedSuperclass
@JsonPropertyOrder({"id","name","description","deleted"})
public abstract class AbstractNamedItem extends AbstractItem implements INamedItem {

    protected static final String[] BUSINESS_KEY = new String[] { "name" };

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -278823660682127691L;

    /** The name. */
    private String name;

	/** The bezeichnung. */
	private String bezeichnung;

	/**
	 * Instantiates a new abstract named item.
	 */
	public AbstractNamedItem() {
	}

	/**
	 * Instantiates a new abstract named item.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param deleted the deleted
	 */
	public AbstractNamedItem(Long id, String name, String bezeichnung, Boolean deleted) {
		super(id, deleted);
		
		setName(name);
		setBezeichnung(bezeichnung);
	}

	@Override
	@BusinessKey
    @Column(name="name", unique=true, length=50)
    @JsonGetter("name")
    @JsonView(Views.DropDown.class)
	public String getName() {
		return name;
	}

	@Override
    @JsonSetter("name")
    public void setName(String name) {
		this.name = name;
	}

	@Override
    @Column(name="bezeichnung", nullable=true, length=100)
    @JsonGetter("description")
    @JsonView(Views.DropDown.class)
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
    @JsonSetter("description")
    public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append("name", getName())
				.append("bezeichnung", getBezeichnung())
				.toString();
	}
}