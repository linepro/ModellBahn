package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Country;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Bahnverwaltung. Represents a Railway company.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.BAHNVERWALTUNG)
@Table(name = DBNames.BAHNVERWALTUNG, 
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.BAHNVERWALTUNG + "_UC1", columnNames = { DBNames.NAME }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class Bahnverwaltung extends NamedItemImpl {

    @Column(name = DBNames.LAND, length = 2)
    @Country(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String land;
}