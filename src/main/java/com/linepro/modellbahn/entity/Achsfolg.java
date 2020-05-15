/*
 * Achsfolg
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Achsfolg Represents Axle configuration using German nomenclature.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ACHSFOLG)
@Table(name = DBNames.ACHSFOLG,
    indexes = { 
        @Index(name = DBNames.ACHSFOLG + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ACHSFOLG + "_UC1", columnNames = { DBNames.NAME }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class Achsfolg extends NamedItemImpl {
}