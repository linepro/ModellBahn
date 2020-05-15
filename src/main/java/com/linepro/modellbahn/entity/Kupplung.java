package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Kupplung. The coupling configuration for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.KUPPLUNG)
@Table(name = DBNames.KUPPLUNG,
    indexes = { 
        @Index(name = DBNames.KUPPLUNG + "_IX1", columnList = DBNames.NAME, unique = true)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.KUPPLUNG + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class Kupplung extends NamedWithAbbildungImpl {
}