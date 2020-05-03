package com.linepro.modellbahn.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.base.NamedWithAbbildungImpl;
import com.linepro.modellbahn.persistence.DBNames;

/**
 * Aufbau. Represents a construction method.
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.AUFBAU)
@Table(name = DBNames.AUFBAU, indexes = { @Index(columnList = DBNames.NAME, unique = true) }, uniqueConstraints = {
        @UniqueConstraint(columnNames = { DBNames.NAME }) })
public class Aufbau extends NamedWithAbbildungImpl {

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
     *            if  { this item is soft deleted, otherwise it is active
     */
    public Aufbau(Long id, String name, String bezeichnung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }
}