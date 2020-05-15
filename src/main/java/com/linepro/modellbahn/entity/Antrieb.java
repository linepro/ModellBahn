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
 * Antrieb. Represents a drive method.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ANTRIEB)
@Table(name = DBNames.ANTRIEB,
    indexes = { 
        @Index(name = DBNames.ANTRIEB + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ANTRIEB + "_UC1", columnNames = { DBNames.NAME }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class Antrieb extends NamedItemImpl {
}