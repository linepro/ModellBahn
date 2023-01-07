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
 * Protokoll. The communications protocol used by a Decoder
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PROTOKOLL)
@Table(name = DBNames.PROTOKOLL,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PROTOKOLL + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notunique}")
public class Protokoll extends NamedWithAbbildungImpl implements Serializable {

    private static final long serialVersionUID = 3939324549043705286L;
}