package com.linepro.modellbahn.model.impl;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypCVSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderCV. Holds the CV values for a Decoder (when Konfiguration.CV is in
 * force)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderCV")
@Table(name = "DecoderCV", indexes = {
        @Index(columnList = DBNames.DECODER_ID + "," + DBNames.CV_ID, unique = true) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.DECODER_ID, DBNames.CV_ID }) })
@JsonRootName(value = ApiNames.CV)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.DECODER, ApiNames.CV, ApiNames.WERT, ApiNames.DELETED, ApiNames.LINKS })
public class DecoderCV extends AbstractItem implements IDecoderCV {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2660599652146536110L;

    /** The decoder. */
    private IDecoder decoder;

    /** The cv. */
    private IDecoderTypCV cv;

    /** The wert. */
    private Integer wert;

    /**
     * Instantiates a new decoder CV.
     */
    public DecoderCV() {
        super();
    }

    /**
     * Instantiates a new decoder CV.
     *
     * @param decoder
     *            the decoder
     * @param cv
     *            the cv
     * @param wert
     *            the wert
     */
    public DecoderCV(IDecoder decoder, IDecoderTypCV cv, Integer wert) {
        setDecoder(decoder);
        setCV(cv);
        setWert(wert);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "decoder_cv_fk1"))
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=DecoderResolver.class)
    public IDecoder getDecoder() {
        return decoder;
    }

    @Override
    @JsonSetter(ApiNames.DECODER)
    @JsonDeserialize(as=Decoder.class)
    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTypCV.class)
    @JoinColumn(name = DBNames.CV_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "decoder_cv_fk2"))
    @JsonGetter(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=DecoderTypCVSerializer.class)
    public IDecoderTypCV getCV() {
        return cv;
    }

    @Override
    @JsonSetter(ApiNames.CV)
    @JsonDeserialize(as=DecoderTypCV.class)
    public void setCV(IDecoderTypCV cv) {
        this.cv = cv;
    }

    @Override
    @Column(name = "wert", nullable = true)
    @JsonGetter(ApiNames.WERT)
    @JsonView(Views.DropDown.class)
    public Integer getWert() {
        return wert;
    }

    @Override
    @JsonSetter(ApiNames.WERT)
    public void setWert(Integer wert) {
        this.wert = wert;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return decoder.getLinkId();
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.DECODER_CV_LINK, getParentId(), getCV());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoder())
                .append(getCV())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderCV)) {
            return false;
        }

        DecoderCV other = (DecoderCV) obj;

        return new EqualsBuilder()
                .append(getDecoder(), other.getDecoder())
                .append(getCV(), other.getCV())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(ApiNames.DECODER, getDecoder().getId())
                .append(ApiNames.CV, getCV())
                .append("wert", getWert())
                .toString();
    }
}
