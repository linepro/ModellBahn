/*
 * Adress
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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
 * DecoderAdress. An address for a decoder (several have more than one)
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_ADRESS)
@Table(name =  DBNames.DECODER_ADRESS,
    uniqueConstraints = { 
        @UniqueConstraint(name = DBNames.DECODER_ADRESS + "_UC1", columnNames = { DBNames.DECODER_ID, DBNames.ADRESS_ID })
    })
//@formatter:on
@Adress
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DecoderAdress extends ItemImpl implements WithAdress {

    /** The decoder. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name =  DBNames.DECODER_ADRESS + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoder.notnull}")
    private Decoder decoder;

    /** The adress. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DecoderTypAdress.class)
    @JoinColumn(name = DBNames.ADRESS_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_ADRESS + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.cv.notnull}")
    private DecoderTypAdress typ;

    /** The adress. */
    @Column(name = DBNames.ADRESS, nullable = false)
    private Integer adress;

    @Transient
    public AdressTyp getAdressTyp() {
        return typ.getAdressTyp();
    }
}