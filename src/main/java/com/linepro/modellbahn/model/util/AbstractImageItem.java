package com.linepro.modellbahn.model.util;

import java.awt.Image;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class AbstractImageItem extends AbstractNamedItem {

	/**
     * 
     */
    private static final long serialVersionUID = -4832954547714701837L;
    private Image abbildung;

	public AbstractImageItem() {
		super();
	}

	public AbstractImageItem(Long id, String name, String bezeichnung, Image abbildung ,Boolean deleted) {
		super(id, name, bezeichnung, deleted);
		
		this.abbildung = abbildung;
	}

	@Lob
	@Basic(optional=true, fetch=FetchType.LAZY)
	@Column(name="ABBILDUNG", columnDefinition="BLOB NOT NULL")
	public Image getAbbildung() {
		return abbildung;
	}

	public void setAbbildung(Image abbildung) {
		this.abbildung = abbildung;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.appendSuper(super.toString())
				//.append("abbildung", getAbbildung())
				.toString();
	}
}