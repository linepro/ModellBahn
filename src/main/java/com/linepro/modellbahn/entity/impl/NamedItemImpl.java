package com.linepro.modellbahn.entity.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * NamedItem. Base class for items with a name (business key) and a description.
 * @author $Author$
 * @version $Id$
 */
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class NamedItemImpl extends ItemImpl implements NamedItem, Comparable<NamedItem> {

    /**
     * The name.
     */
    @Column(name = DBNames.NAME, length = 50, nullable = false)
    private String name;

    /**
     * The bezeichnung.
     */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    private String bezeichnung;

    @Override
    public int compareTo(NamedItem other) {
        return new CompareToBuilder()
            .append(getName(), ((NamedItem) other).getName())
            .toComparison();
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

      if (!(obj instanceof NamedItem)) {
        return false;
      }

      NamedItem other = (NamedItem) obj;

      return new EqualsBuilder()
          .append(getName(), other.getName())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("name", name)
            .append("bezeichnung", bezeichnung)
            .toString();
    }
}
