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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Currency;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
        @Index(name = DBNames.ARTIKEL + "_IX6", columnList = DBNames.KUPPLUNG_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ARTIKEL + "_UC1", columnNames = { DBNames.ARTIKEL_ID }) 
    })
@NamedEntityGraphs({
    // Used for drop downs
    @NamedEntityGraph(name="artikel.lookup",
        attributeNodes = {
            @NamedAttributeNode(value = "artikelId"),
            @NamedAttributeNode(value = "produkt", subgraph = "artikel.produkt"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "status")
        },
        subgraphs = {
            @NamedSubgraph(name = "artikel.produkt",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "artikel.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "gattung", subgraph = "artikel.gattung"),
                    @NamedAttributeNode(value = "abbildung"),
                    @NamedAttributeNode(value = "grossansicht")
                }),
            @NamedSubgraph(name = "artikel.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
        }),
    // Used for summary page
    @NamedEntityGraph(name="artikel.noChildren",
        attributeNodes = {
            @NamedAttributeNode(value = "artikelId"),
            @NamedAttributeNode(value = "produkt", subgraph = "artikel.produkt"),
            @NamedAttributeNode(value = "kaufdatum"),
            @NamedAttributeNode(value = "wahrung"),
            @NamedAttributeNode(value = "steuerung", subgraph = "artikel.steuerung"),
            @NamedAttributeNode(value = "motorTyp", subgraph = "artikel.motorTyp"),
            @NamedAttributeNode(value = "licht", subgraph = "artikel.licht"),
            @NamedAttributeNode(value = "kupplung", subgraph = "artikel.kupplung"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "preis"),
            @NamedAttributeNode(value = "menge"),
            @NamedAttributeNode(value = "verbleibende"),
            @NamedAttributeNode(value = "anmerkung"),
            @NamedAttributeNode(value = "beladung"),
            @NamedAttributeNode(value = "abbildung"),
            @NamedAttributeNode(value = "grossansicht"),
            @NamedAttributeNode(value = "status"),
            @NamedAttributeNode(value = "deleted")
        },
        subgraphs = {
            @NamedSubgraph(name = "artikel.produkt",
                attributeNodes = {
                @NamedAttributeNode(value = "hersteller", subgraph = "artikel.hersteller"),
                @NamedAttributeNode(value = "bestellNr"),
                @NamedAttributeNode(value = "bezeichnung"),
                @NamedAttributeNode(value = "bahnverwaltung", subgraph = "artikel.bahnverwaltung"),
                @NamedAttributeNode(value = "unterKategorie", subgraph = "artikel.unterKategorie"),
                @NamedAttributeNode(value = "massstab", subgraph = "artikel.massstab"),
                @NamedAttributeNode(value = "spurweite", subgraph = "artikel.spurweite"),
                @NamedAttributeNode(value = "epoch", subgraph = "artikel.epoch"),
                @NamedAttributeNode(value = "gattung", subgraph = "artikel.gattung"),
                @NamedAttributeNode(value = "betreibsnummer"),
                @NamedAttributeNode(value = "achsfolg", subgraph = "artikel.achsfolg"),
                @NamedAttributeNode(value = "sondermodell", subgraph = "artikel.sondermodell"),
                @NamedAttributeNode(value = "aufbau", subgraph = "artikel.aufbau"),
                @NamedAttributeNode(value = "abbildung"),
                @NamedAttributeNode(value = "grossansicht")
            }),
            @NamedSubgraph(name = "artikel.steuerung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.motorTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.licht",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.kupplung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.unterKategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "arktikel.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.massstab",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.spurweite",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.epoch",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.achsfolg",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.sondermodell",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.aufbau",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                })
        }),
    // Used for detail page
    @NamedEntityGraph(name="artikel.withChildren",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "produkt", subgraph = "artikel.produkt"),
            @NamedAttributeNode(value = "steuerung", subgraph = "artikel.steuerung"),
            @NamedAttributeNode(value = "motorTyp", subgraph = "artikel.motorTyp"),
            @NamedAttributeNode(value = "licht", subgraph = "artikel.licht"),
            @NamedAttributeNode(value = "kupplung", subgraph = "artikel.kupplung"),
            @NamedAttributeNode(value = "decoders", subgraph = "artikel.decoders"),
            @NamedAttributeNode(value = "anderungen", subgraph = "artikel.anderungen")
        },
        subgraphs = {
            @NamedSubgraph(name = "artikel.produkt",
                attributeNodes = {
                @NamedAttributeNode(value = "hersteller", subgraph = "artikel.hersteller"),
                @NamedAttributeNode(value = "bestellNr"),
                @NamedAttributeNode(value = "bezeichnung"),
                @NamedAttributeNode(value = "bahnverwaltung", subgraph = "artikel.bahnverwaltung"),
                @NamedAttributeNode(value = "unterKategorie", subgraph = "artikel.unterKategorie"),
                @NamedAttributeNode(value = "massstab", subgraph = "artikel.massstab"),
                @NamedAttributeNode(value = "spurweite", subgraph = "artikel.spurweite"),
                @NamedAttributeNode(value = "epoch", subgraph = "artikel.epoch"),
                @NamedAttributeNode(value = "vorbild", subgraph = "artikel.vorbild"),
                @NamedAttributeNode(value = "gattung", subgraph = "artikel.gattung"),
                @NamedAttributeNode(value = "betreibsnummer"),
                @NamedAttributeNode(value = "achsfolg", subgraph = "artikel.achsfolg"),
                @NamedAttributeNode(value = "sondermodell", subgraph = "artikel.sondermodell"),
                @NamedAttributeNode(value = "aufbau", subgraph = "artikel.aufbau"),
                @NamedAttributeNode(value = "abbildung"),
                @NamedAttributeNode(value = "grossansicht")
            }),
            @NamedSubgraph(name = "artikel.steuerung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.motorTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.licht",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.kupplung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.decoders",
                attributeNodes = {
                    @NamedAttributeNode(value = "decoderId"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "decoderTyp", subgraph = "artikel.decoderTyp"),
                    @NamedAttributeNode(value = "protokoll", subgraph = "artikel.protokoll"),
                    @NamedAttributeNode(value = "fahrstufe"),
                    @NamedAttributeNode(value = "adress"),
                    @NamedAttributeNode(value = "status"),
                    @NamedAttributeNode(value = "funktionen", subgraph = "artikel.funktionen")
                }),
            @NamedSubgraph(name = "artikel.funktionen",
            attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode(value = "funktion", subgraph = "artikel.funktion"),
                @NamedAttributeNode(value = "bezeichnung"),
                @NamedAttributeNode("deleted")
            }),
            @NamedSubgraph(name = "artikel.funktion",
            attributeNodes = {
                @NamedAttributeNode(value = "funktion"),
                @NamedAttributeNode(value = "bezeichnung"),
                @NamedAttributeNode(value = "programmable")
            }),
            @NamedSubgraph(name = "artikel.hersteller",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.bahnverwaltung",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.unterKategorie",
            attributeNodes = {
                @NamedAttributeNode(value = "kategorie", subgraph = "arktikel.kategorie"),
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.kategorie",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.massstab",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.spurweite",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.epoch",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.vorbild",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.gattung",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.achsfolg",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.sondermodell",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.aufbau",
            attributeNodes = {
                @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "artikel.decoderHersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "adressTyp"),
                    @NamedAttributeNode(value = "konfiguration"),
                    @NamedAttributeNode(value = "anleitungen")
                }),
            @NamedSubgraph(name = "artikel.protokoll",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "artikel.decoderHersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "artikel.anderungen",
                 attributeNodes = {
                     @NamedAttributeNode(value = "anderungId"),
                     @NamedAttributeNode(value = "anderungsDatum"),
                     @NamedAttributeNode(value = "anderungsTyp"),
                     @NamedAttributeNode(value = "bezeichnung"),
                     @NamedAttributeNode(value = "menge"),
                     @NamedAttributeNode(value = "anmerkung"),
                     @NamedAttributeNode(value = "deleted")
                 })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Artikel extends ItemImpl implements Comparable<Artikel> {

    /** The abbildung. */
    @Column(name = DBNames.ARTIKEL_ID, length = 6, nullable = false, updatable = false)
    @Pattern(regexp = "^" + DBNames.ID_PATTERN + "$", message = "{com.linepro.modellbahn.validator.constraints.artikelId.invalid}")
    private String artikelId;

    /** The produkt. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class, optional = false)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The Kaufdatum. */
    @Column(name = DBNames.KAUFDATUM)
    @PastOrPresent(message = "{com.linepro.modellbahn.validator.constraints.kaufdatum.past}")
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

    /** The bezeichnung. */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The preis. */
    @Column(name = DBNames.PREIS, precision = 6, scale = 2)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.preis.positive}")
    private BigDecimal preis;

    /** The menge. */
    @Column(name = DBNames.MENGE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.menge.notnull}")
    @Min(value=1, message = "{com.linepro.modellbahn.validator.constraints.menge.positive}")
    private Integer menge;

    /** The menge. */
    @Column(name = DBNames.VERBLEIBENDE, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.verbleibende.notnull}")
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.verbleibende.positive}")
    private Integer verbleibende;

    /** The anmerkung. */
    @Column(name = DBNames.ANMERKUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    private String anmerkung;

    /** The beladung. */
    @Column(name = DBNames.BELADUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    private String beladung;

    /** The abbildung. */
    @Column(name = DBNames.ABBILDUNG, length = 512)
    @Convert(converter = PathConverter.class)
    private Path abbildung;

    /** The grossansicht. */
    @Column(name = DBNames.GROSSANSICHT, length = 512)
    @Convert(converter = PathConverter.class)
    private Path grossansicht;

    /** The status. */
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.STATUS, nullable = false, length = 12)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.status.notnull}")
    private Status status;

    /** The anderung. */
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.ARTIKEL, targetEntity=Anderung.class, orphanRemoval = true)
    @Builder.Default
    private Set<Anderung> anderungen = new HashSet<>();

    public void addAnderung(Anderung anderung) {
        if (anderungen == null) {
            anderungen = new HashSet<>();
        }

        int anderungId = anderungen.stream()
                                   .mapToInt(a -> a.getAnderungId())
                                   .max()
                                   .orElse(0);

        anderung.setArtikel(this);
        anderung.setAnderungId(anderungId+1);
        anderung.setDeleted(false);
        anderungen.add(anderung);
    }

    public void removeAnderung(Anderung anderung) {
        anderungen.remove(anderung);
    }

    /** The decoders. */
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.ARTIKEL, targetEntity=Decoder.class, orphanRemoval = false)
    @Builder.Default
    private Set<Decoder> decoders = new HashSet<>();

    public void addDecoder(Decoder decoder) {
        if (decoders == null) {
            decoders = new HashSet<>();
        }

        decoder.setArtikel(this);
        decoder.setDeleted(false);
        decoders.add(decoder);
    }

    public void removeDecoder(Decoder decoder) {
        decoders.remove(decoder);
        decoder.setArtikel(null);
    }

    @Override
    public int compareTo(Artikel other) {
        return new CompareToBuilder()
            .append(getArtikelId(), other.getArtikelId())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getArtikelId())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Artikel)) {
        return false;
      }

      Artikel other = (Artikel) obj;

      return new EqualsBuilder()
          .append(getArtikelId(), other.getArtikelId())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("artikelId", artikelId)
            .append("produkt", produkt)
            .append("kaufdatum", kaufdatum)
            .append("wahrung", wahrung)
            .append("steuerung", steuerung)
            .append("motorTyp", motorTyp)
            .append("licht", licht)
            .append("kupplung", kupplung)
            .append("bezeichnung", bezeichnung)
            .append("preis", preis)
            .append("menge", menge)
            .append("verbleibende", verbleibende)
            .append("anmerkung", anmerkung)
            .append("beladung", beladung)
            .append("abbildung", abbildung)
            .append("status", status)
            .append("anderungen", anderungen)
            .append("decoders", decoders)
            .toString();
    }
}
