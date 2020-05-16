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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.ArtikelId;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.validation.Currency;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Artikel.
 * An article. 
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ARTIKEL)
@Table(name = DBNames.ARTIKEL,
    indexes = { 
        @Index(name = DBNames.ARTIKEL + "_IX1", columnList = DBNames.PRODUKT_ID),
        @Index(name = DBNames.ARTIKEL + "_IX3", columnList = DBNames.STEUERUNG_ID),
        @Index(name = DBNames.ARTIKEL + "_IX4", columnList = DBNames.MOTOR_TYP_ID),
        @Index(name = DBNames.ARTIKEL + "_IX5", columnList = DBNames.LICHT_ID),
        @Index(name = DBNames.ARTIKEL + "_IX6", columnList = DBNames.KUPPLUNG_ID),
        @Index(name = DBNames.ARTIKEL + "_IX7", columnList = DBNames.DECODER_ID) 
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ARTIKEL + "_UC1", columnNames = { DBNames.ARTIKEL_ID }) 
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Artikel extends ItemImpl {

    /** The abbildung. */
    @ArtikelId
    @Column(name = DBNames.ARTIKEL_ID, length = 6, nullable = false, updatable = false)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "{com.linepro.modellbahn.validator.constraints.artikelId.invalid}")
    private String artikelId;

    /** The produkt. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The Kaufdatum. */
    @Column(name = DBNames.KAUFDATUM)
    @Past(message = "{com.linepro.modellbahn.validator.constraints.kaufdatum.past}")
    private LocalDate kaufdatum;

    /** The wahrung. */
    @Column(name = DBNames.WAHRUNG, length = 3)
    @Currency(message = "{com.linepro.modellbahn.validator.constraints.wahrung.invalid}")
    private String wahrung;

    /** The steuerung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = DBNames.STEUERUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk3"))
    private Steuerung steuerung;

    /** The motor typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = DBNames.MOTOR_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk4"))
    private MotorTyp motorTyp;

    /** The licht. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = DBNames.LICHT_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk5"))
    private Licht licht;

    /** The kupplung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = DBNames.KUPPLUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk6"))
    private Kupplung kupplung;

    /** The decoder. */
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk7"))
    private Decoder decoder;

    /** The bezeichnung. */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The preis. */
    @Column(name = DBNames.PREIS, precision = 6, scale = 2)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.preis.positive}")
    private BigDecimal preis;

    /** The stuck. */
    @Column(name = DBNames.STUCK, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.stuck.notnull}")
    @Min(value=1, message = "{com.linepro.modellbahn.validator.constraints.stuck.positive}")
    private Integer stuck;

    /** The stuck. */
    @Column(name = DBNames.VERBLEIBENDE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.verbleibende.notnull}")
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.verbleibende.positive}")
    private Integer verbleibende;

    /** The anmerkung. */
    @Column(name = DBNames.ANMERKUNG, length = 100)
    private String anmerkung;

    /** The beladung. */
    @Column(name = DBNames.BELADUNG, length = 100)
    private String beladung;

    /** The abbildung. */
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    private Path abbildung;

    /** The status. */
    @Column(name = DBNames.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.status.notnull}")
    private Status status;

    /** The anderung. */
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = DBNames.ARTIKEL, targetEntity=Anderung.class, orphanRemoval = true)
    private Set<Anderung> anderungen;

    public void addAnderung(Anderung anderung) {
        anderung.setArtikel(this);
        anderung.setAnderungId(anderungen.stream()
                                         .mapToInt(a -> a.getAnderungId())
                                         .max()
                                         .orElse(1));
        anderung.setDeleted(false);
        if (anderungen == null) { anderungen = new HashSet<>(); }
        anderungen.add(anderung);
    }
    
    public void removeAnderung(Anderung anderung) {
        anderungen.remove(anderung);
    }
}
