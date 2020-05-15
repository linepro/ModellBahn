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
 * ZugTyp. The type of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ZUG_TYP)
@Table(name = DBNames.ZUG_TYP,
    indexes = { 
            @Index(name = DBNames.ZUG_TYP + "_IX1", columnList = DBNames.NAME, unique = true) 
        }, uniqueConstraints = {
            @UniqueConstraint(name = DBNames.ZUG_TYP + "_UC1", columnNames = { DBNames.NAME }) 
        })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class ZugTyp extends NamedItemImpl {
}