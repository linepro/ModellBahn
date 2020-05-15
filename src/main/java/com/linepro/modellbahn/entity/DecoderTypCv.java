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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.validation.CVValue;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTypCv.
 * The CVs available for a given DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_CV)
@Table(name = DBNames.DECODER_TYP_CV,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_CV + "_IX1", columnList = DBNames.DECODER_TYP_ID +"," + DBNames.CV, unique = true),
        @Index(name = DBNames.DECODER_TYP_CV + "_IX2", columnList = DBNames.DECODER_TYP_ID), 
        @Index(name = DBNames.DECODER_TYP_CV + "_IX3", columnList = DBNames.CV) }, 
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_CV + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.CV }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class DecoderTypCv extends ItemImpl {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_CV + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The cv. */
    @Column(name = DBNames.CV, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    @Range(min=1, max=255, message = "{com.linepro.modellbahn.validator.constraints.cv.range}")
    private Integer cv;

    /** The bezeichnung. */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The minimal. */
    @Column(name = DBNames.MINIMAL)
    @CVValue
    private Integer minimal;

    /** The maximal. */
    @Column(name = DBNames.MAXIMAL)
    @CVValue
    private Integer maximal;

    /** The werkseinstellung. */
    @Column(name = DBNames.WERKSEINSTELLUNG)
    @CVValue
    private Integer werkseinstellung;
}