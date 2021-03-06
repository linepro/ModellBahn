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
import lombok.experimental.SuperBuilder;

/**
 * MotorTyp. The (Märklin) motor type for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.MOTOR_TYP)
@Table(name = DBNames.MOTOR_TYP,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.MOTOR_TYP + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.motorTyp.notunique}")
public class MotorTyp extends NamedItemImpl {
}