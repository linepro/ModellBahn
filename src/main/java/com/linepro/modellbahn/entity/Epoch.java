package com.linepro.modellbahn.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Positive;

import com.linepro.modellbahn.entity.impl.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Epoch. The NEM 800 Epoch for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.EPOCH)
@Table(name = DBNames.EPOCH,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.EPOCH + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.epoch.notunique}")
public class Epoch extends NamedWithAbbildungImpl implements Serializable {

    private static final long serialVersionUID = -2775082120253161739L;

    @Column(name = DBNames.START_YEAR)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.startYear}")
    private Integer startYear;

    @Column(name = DBNames.END_YEAR)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.endYear}")
    private Integer endYear;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("startYear", startYear)
            .append("endYear", endYear)
            .toString();
    }
}