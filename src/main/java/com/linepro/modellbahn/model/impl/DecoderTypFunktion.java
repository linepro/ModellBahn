package com.linepro.modellbahn.model.impl;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.DecoderTypSerializer;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTypFunktion. The Functions available for a DecoderTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderTypFunktion")
@Table(name = "decoder_typ_funktionen", indexes = { @Index(columnList = "decoder_typ_id,reihe,name", unique = true),
        @Index(columnList = "decoder_typ_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "decoder_typ_id", "reihe", "name" }) })
@JsonRootName(value = "function")
@AttributeOverride(name = "name", column = @Column(name = "name", unique = false, length = 4))
public class DecoderTypFunktion extends AbstractNamedItem implements IDecoderTypFunktion {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9194895557054214626L;

    /** The decoder typ. */
    private IDecoderTyp decoderTyp;

    /** The reihe. */
    private Integer reihe;

    /** The programmable. */
    private Boolean programmable;

    /**
     * Instantiates a new decoder typ funktion.
     */
    public DecoderTypFunktion() {
        super();
    }

    public DecoderTypFunktion(IDecoderTyp decoderTyp, Integer reihe, String funktionNr) {
        super(funktionNr);

        setDecoderTyp(decoderTyp);
        setReihe(reihe);
    }

    /**
     * Instantiates a new decoder typ funktion.
     *
     * @param id
     *            the id
     * @param decoderTyp
     *            the decoder typ
     * @param reihe
     *            the reihe
     * @param funktionNr
     *            the funktion nr
     * @param bezeichnung
     *            the bezeichnung
     * @param programmable
     *            the programmable
     * @param deleted
     *            the deleted
     */
    public DecoderTypFunktion(Long id, IDecoderTyp decoderTyp, Integer reihe, String funktionNr, String bezeichnung,
            Boolean programmable, Boolean deleted) {
        super(id, funktionNr, bezeichnung, deleted);

        setDecoderTyp(decoderTyp);
        setReihe(reihe);
        setProgrammable(programmable);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_typ_fn_fk1"))
    @JsonGetter("decoderTyp")
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentUsing=DecoderTypSerializer.class)
    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    @Override
    @JsonSetter("decoderTyp")
    public void setDecoderTyp(IDecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    @Override
    @BusinessKey
    @Column(name = "reihe", nullable = false)
    @JsonGetter("bank")
    @JsonView(Views.DropDown.class)
    public Integer getReihe() {
        return reihe;
    }

    @Override
    @JsonSetter("bank")
    public void setReihe(Integer reihe) {
        this.reihe = reihe;
    }

    @Override
    @Column(name = "programmable", nullable = false)
    @JsonGetter("programmable")
    @JsonView(Views.Public.class)
    public Boolean getProgrammable() {
        return programmable;
    }

    @Override
    @JsonSetter("programmable")
    public void setProgrammable(Boolean programmable) {
        this.programmable = programmable;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return getDecoderTyp().getLinkId() + "/fn/" + getReihe() + "/" + getName();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoderTyp())
                .append(getReihe())
                .append(getName())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderTypFunktion)) {
            return false;
        }

        DecoderTypFunktion other = (DecoderTypFunktion) obj;

        return new EqualsBuilder()
                .append(getDecoderTyp(), other.getDecoderTyp())
                .append(getReihe(), other.getReihe())
                .append(getName(), other.getName())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("decoderTyp", getDecoderTyp())
                .append("reihe", getReihe())
                .append("programmable", getProgrammable())
                .toString();
    }
}