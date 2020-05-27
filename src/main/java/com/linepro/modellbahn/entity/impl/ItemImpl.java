package com.linepro.modellbahn.entity.impl;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * AbstractItem.
 * The base class for all items 
 * @author  $Author:$
 * @version $Id:$
 */
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class ItemImpl implements Item {

    /** The primary key id. */
    @Id
    @Column(name = DBNames.ID, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.id.notnull}")
    private Long id;

	/** The soft deleted state. */
    @Column(name=DBNames.DELETED, length=5, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.deleted.notnull}")
	private Boolean deleted;

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

        ItemImpl other = (ItemImpl) obj;

        return Objects.equals(id, other.id);
    }
}