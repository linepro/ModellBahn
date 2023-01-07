package com.linepro.modellbahn.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Country;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.bahnverwaltung.notunique}")
public class Bahnverwaltung extends NamedWithAbbildungImpl implements Serializable {

    private static final long serialVersionUID = 5530555691539153846L;

    @Column(name = DBNames.LAND, length = 2)
    @Country(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String land;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("land", land)
            .toString();
    }
}