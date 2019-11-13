package com.linepro.modellbahn.model.util;

import java.util.List;
import java.util.Optional;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * AbstractItem.
 * The base class for all items 
 * @author  $Author:$
 * @version $Id:$
 */
@MappedSuperclass
@Cacheable
public abstract class AbstractItem<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> implements IItem {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 938276986391979417L;

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
	public AbstractItem() {
	}

	/**
	 * Instantiates a new abstract item.
	 *
	 * @param id the id
	 * @param deleted if <code>true</code> this item is soft deleted, otherwise it is active
	 */
    public AbstractItem(Long id, Boolean deleted) {
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

    @Override
    @Transient
    public String getParentId() {
        return null;
    }

    @Override
    @Transient
    public String getLinkId() {
        return getId().toString();
    }

    @Override
    @Transient
    public Links getLinks() {
        return super.getLinks();
    }

    @Override
    @Transient
    public Optional<Link> getLink(String relation) {
        return super.getLink(relation);
    }

    @Override
    @Transient
    public Optional<Link> getLink(LinkRelation relation) {
        return super.getLink(relation);
    }

    @Override
    @Transient
    public Link getRequiredLink(String relation) {
        return super.getRequiredLink(relation);
    }

    @Override
    @Transient
    public Link getRequiredLink(LinkRelation relation) {
        return super.getRequiredLink(relation);
    }

    @Override
    @Transient
    public List<Link> getLinks(String relation) {
        return super.getLinks(relation);
    }

    @Override
    @Transient
    public List<Link> getLinks(LinkRelation relation) {
        return super.getLinks(relation);
    }

    @Override
    public int compareTo(IItem other) {
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

		if (!(obj instanceof AbstractItem)) {
			return false;
		}

		IItem other = (IItem) obj;

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