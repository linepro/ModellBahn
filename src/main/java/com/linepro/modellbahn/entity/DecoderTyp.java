package com.linepro.modellbahn.entity;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.validation.Fahrstufe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DecoderTyp. Represents a Decoder type (manufacturer : part numer)
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER_TYP)
@Table(name = DBNames.DECODER_TYP,
    indexes = { 
        @Index(name = DBNames.DECODER_TYP + "_IX1", columnList = DBNames.HERSTELLER_ID),
        @Index(name = DBNames.DECODER_TYP + "_IX2", columnList = DBNames.PROTOKOLL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER_TYP + "_UC1", columnNames = { DBNames.HERSTELLER_ID, DBNames.BESTELL_NR })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class DecoderTyp extends ItemImpl {

    /** The hersteller. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hersteller.class)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.hersteller.notnull}")
    private Hersteller hersteller;

    @Column(name = DBNames.BESTELL_NR, length = 50)
    @Pattern(regexp = "^[A-Z0-9\\-.]+$", message = "{com.linepro.modellbahn.validator.constraints.bestellNr.invalid}")
    private String bestellNr;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The i max. */
    @Column(name = DBNames.I_MAX, precision = 6, scale = 2)
    @Range(max = 10, min = 0, message = "{com.linepro.modellbahn.validator.constraints.imax.range}")
    private BigDecimal iMax;

    /** The protokoll. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk3"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notnull}")
    private Protokoll protokoll;

    /** The fahrstufe. */
    @Column(name = DBNames.FAHRSTUFE)
    @Fahrstufe
    private Integer fahrstufe;

    /** The sound. */
    @Column(name = DBNames.SOUND, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.sound.notnull}")
    private Boolean sound;

    /** The konfiguration. */
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.KONFIGURATION, nullable = false, length = 15)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.konfiguration.notnull}")
    private Konfiguration konfiguration;

    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.STECKER, nullable = false, length = 10)
    private Stecker stecker;

    /** The anleitungen. */
    @Column(name = DBNames.ANLEITUNGEN, length = 512)
    @Convert(converter = PathConverter.class)
    private Path anleitungen;

    /** The adressen. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypAdress.class, orphanRemoval = true)
    private Set<DecoderTypAdress> adressen;

    /** The cvs. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypCv.class, orphanRemoval = true)
    private Set<DecoderTypCv> cvs;

    /** The funktion. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypFunktion.class, orphanRemoval = true)
    private Set<DecoderTypFunktion> funktionen;

    public void addAdress(DecoderTypAdress adress) {
        adress.setDecoderTyp(this);
        adress.setIndex(getAdressen().size() + 1);
        adress.setDeleted(false);
        if (adressen == null) { adressen = new HashSet<>(); };
        adressen.add(adress);
    }

    public void removeAdress(DecoderTypAdress adress) {
        adressen.remove(adress);
    }

    public void addCv(DecoderTypCv cv) {
        cv.setDecoderTyp(this);
        cv.setDeleted(false);
        if (cvs == null) { cvs = new HashSet<>(); };
        cvs.add(cv);
    }

    public void removeCv(DecoderTypCv cv) {
        cvs.remove(cv);
    }

    public void addFunktion(DecoderTypFunktion funktion) {
        funktion.setDecoderTyp(this);
        funktion.setDeleted(false);
        if (funktionen == null) { funktionen = new HashSet<>(); };
        funktionen.add(funktion);
    }

    public void removeFunktion(DecoderTypFunktion funktion) {
        funktionen.remove(funktion);
    }
}
