package com.linepro.modellbahn.entity.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * AbstractNamedItem. Base class for items with a name (business key) and a description.
 * @author $Author$
 * @version $Id$
 */
@MappedSuperclass
public abstract class NamedItemImpl extends ItemImpl implements NamedItem {

    /**
     * The name.
     */
    @Pattern(regexp = "^[A-Z0-9\\-.]+$", message = "{com.linepro.modellbahn.validator.constraints.name.invalid}")
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.name.notempty}")
    private String name;

    /**
     * The bezeichnung.
     */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /**
     * Instantiates a new abstract named item.
     */
    public NamedItemImpl() {
    }

    /**
     * Convienience method for lookups
     * @param name the item name
     */
    public NamedItemImpl(String name) {
        super(null, null);

        setName(name);
    }

    /**
     * Instantiates a new abstract named item.
     * @param id the id
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param deleted if { this item is soft deleted, otherwise it is active
     */
    public NamedItemImpl(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, deleted);

        setName(name);
        setBezeichnung(bezeichnung);
    }

    @Override
    @BusinessKey
    @Column(name = DBNames.NAME, unique = true, length = 50, nullable = false)
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = (name != null ? name.toUpperCase() : name);
    }

    @Override
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public int compareTo(Item other) {
        if (other instanceof NamedItemImpl) {
            return new CompareToBuilder().append(getName(), ((NamedItem) other).getName()).toComparison();
        }

        return super.compareTo(other);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getName()).hashCode();
    }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof NamedItemImpl)) {
      return false;
    }

    NamedItem other = (NamedItem) obj;

    return new EqualsBuilder()
        .append(getName(), other.getName())
        .isEquals();
  }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).append(DBNames.NAME, getName())
                .append(DBNames.BEZEICHNUNG, getBezeichnung()).toString();
    }
}
