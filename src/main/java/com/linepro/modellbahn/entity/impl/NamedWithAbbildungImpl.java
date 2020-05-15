/**
 * 
 */
package com.linepro.modellbahn.entity.impl;

import java.nio.file.Path;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;

import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.NamedWithAbbildung;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author JohnG
 *
 */
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class NamedWithAbbildungImpl extends NamedItemImpl implements NamedWithAbbildung {

    /** The abbildung. */
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    private Path abbildung;
}
