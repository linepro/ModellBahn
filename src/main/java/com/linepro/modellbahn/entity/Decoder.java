package com.linepro.modellbahn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.DecoderId;
import com.linepro.modellbahn.validation.Fahrstufe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Decoder.
 * Represents a decoder (with its settings).
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER)
@Table(name = DBNames.DECODER, 
    indexes = {
        @Index(name = DBNames.DECODER + "_IX1", columnList = DBNames.DECODER_TYP_ID),
        @Index(name = DBNames.DECODER + "_IX2", columnList = DBNames.PROTOKOLL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER + "_UC1", columnNames = { DBNames.DECODER_ID }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Decoder extends ItemImpl {

    @DecoderId
    @Column(name=DBNames.DECODER_ID, unique=true, length=6, nullable = false, updatable = false)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "{com.linepro.modellbahn.validator.constraints.decoderId.invalid}")
    private String decoderId;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The typ. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The protokoll. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notnull}")
    private Protokoll protokoll;

    /** The fahrstufe. */
    @Column(name = DBNames.FAHRSTUFE)
    @Fahrstufe
    private Integer fahrstufe;

    @Column(name = DBNames.STATUS)
    @Enumerated(EnumType.STRING)
    private DecoderStatus status;

    /** The adressen. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderAdress.class, orphanRemoval = true)
    private Set<DecoderAdress> adressen;

    /** The cvs. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderCv.class, orphanRemoval = true)
    private Set<DecoderCv> cvs;

    /** The funktionen. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderFunktion.class, orphanRemoval = true)
    private Set<DecoderFunktion> funktionen;

    public void addAdress(DecoderAdress adress) {
        adress.setDecoder(this);
        if (adressen == null) { adressen = new HashSet<>(); };
        adressen.add(adress);
    }
    
    public void removeAdress(DecoderAdress adress) {
        adressen.remove(adress);
    }

    public void addCv(DecoderCv cv) {
        cv.setDecoder(this);
        if (cvs == null) { cvs = new HashSet<>(); };
        cvs.add(cv);
    }

    public void removeCv(DecoderCv cv) {
        cvs.remove(cv);
    }

    public void addFunktion(DecoderFunktion funktion) {
        funktion.setDecoder(this);
        funktion.setDeleted(false);
        if (funktionen == null) { funktionen = new HashSet<>(); };
        funktionen.add(funktion);
    }

    
    public void removeFunktion(DecoderFunktion funktion) {
        funktionen.remove(funktion);
    }
}