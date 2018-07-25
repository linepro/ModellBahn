package com.linepro.modellbahn.model.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.ws.rs.core.Link;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.rest.json.LinkSerializer;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * AbstractItem.
 * The base class for all items 
 * @author  $Author:$
 * @version $Id:$
 */
@MappedSuperclass
@Cacheable
@JsonAutoDetect(fieldVisibility = Visibility.PUBLIC_ONLY)
public abstract class AbstractItem implements Serializable, IItem {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 938276986391979417L;

    /** The id. */
    private Long id;

	/** The deleted. */
	private Boolean deleted;
	
    protected final Set<Link> links = new HashSet<Link>();

	/**
	 * Instantiates a new abstract item.
	 */
	public AbstractItem() {
	}

	/**
	 * Instantiates a new abstract item.
	 *
	 * @param id the id
	 * @param deleted the deleted
	 */
	public AbstractItem(Long id, Boolean deleted) {
		setId(id);
		setDeleted(deleted);
	}

	@Override
    @Id
	@Column(name="id", nullable = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonGetter("id")
	@JsonView(Views.Internal.class)
	public Long getId() {
		return id;
	}

	@Override
    @JsonSetter("id")
    public void setId(Long id) {
		this.id = id;
	}

	@Override
    @Column(name="deleted", length=5, nullable = true)
    @JsonView(Views.Public.class)
    @JsonGetter("deleted")
	public Boolean getDeleted() {
		return deleted;
	}

	@Override
    @JsonSetter("deleted")
    public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

    @Override
    @Transient
    @JsonGetter("links")
    @JsonSerialize(contentUsing=LinkSerializer.class)
    public Set<Link> getLinks() {
        return links;
    }
    
    @Override
    public void addLink(Link link) {
        links.add(link);
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return getId().toString();
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

		if (getClass() != obj.getClass()) {
			return false;
		}

		IItem other = (IItem) obj;

		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		        .append("id", getId())
				.append("deleted", getDeleted())
				.toString();
	}
}