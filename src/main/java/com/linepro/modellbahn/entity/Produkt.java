package com.linepro.modellbahn.entity;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Produkt. A product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PRODUKT)
@Table(name = DBNames.PRODUKT,
    indexes = {
        @Index(name = DBNames.PRODUKT + "_IX1", columnList = DBNames.HERSTELLER_ID),
        @Index(name = DBNames.PRODUKT + "_IX2", columnList = DBNames.EPOCH_ID),
        @Index(name = DBNames.PRODUKT + "_IX3", columnList = DBNames.GATTUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX4", columnList = DBNames.BAHNVERWALTUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX5", columnList = DBNames.ACHSFOLG_ID),
        @Index(name = DBNames.PRODUKT + "_IX6", columnList = DBNames.MASSSTAB_ID),
        @Index(name = DBNames.PRODUKT + "_IX7", columnList = DBNames.SPURWEITE_ID),
        @Index(name = DBNames.PRODUKT + "_IX8", columnList = DBNames.UNTER_KATEGORIE_ID),
        @Index(name = DBNames.PRODUKT + "_IX9", columnList = DBNames.SONDERMODEL_ID),
        @Index(name = DBNames.PRODUKT + "_IX10", columnList = DBNames.AUFBAU_ID),
        @Index(name = DBNames.PRODUKT + "_IX11", columnList = DBNames.LICHT_ID),
        @Index(name = DBNames.PRODUKT + "_IX12", columnList = DBNames.KUPPLUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX13", columnList = DBNames.VORBILD_ID),
        @Index(name = DBNames.PRODUKT + "_IX14", columnList = DBNames.STEUERUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX15", columnList = DBNames.DECODER_TYP_ID),
        @Index(name = DBNames.PRODUKT + "_IX16", columnList = DBNames.MOTOR_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PRODUKT + "_UC1", columnNames = { DBNames.HERSTELLER_ID, DBNames.BESTELL_NR })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Produkt extends ItemImpl {

    /** The hersteller. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hersteller.class)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk16"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.hersteller.notnull}")
    private Hersteller hersteller;

    @Column(name = DBNames.BESTELL_NR, length = 50)
    @Pattern(regexp = "^[A-Z0-9\\-.]+$", message = "{com.linepro.modellbahn.validator.constraints.bestellNr.invalid}")
    private String bestellNr;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The unter kategorie. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk7"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.unterKategorie.notnull}")
    private UnterKategorie unterKategorie;

    /** The massstab. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Massstab.class)
    @JoinColumn(name = DBNames.MASSSTAB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk5"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.massstab.notnull}")
    private Massstab massstab;

    /** The spurweite. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Spurweite.class)
    @JoinColumn(name = DBNames.SPURWEITE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk6"))
    private Spurweite spurweite;

    /** The bahnverwaltung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk3"))
    private Bahnverwaltung bahnverwaltung;

    /** The gattung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk2"))
    private Gattung gattung;

    /** The epoch. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Epoch.class)
    @JoinColumn(name = DBNames.EPOCH_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk1"))
    private Epoch epoch;

    /** The achsfolg. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk4"))
    private Achsfolg achsfolg;

    /** The Sondermodel. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SonderModel.class)
    @JoinColumn(name = DBNames.SONDERMODEL_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk8"))
    private SonderModel sonderModel;

    /** The aufbau. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Aufbau.class)
    @JoinColumn(name = DBNames.AUFBAU_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk9"))
    private Aufbau aufbau;

    /** The licht. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = DBNames.LICHT_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk10"))
    private Licht licht;

    /** The kupplung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = DBNames.KUPPLUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk11"))
    private Kupplung kupplung;

    /** The vorbild. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Vorbild.class)
    @JoinColumn(name = DBNames.VORBILD_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk12"))
    private Vorbild vorbild;

    /** The steuerung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = DBNames.STEUERUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk13"))
    private Steuerung steuerung;

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk14"))
    private DecoderTyp decoderTyp;

    /** The motor typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = DBNames.MOTOR_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk15"))
    private MotorTyp motorTyp;

    /** The anmerkung. */
    @Column(name = DBNames.ANMERKUNG, length = 100)
    private String anmerkung;

    /** The betreibsnummer. */
    @Column(name = DBNames.BETREIBSNUMMER, length = 100)
    private String betreibsnummer;

    /** The bauzeit. */
    @Column(name = DBNames.BAUZEIT)
    @Past(message = "{com.linepro.modellbahn.validator.constraints.bauzeit.past}")
    private LocalDate bauzeit;

    /** The anleitungen. */
    @Column(name = DBNames.ANLEITUNGEN, length = 512)
    @Convert(converter = PathConverter.class)
    private Path anleitungen;

    /** The explosionszeichnung. */
    @Column(name = DBNames.EXPLOSIONSZEICHNUNG, length = 512)
    @Convert(converter = PathConverter.class)
    private Path explosionszeichnung;

    /** The lange. */
    @Column(name = DBNames.LANGE, precision = 6, scale = 2)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.lange.positive}")
    private BigDecimal lange;

    /** The abbildung. */
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    private Path abbildung;

    /** The teilen. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.PRODUKT, targetEntity = ProduktTeil.class, orphanRemoval = true)
    private Set<ProduktTeil> teilen;
    
    public void addTeil(ProduktTeil teil) {
        teil.setProdukt(this);
        teil.setDeleted(false);
        if (teilen == null) { teilen = new HashSet<>(); }
        teilen.add(teil);
    }

    
    public void removeTeil(ProduktTeil teil) {
        teilen.remove(teil);
    }
}