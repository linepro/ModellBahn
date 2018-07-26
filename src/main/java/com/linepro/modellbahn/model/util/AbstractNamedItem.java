package com.linepro.modellbahn.model.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * AbstractNamedItem.
 * Base class for items with a name (business key) and a description.
 * @author   $Author$
 * @version  $Id$
 */
@MappedSuperclass
@JsonPropertyOrder({ApiNames.ID,ApiNames.NAME,ApiNames.DESCRIPTION,ApiNames.DELETED, ApiNames.LINKS})
public abstract class AbstractNamedItem extends AbstractItem implements INamedItem, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -278823660682127691L;

    protected static final String[] BUSINESS_KEY = new String[] { ApiNames.NAME };

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
     * Convienience method for lookups
     * @param name
     */
    public AbstractNamedItem(String name) {
        super(null, null);

        setName(name);
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
    @Column(name=ApiNames.NAME, unique=true, length=50, nullable = true)
    @JsonGetter(ApiNames.NAME)
    @JsonView(Views.DropDown.class)
	public String getName() {
		return name;
	}

	@Override
    @JsonSetter(ApiNames.NAME)
    public void setName(String name) {
		this.name = name;
	}

	@Override
    @Column(name=ApiNames.DESCRIPTION, nullable=true, length=100)
    @JsonGetter(ApiNames.DESCRIPTION)
    @JsonView(Views.DropDown.class)
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
    @JsonSetter(ApiNames.DESCRIPTION)
    public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getName())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AbstractNamedItem)) {
            return false;
        }

        AbstractNamedItem other = (AbstractNamedItem) obj;

        return new EqualsBuilder()
                .append(getName(), other.getName())
                .isEquals();
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return getName();
    }

    @Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append(ApiNames.NAME, getName())
				.append(ApiNames.DESCRIPTION, getBezeichnung())
				.toString();
	}
}