package com.linepro.modellbahn.model.impl;

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
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.DecoderFunktionKey;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.DecoderResolver;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * DecoderFunktion.
 * The functions supported by a Decoder (Key : description) 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.DECODER_FUNKTION)
@Table(name = DBNames.DECODER_FUNKTION, indexes = { @Index(columnList = DBNames.DECODER_ID + "," + DBNames.FUNKTION_ID, unique = true) },
       uniqueConstraints = { @UniqueConstraint(columnNames = { DBNames.DECODER_ID, DBNames.FUNKTION_ID })})
@JsonRootName(value = ApiNames.FUNKTION)
@JsonPropertyOrder({ApiNames.ID, ApiNames.DECODER, ApiNames.FUNKTION,  ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS})
public class DecoderFunktion extends AbstractItem<DecoderFunktionKey> implements IDecoderFunktion {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3254516717556070251L;

    /** The decoder. */
    @NotNull
    private IDecoder decoder;
    
    /** The funktion. */
    @NotNull
    private IDecoderTypFunktion funktion;

	/** The wert. */
    @NotEmpty
	private String bezeichnung;

    private String funktionStr;

    private Integer reihe;

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
	 * @param bezeichnung the bezeichnung
	 */
	public DecoderFunktion(Long id, IDecoder decoder, IDecoderTypFunktion funktion, String bezeichnung, Boolean deleted) {
	    super(id, deleted);

        setDecoder(decoder);
        setFunktion(funktion);		
        setBezeichnung(bezeichnung);
	}

	@Override
    @BusinessKey
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name=DBNames.DECODER_ID, nullable = false, referencedColumnName=DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_FUNKTION + "_fk1"))
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

    @Transient
    @Override
    @JsonGetter(ApiNames.REIHE)
    @JsonView(Views.DropDown.class)
    public Integer getReihe() {
        return reihe;
    }

    @Override
    @JsonSetter(ApiNames.REIHE)
    public void setReihe(Integer reihe) {
        this.reihe = reihe;
    }

    @Transient
    @Override
    @JsonGetter(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    public String getFunktionStr() {
        return funktionStr;
    }

    @Override
    @JsonSetter(ApiNames.FUNKTION)
    public void setFunktionStr(String funktion) {
        this.funktionStr = funktion;
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypFunktion.class)
    @JoinColumn(name = DBNames.FUNKTION_ID, nullable = false, referencedColumnName=DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_FUNKTION + "_fk2"))
    public IDecoderTypFunktion getFunktion() {
        if (funktion != null) {
            setFunktionStr(funktion.getName());
            setReihe(funktion.getReihe());
        }

        return funktion;
    }

    @Override
    @JsonIgnore
    public void setFunktion(IDecoderTypFunktion funktion) {
        this.funktion = funktion;

        if (funktion != null) {
            setFunktionStr(funktion.getName());
            setReihe(funktion.getReihe());
        }
    }

    @Override
	@Column(name=DBNames.BEZEICHNUNG, length=100)
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
	public String getBezeichnung() {
		return bezeichnung;
	}

    @Override
    @JsonSetter(ApiNames.BEZEICHNUNG)
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.DECODER_FUNKTION_LINK, getParentId(), getFunktion().getReihe(), getFunktion().getName());
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return getDecoder().getLinkId();
    }

    @Override
    public int compareTo(IItem<?> other) {
        if (other instanceof DecoderFunktion) {
            return new CompareToBuilder()
                    .append(getDecoder(), ((DecoderFunktion) other).getDecoder())
                    .append(getFunktion(), ((DecoderFunktion) other).getFunktion())
                    .toComparison();
        }
        
        return super.compareTo(other);
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
		return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.DECODER, getDecoder())
				.append(ApiNames.FUNKTION, getFunktion())
				.append(ApiNames.BEZEICHNUNG, getBezeichnung())
				.toString();
	}
}
