/**
 * 
 */
package com.linepro.modellbahn.entity.impl;

import java.nio.file.Path;

import javax.persistence.Column;
import javax.persistence.Convert;

import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.NamedWithAbbildung;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * @author JohnG
 *
 */
//@formatter:on
public class NamedWithAbbildungImpl extends NamedItemImpl implements NamedWithAbbildung {

    /** The abbildung. */
    private Path abbildung;

    /**
     * 
     */
    public NamedWithAbbildungImpl() {
    }

    /**
     * @param name
     */
    public NamedWithAbbildungImpl(String name) {
        super(name);
    }

    /**
     * @param id
     * @param name
     * @param bezeichnung
     * @param deleted
     */
    public NamedWithAbbildungImpl(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }

    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    public Path getAbbildung() {
        return abbildung;
    }
    
    public void setAbbildung(Path abbildung) {
        this.abbildung = abbildung;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(DBNames.ABBILDUNG, getAbbildung())
            .toString();
    }
}
