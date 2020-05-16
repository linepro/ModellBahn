package com.linepro.modellbahn.entity;

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

import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.WithAdress;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.Adress;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTypAdress.
 * The Adresss available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_ADRESS)
@Table(name = DBNames.DECODER_TYP_ADRESS,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX1", columnList = DBNames.DECODER_TYP_ID), 
        @Index(name = DBNames.DECODER_TYP_ADRESS + "_IX2", columnList = DBNames.INDEX)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_ADRESS + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.INDEX })
    })
//@formatter:on
@Adress
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DecoderTypAdress extends ItemImpl implements WithAdress {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_ADRESS + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The index. */
    @Column(name = DBNames.INDEX, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.index.notnull}")
    @Range(min=1, max=10, message = "{com.linepro.modellbahn.validator.constraints.index.range}")
    private Integer index;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The adressTyp. */
    @Column(name = DBNames.ADRESS_TYP, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private AdressTyp adressTyp;

    /** The span. */
    @Column(name = DBNames.SPAN)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.span.notnull}")
    @Range(min=1, max=32, message = "{com.linepro.modellbahn.validator.constraints.span.range}")
    private Integer span;

    /** The adress. */
    @Column(name = DBNames.WERKSEINSTELLUNG)
    private Integer adress;
}