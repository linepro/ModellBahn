package com.linepro.modellbahn.model.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * AbstractNamedItem. Base class for items with a name (business key) and a description.
 *
 * @author $Author$
 * @version $Id$
 */
@MappedSuperclass
public abstract class AbstractNamedItem<K extends IKey> extends AbstractItem<K> implements
    INamedItem<K>, Serializable {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = -278823660682127691L;

  protected static final String[] BUSINESS_KEY = new String[]{ApiNames.NAMEN};

  protected static final List<Character> URL_SPECIAL_CHARS = Arrays
      .asList(':', '/', '@', '[', ']', '?', '&', '=', '+');

  /**
   * The name.
   */
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
   * @param deleted the deleted
   */
  public AbstractNamedItem(Long id, String name, String bezeichnung, Boolean deleted) {
    super(id, deleted);

    setName(name);
    setBezeichnung(bezeichnung);
  }

  @Override
  @BusinessKey
  @Column(name = DBNames.NAME, unique = true, length = 50)
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
  @Transient
  public String getLinkId() {
    return getName().codePoints()
        .mapToObj(this::encodeChar)
        .collect(Collectors.joining(""));
  }

  private String encodeChar(int ch) {
    return isUrlSpecialChar(ch) ?
        Character.toString((char) ch) :
        String.format("%%%02x", ch);
  }

  private boolean isUrlSpecialChar(int ch) {
    return URL_SPECIAL_CHARS.contains((char) ch);
  }

  @Override
  public int compareTo(IItem<?> other) {
    if (other instanceof AbstractNamedItem) {
      return new CompareToBuilder()
          .append(getName(), ((AbstractNamedItem<?>) other).getName())
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