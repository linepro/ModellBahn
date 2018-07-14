package com.linepro.modellbahn.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linepro.modellbahn.model.ILand;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "LANDER", indexes = { @Index(columnList = "NAME", unique = true) })
public class Land extends AbstractNamedItem implements ILand {

    private static final long serialVersionUID = -5352015940349871580L;

    private IWahrung wharung;

    public Land() {
    }

    public Land(Long id, String name, String bezeichnung, IWahrung wharung, Boolean deleted) {
        super(id, name, bezeichnung, deleted);

        this.wharung = wharung;
    }

    @Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Wahrung.class)
    @JsonBackReference
    public IWahrung getWahrung() {
        return wharung;
    }

    @Override
    public void setWahrung(IWahrung wharung) {
        this.wharung = wharung;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("wahrung", getWahrung())
                .toString();
    }
}