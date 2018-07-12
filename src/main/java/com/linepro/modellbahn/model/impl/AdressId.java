/*
 * AdressId
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IAdressId;
import com.linepro.modellbahn.model.IAdressTyp;

/**
 * The Class AdressId is the key for an Adress record.
 */
@Embeddable
public class AdressId implements Serializable, IAdressId {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1077139462591790462L;

    /** The typ. */
    private AdressTyp typ;

	/** The adress. */
	private Integer adress;

	/**
	 * Instantiates a new adress id.
	 * @param id 
	 */
	public AdressId() {
	}

	/**
	 * Instantiates a new adress id.
	 *
	 * @param typ the typ
	 * @param adress the adress
	 */
	public AdressId(IAdressTyp typ, Integer adress) {
	    setTyp(typ);
	    setAdress(adress);
	}

    /**
     * Instantiates a new adress id.
     * @param id 
     */
    public AdressId(IAdressId id) {
        this(id.getTyp(), id.getAdress());
    }

	@Transient
	public IAdressTyp getTyp() {
		return getAdressTyp();
	}

	public void setTyp(IAdressTyp typ) {
		setAdressTyp(new AdressTyp(typ));
	}

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=AdressTyp.class)
    @JoinColumn(name = "TYP_ID", referencedColumnName="ID")
    public AdressTyp getAdressTyp() {
        return typ;
    }

    public void setAdressTyp(AdressTyp typ) {
        this.typ = typ;
    }

	@Column(name = "ADRESS")
	public Integer getAdress() {
		return adress;
	}

	public void setAdress(Integer adress) {
		this.adress = adress;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getTyp()).append(getAdress()).hashCode();
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

		IAdressId other = (IAdressId) obj;

		return new EqualsBuilder().append(getTyp(), other.getTyp()).append(getAdress(), other.getAdress()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("typ", getTyp())
				.append("adress", getAdress()).toString();
	}
}