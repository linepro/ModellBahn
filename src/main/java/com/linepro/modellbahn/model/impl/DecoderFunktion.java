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
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypFunktionSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderFunktion.
 * The functions supported by a Decoder (Key : description) 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderFunktion")
@Table(name = "DecoderFunktion", indexes = { @Index(columnList = DBNames.DECODER_ID + "," + DBNames.FUNKTION_ID, unique = true) },
       uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.DECODER_ID, DBNames.FUNKTION_ID })})
@JsonRootName(value = ApiNames.FUNKTION)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER, ApiNames.FUNKTION,  ApiNames.DESCRIPTION, ApiNames.DELETED, ApiNames.LINKS})
public class DecoderFunktion extends AbstractItem implements IDecoderFunktion {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3254516717556070251L;

    /** The decoder. */
    private IDecoder decoder;
    
    /** The funktion. */
    private IDecoderTypFunktion funktion;

	/** The wert. */
	private String bezeichnung;

	/**
	 * Instantiates a new decoder funktion.
	 */
	public DecoderFunktion() {
		super();
	}

	/**
	 * Instantiates a new decoder funktion.
	 *
	 * @param decoder the decoder
	 * @param funktion the funktion
	 * @param wert the wert
	 */
	public DecoderFunktion(IDecoder decoder, IDecoderTypFunktion funktion, String wert) {
        setDecoder(decoder);
        setFunktion(funktion);		
        setBezeichnung(wert);
	}

	@Override
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name=DBNames.DECODER_ID, nullable = false, referencedColumnName=DBNames.ID, foreignKey = @ForeignKey(name = "decoder_fn_fk1"))
    @JsonGetter(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.ID, resolver=DecoderResolver.class)
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
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypFunktion.class)
    @JoinColumn(name = DBNames.FUNKTION_ID, nullable = false, referencedColumnName=DBNames.ID, foreignKey = @ForeignKey(name = "decoder_fn_fk2"))
    @JsonGetter(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=DecoderTypFunktionSerializer.class)
    public IDecoderTypFunktion getFunktion() {
        return funktion;
    }

    @Override
    @JsonSetter(ApiNames.FUNKTION)
    @JsonDeserialize(as=DecoderTypFunktion.class)
    public void setFunktion(IDecoderTypFunktion funktion) {
        this.funktion = funktion;
    }

    @Override
	@Column(name=DBNames.DESCRIPTION, nullable=true, length=100)
    @JsonGetter(ApiNames.DESCRIPTION)
    @JsonView(Views.DropDown.class)
	public String getBezeichnung() {
		return bezeichnung;
	}

    @Override
    @JsonSetter(ApiNames.DESCRIPTION)
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.DECODER_FN_LINK, getParentId(), getFunktion().getName());
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return getDecoder().getLinkId();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDecoder())
                .append(getFunktion())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderFunktion)) {
            return false;
        }

        DecoderFunktion other = (DecoderFunktion) obj;

        return new EqualsBuilder()
                .append(getDecoder(), other.getDecoder())
                .append(getFunktion(), other.getFunktion())
                .isEquals();
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(ApiNames.DECODER, getDecoder())
				.append(ApiNames.ID, getId())
				.append("wert", getBezeichnung())
				.toString();
	}
}
