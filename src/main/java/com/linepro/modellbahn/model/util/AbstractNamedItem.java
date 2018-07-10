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

import com.linepro.modellbahn.model.INamedItem;

/**
 * The Class AbstractNamedItem.
 */
@MappedSuperclass
public abstract class AbstractNamedItem extends AbstractItem implements INamedItem {

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
		
		this.name = name;
		this.bezeichnung = bezeichnung;
	}

	@Override
    @Column(name="NAME", length=50)
	public String getName() {
		return name;
	}

	@Override
    public void setName(String name) {
		this.name = name;
	}

	@Override
    @Column(name="BEZEICHNUNG", nullable=true, length=100)
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
    public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.appendSuper(super.toString())
				.append("name", getName())
				.append("bezeichnung", getBezeichnung())
				.toString();
	}
}