package com.linepro.modellbahn.entity.base;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * AbstractItem.
 * The base class for all items 
 * @author  $Author:$
 * @version $Id:$
 */
@MappedSuperclass
@Cacheable
public abstract class ItemImpl implements Item {

    /** The primary key id. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.id.notnull}")
    @GeneratedValue
    private Long id;

	/** The soft deleted state. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.deleted.notnull}")
	private Boolean deleted;

	/**
	 * Instantiates a new abstract item.
	 */
	public ItemImpl() {
	}

	/**
	 * Instantiates a new abstract item.
	 *
	 * @param id the id
	 * @param deleted if  { this item is soft deleted, otherwise it is active
	 */
    public ItemImpl(Long id, Boolean deleted) {
		setId(id);
		setDeleted(deleted);
	}

    @Override
    @Id
	@Column(name=DBNames.ID)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

    @Override
    public void setId(Long id) {
		this.id = id;
	}

    @Override
    @Column(name=DBNames.DELETED, length=5)
	public Boolean getDeleted() {
		return deleted;
	}

    @Override
    public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

    public int compareTo(Item other) {
        return new CompareToBuilder()
                .append(getId(), other.getId())
                .toComparison();
    }
    
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).hashCode();
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof ItemImpl)) {
			return false;
		}

		Item other = (Item) obj;

		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

    @Override
	public String toString() {
		return new ToStringBuilder(this)
		        .append(ApiNames.ID, getId())
				.append(ApiNames.DELETED, getDeleted())
                .appendSuper(super.toString())
				.toString();
	}
}