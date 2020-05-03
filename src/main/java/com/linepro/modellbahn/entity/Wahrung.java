package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Wahrung.
 * Currency for a price.
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.WAHRUNG)
@Table(name = DBNames.WAHRUNG, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.NAME }) })
public class Wahrung extends NamedItemImpl {
	
    /** The dezimal. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.dezimal.notnull}")
    @Range(min=0, max=2, message = "{com.linepro.modellbahn.validator.constraints.dezimal.range}")
    private Integer dezimal;
	
	/**
	 * Instantiates a new wahrung.
	 */
	public Wahrung() {
		super();
	}

    public Wahrung(String name) {
        super(name);
    }

    /**
	 * Instantiates a new wahrung.
	 *
	 * @param id the id
	 * @param name the name
	 * @param bezeichnung the bezeichnung
	 * @param dezimal the dezimal
	 * @param deleted if  { this item is soft deleted, otherwise it is active
	 */
	public Wahrung(Long id, String name, String bezeichnung, Integer dezimal, Boolean deleted) {
		super(id, name, bezeichnung, deleted);

		setDecimals(dezimal);
	}

	
    @Column(name=DBNames.DEZIMAL)
	public Integer getDecimals() {
		return dezimal;
	}

	
    public void setDecimals(Integer dezimal) {
		this.dezimal = dezimal;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		        .appendSuper(super.toString())
		        .append(ApiNames.DEZIMAL, getDecimals())
		        .toString();
	}
}
