package com.linepro.modellbahn.model.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * AbstractNamedItem. Base class for items with a name (business key) and a description.
 *
 * @author $Author$
 * @version $Id$
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractNamedItem<T extends RepresentationModel<? extends T>> extends AbstractItem<T> implements INamedItem {

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
  public AbstractNamedItem() {
  }

  /**
   * Convienience method for lookups
   *
   * @param name the item name
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
   * @param deleted if <code>true</code> this item is soft deleted, otherwise it is active
   */
  public AbstractNamedItem(Long id, String name, String bezeichnung, Boolean deleted) {
    super(id, deleted);

    setName(name);
    setBezeichnung(bezeichnung);
  }

  @BusinessKey
  @Column(name = DBNames.NAME, unique = true, length = 50)
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = (name != null ? name.toUpperCase() : name);
  }

  @Column(name = DBNames.BEZEICHNUNG, length = 100)
  public String getBezeichnung() {
    return bezeichnung;
  }

  @Override
  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  @Override
  @Transient
  public String getLinkId() {
    return getName();
  }

  @Override
  public int compareTo(IItem other) {
    if (other instanceof INamedItem) {
      return new CompareToBuilder()
          .append(getName(), ((INamedItem) other).getName())
          .toComparison();
    }

    return super.compareTo(other);
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

    AbstractNamedItem<?> other = (AbstractNamedItem<?>) obj;

    return new EqualsBuilder()
        .append(getName(), other.getName())
        .isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .appendSuper(super.toString())
        .append(ApiNames.NAMEN, getName())
        .append(ApiNames.BEZEICHNUNG, getBezeichnung())
        .toString();
  }
}