package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Sondermodell. Special category for a product. E.g. MHI, Einmaligeserien
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.SONDERMODELL)
@Table(name = DBNames.SONDERMODELL,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.SONDERMODELL + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.sondermodell.notunique}")
public class Sondermodell extends NamedItemImpl {
}