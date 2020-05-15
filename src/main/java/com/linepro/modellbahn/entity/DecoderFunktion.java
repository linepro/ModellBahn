package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderFunktion.
 * The functions supported by a Decoder (Key : description) 
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_FUNKTION)
@Table(name = DBNames.DECODER_FUNKTION,
    indexes = { 
        @Index(name = DBNames.DECODER_FUNKTION + "_IX1", columnList = DBNames.DECODER_ID + "," + DBNames.FUNKTION_ID, unique = true)
    }, uniqueConstraints = { 
        @UniqueConstraint(name = DBNames.DECODER_FUNKTION + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.FUNKTION_ID })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class DecoderFunktion extends ItemImpl {

    /** The decoder. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_ADRESS + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;
    
    /** The funktion. */
    @ManyToOne(fetch=FetchType.EAGER, targetEntity=DecoderTypFunktion.class)
    @JoinColumn(name = DBNames.FUNKTION_ID, nullable = false, referencedColumnName=DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_FUNKTION + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.funktion.notnull}")
    private DecoderTypFunktion funktion;

	/** The wert. */
    @Column(name=DBNames.BEZEICHNUNG, length=100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.wert.notnull}")
	private String bezeichnung;
}
