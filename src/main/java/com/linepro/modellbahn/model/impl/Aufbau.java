package com.linepro.modellbahn.model.impl;

import java.nio.file.Path;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Aufbau. Represents a construction method.
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.AUFBAU)
@Table(name = DBNames.AUFBAU, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
@JsonRootName(value = ApiNames.AUFBAU)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
public class Aufbau extends AbstractNamedItem<NameKey> implements IAufbau {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 233985591709407388L;

    /** The abbildung. */
    private Path abbildung;

    /**
     * Instantiates a new aufbau.
     */
    public Aufbau() {
        super();
    }

    public Aufbau(String name) {
        super(name);
    }

    /**
     * Instantiates a new aufbau.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     */
    public Aufbau(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }

    @Override
    @Column(name = DBNames.ABBILDUNG, nullable = true)
    @Convert(converter = PathConverter.class)
    @JsonGetter(ApiNames.ABBILDUNG)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=FileSerializer.class)
    public Path getAbbildung() {
        return abbildung;
    }

    @Override
    public void setAbbildung(Path abbildung) {
        this.abbildung = abbildung;
    }
}