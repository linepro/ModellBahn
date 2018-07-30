package com.linepro.modellbahn.model.util;

import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.PUT;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
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
import javax.ws.rs.core.UriBuilder;

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
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.LinkSerializer;
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
@JsonAutoDetect(fieldVisibility = Visibility.PUBLIC_ONLY)
public abstract class AbstractItem<K extends IKey> implements Serializable, IItem<K> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 938276986391979417L;

    /** The primary key id. */
    private Long id;

	/** The soft deleted state. */
	private Boolean deleted;
	
	/** Set of HATEOAS links for Json serialization */
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
	@Column(name=DBNames.ID, nullable = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonGetter(ApiNames.ID)
	@JsonView(Views.Internal.class)
	public Long getId() {
		return id;
	}

	@Override
    @JsonSetter(ApiNames.ID)
    public void setId(Long id) {
		this.id = id;
	}

	@Override
    @Column(name=DBNames.DELETED, length=5, nullable = true)
    @JsonView(Views.Public.class)
    @JsonGetter(ApiNames.DELETED)
	public Boolean getDeleted() {
		return deleted;
	}

	@Override
    @JsonSetter(ApiNames.DELETED)
    public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

    @Override
    @Transient
    @JsonGetter(ApiNames.LINKS)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentUsing=LinkSerializer.class)
    public Set<Link> getLinks() {
        return links;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return null;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return getId().toString();
    }

    protected Link makeLink(URI uri, String path, String rel, String method) {
        return Link.fromUri(UriBuilder.fromUri(uri).path( path).build()).rel(rel).type(method).build();
    }
    
    @Override
    public IItem<K> addLinks(URI root, boolean update, boolean delete) {
        getLinks().clear();

        addParent(root);
        addSelf(root);

        if (update) {
            addUpdate(root);
        }
        
        if (delete) {
            addDelete(root);
        }

        addChildLinks(root);

        return this;
    }
    
    protected void addChildLinks(URI root) {
    }

    protected void addLinks(URI root, Collection<? extends IItem<?>> items, boolean update, boolean delete) {
        if (!items.isEmpty()) {
            for (IItem<?> item : items) {
                item.addLinks(root, update, delete);
            }
        }
        
    }

    protected void addParent(URI root) {
        if (getParentId() != null) {
            getLinks().add(makeLink(root, getParentId(), "parent", GET));
        }
    }

    protected void addDelete(URI root) {
        getLinks().add(makeLink(root, getLinkId(), "delete", DELETE));
    }

    protected void addSelf(URI root) {
        getLinks().add(makeLink(root, getLinkId(), "self", GET));
    }

    protected void addUpdate(URI root) {
        getLinks().add(makeLink(root, getLinkId(), "update", PUT));
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

		IItem<?> other = (IItem<?>) obj;

		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		        .append(ApiNames.ID, getId())
				.append(ApiNames.DELETED, getDeleted())
				.toString();
	}
}