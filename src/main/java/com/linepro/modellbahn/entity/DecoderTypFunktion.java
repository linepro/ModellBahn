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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTypFunktion. The Functions available for a DecoderTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP_FUNKTION)
@Table(name = DBNames.DECODER_TYP_FUNKTION,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP_FUNKTION + "_IX1", columnList = DBNames.DECODER_TYP_ID + "," + DBNames.REIHE + "," + DBNames.FUNKTION, unique = true),
        @Index(name = DBNames.DECODER_TYP_FUNKTION + "_IX2", columnList = DBNames.DECODER_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP_FUNKTION + "_UC1", columnNames = { DBNames.DECODER_TYP_ID, DBNames.REIHE, DBNames.FUNKTION })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class DecoderTypFunktion extends ItemImpl {

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP_FUNKTION + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The reihe. */
    @Column(name = DBNames.REIHE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.reihe.notnull}")
    @Range(min=1, max=2, message = "{com.linepro.modellbahn.validator.constraints.reihe.range}")
    private Integer reihe;

    @Column(name = DBNames.FUNKTION, length = 4)
    @Pattern(regexp = "^F([12]\\d|3[012]|\\d)$|^K(1[012345]|\\d)$|^S[0123456]$", message = "{com.linepro.modellbahn.validator.constraints.funktion.invalid}")
    private String funktion;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;
    
    /** The programmable. */
    @Column(name = DBNames.PROGRAMMABLE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.programmable.notnull}")
    private Boolean programmable;
}