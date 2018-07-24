package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderFunktion.
 * The functions supported by a Decoder (Key : description) 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "DecoderFunktion")
@Table(name = "decoder_funktion", indexes = { @Index(columnList = "decoder_id,funktion_id", unique = true) },
       uniqueConstraints = { @UniqueConstraint(columnNames = { "decoder_id", "funktion_id" })})
public class DecoderFunktion extends AbstractItem implements IDecoderFunktion {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3254516717556070251L;

    /** The decoder. */
    private IDecoder decoder;
    
    /** The funktion. */
    private IDecoderTypFunktion funktion;

	/** The wert. */
	private String wert;

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
        setWert(wert);
	}

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Decoder.class)
    @JoinColumn(name="decoder_id", nullable = false, referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_fn_fk1"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTypFunktion.class)
    @JoinColumn(name="funktion_id", nullable = false, referencedColumnName="id", foreignKey = @ForeignKey(name = "decoder_fn_fk2"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IDecoderTypFunktion getFunktion() {
        return funktion;
    }

    public void setFunktion(IDecoderTypFunktion funktion) {
        this.funktion = funktion;
    }

    @Override
	@Column(name="wert", nullable=true, length=100)
	public String getWert() {
		return wert;
	}

    @Override
	public void setWert(String wert) {
		this.wert = wert;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("decoder", getDecoder())
				.append("id", getId())
				.append("wert", getWert())
				.toString();
	}
}
