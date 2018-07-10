/*
 * Adress
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IAdress;

/**
 * The Class Adress represents a decoder Address.
 */
@Entity
@Table(name = "ADRESSEN", indexes = { @Index(columnList = "TYP_ID,ADRESS", unique = true) })
public class Adress implements IAdress {

    /** The id. */
    private AdressId id;

    /** The poles. */
    private Integer poles;

    /** The switches. */
    private Long switches;

    /**
     * Instantiates a new adress.
     */
    public Adress() {
    }

    /**
     * Instantiates a new adress.
     *
     * @param id
     *            the id
     * @param poles
     *            the poles
     * @param switches
     *            the switches
     */
    public Adress(AdressId id, Integer poles, Long switches) {
        setId(id);
        setPoles(poles);
        setSwitches(switches);
    }

    @Id
    public AdressId getId() {
        return id;
    }
    
    public void setId(AdressId id) {
        this.id = id;
    }

    @Override
    @Column(name = "POLES", nullable = true)
    public Integer getPoles() {
        return poles;
    }

    @Override
    public void setPoles(Integer poles) {
        this.poles = poles;
    }

    @Override
    @Column(name = "SWITCHES", nullable = true)
    public Long getSwitches() {
        return switches;
    }

    @Override
    public void setSwitches(Long switches) {
        this.switches = switches;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        IAdress other = (IAdress) obj;

        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("id", getId()).append("poles", getPoles())
                .append("switches", getSwitches()).toString();
    }
}