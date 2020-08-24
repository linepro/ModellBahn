/**
 * 
 */
package com.linepro.modellbahn.entity.impl;

import java.nio.file.Path;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@MappedSuperclass
public class NamedWithAbbildungImpl extends NamedItemImpl implements NamedWithAbbildungItem {

    /** The abbildung. */
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    private Path abbildung;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("abbildung", abbildung)
            .toString();
    }
}
