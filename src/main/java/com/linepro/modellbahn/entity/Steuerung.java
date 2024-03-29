package com.linepro.modellbahn.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Steuerung. The steering method for a product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.STEUERUNG)
@Table(name = DBNames.STEUERUNG,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.STEUERUNG + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.steuerung.notunique}")
public class Steuerung extends NamedWithAbbildungImpl implements Serializable {

    private static final long serialVersionUID = -3623527637631533373L;
}