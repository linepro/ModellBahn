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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;

import lombok.Builder;
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
        @Index(name = DBNames.PRODUKT + "_IX9", columnList = DBNames.SONDERMODELL_ID),
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
@NamedEntityGraphs({
    @NamedEntityGraph(name="produkt.lookup",
        attributeNodes = {
            @NamedAttributeNode(value = "hersteller", subgraph = "produkt.hersteller"),
            @NamedAttributeNode(value = "bestellNr"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "unterKategorie", subgraph = "produkt.unterKategorie"),
            @NamedAttributeNode(value = "abbildung")
        },
        subgraphs = {
            @NamedSubgraph(name = "produkt.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.unterkategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "produkt.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
        }),
    @NamedEntityGraph(name="produkt.noChildren",
        attributeNodes = {
            @NamedAttributeNode(value = "hersteller", subgraph = "produkt.hersteller"),
            @NamedAttributeNode(value = "bestellNr"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "unterKategorie", subgraph = "produkt.unterKategorie"),
            @NamedAttributeNode(value = "massstab", subgraph = "produkt.massstab"),
            @NamedAttributeNode(value = "spurweite", subgraph = "produkt.spurweite"),
            @NamedAttributeNode(value = "bahnverwaltung", subgraph = "produkt.bahnverwaltung"),
            @NamedAttributeNode(value = "gattung", subgraph = "produkt.gattung"),
            @NamedAttributeNode(value = "epoch", subgraph = "produkt.epoch"),
            @NamedAttributeNode(value = "achsfolg", subgraph = "produkt.achsfolg"),
            @NamedAttributeNode(value = "sondermodell", subgraph = "produkt.sondermodell"),
            @NamedAttributeNode(value = "aufbau", subgraph = "produkt.aufbau"),
            @NamedAttributeNode(value = "licht", subgraph = "produkt.licht"),
            @NamedAttributeNode(value = "kupplung", subgraph = "produkt.kupplung"),
            @NamedAttributeNode(value = "vorbild", subgraph = "produkt.vorbild"),
            @NamedAttributeNode(value = "steuerung", subgraph = "produkt.steuerung"),
            @NamedAttributeNode(value = "decoderTyp", subgraph = "produkt.decoderTyp"),
            @NamedAttributeNode(value = "motorTyp", subgraph = "produkt.motorTyp"),
            @NamedAttributeNode(value = "anmerkung"),
            @NamedAttributeNode(value = "betreibsnummer"),
            @NamedAttributeNode(value = "bauzeit"),
            @NamedAttributeNode(value = "anleitungen"),
            @NamedAttributeNode(value = "explosionszeichnung"),
            @NamedAttributeNode(value = "lange"),
            @NamedAttributeNode(value = "abbildung"),
            @NamedAttributeNode(value = "deleted")
        },
        subgraphs = {
            @NamedSubgraph(name = "produkt.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.unterkategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "produkt.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.massstab",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.spurweite",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.epoch",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.achsfolg",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.sondermodell",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.aufbau",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.licht",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.kupplung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.vorbild",
                attributeNodes = {
                    @NamedAttributeNode(value = "gattung")
                }),
            @NamedSubgraph(name = "produkt.steuerung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "produkt.decoderHersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung")
                }),
            @NamedSubgraph(name = "produkt.decoderHersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.motorTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                })
        }),
    @NamedEntityGraph(name="produkt.withChildren",
        includeAllAttributes = true, 
        attributeNodes = {
            @NamedAttributeNode(value = "hersteller", subgraph = "produkt.hersteller"),
            @NamedAttributeNode(value = "unterKategorie", subgraph = "produkt.unterkategorie"),
            @NamedAttributeNode(value = "massstab", subgraph = "produkt.massstab"),
            @NamedAttributeNode(value = "spurweite", subgraph = "produkt.spurweite"),
            @NamedAttributeNode(value = "bahnverwaltung", subgraph = "produkt.bahnverwaltung"),
            @NamedAttributeNode(value = "gattung", subgraph = "produkt.gattung"),
            @NamedAttributeNode(value = "epoch", subgraph = "produkt.epoch"),
            @NamedAttributeNode(value = "achsfolg", subgraph = "produkt.achsfolg"),
            @NamedAttributeNode(value = "sondermodell", subgraph = "produkt.sondermodell"),
            @NamedAttributeNode(value = "aufbau", subgraph = "produkt.aufbau"),
            @NamedAttributeNode(value = "licht", subgraph = "produkt.licht"),
            @NamedAttributeNode(value = "kupplung", subgraph = "produkt.kupplung"),
            @NamedAttributeNode(value = "vorbild", subgraph = "produkt.vorbild"),
            @NamedAttributeNode(value = "steuerung", subgraph = "produkt.steuerung"),
            @NamedAttributeNode(value = "decoderTyp", subgraph = "produkt.decoderTyp"),
            @NamedAttributeNode(value = "motorTyp", subgraph = "produkt.motorTyp"),
            @NamedAttributeNode(value = "teilen", subgraph = "produkt.teilen")
        },
        subgraphs = {
            @NamedSubgraph(name = "produkt.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.unterkategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "produkt.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.massstab",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.spurweite",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.epoch",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.achsfolg",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.sondermodell",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.aufbau",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.licht",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.kupplung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.vorbild",
                attributeNodes = {
                    @NamedAttributeNode(value = "gattung")
                }),
            @NamedSubgraph(name = "produkt.steuerung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "produkt.decoderHersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung")
                }),
            @NamedSubgraph(name = "produkt.decoderHersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.motorTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.teilen", 
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "teil", subgraph = "produkt.teil"),
                    @NamedAttributeNode(value = "anzahl"),
                    @NamedAttributeNode(value = "deleted")
                }),
            @NamedSubgraph(name = "produkt.teil", 
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "produkt.teilHersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "unterKategorie", subgraph = "produkt.teilUnterkategorie"),
                }),
            @NamedSubgraph(name = "produkt.teilHersteller", 
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
                }),
            @NamedSubgraph(name = "produkt.teilUnterkategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "produkt.teilKategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "produkt.teilKategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Produkt extends ItemImpl implements Comparable<Produkt> {

    /** The hersteller. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class)
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
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk7"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.unterKategorie.notnull}")
    private UnterKategorie unterKategorie;

    /** The massstab. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Massstab.class)
    @JoinColumn(name = DBNames.MASSSTAB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk5"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.massstab.notnull}")
    private Massstab massstab;

    /** The spurweite. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Spurweite.class, optional = true)
    @JoinColumn(name = DBNames.SPURWEITE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk6"))
    private Spurweite spurweite;

    /** The bahnverwaltung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class, optional = true)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk3"))
    private Bahnverwaltung bahnverwaltung;

    /** The gattung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class, optional = true)
    @JoinColumn(name = DBNames.GATTUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk2"))
    private Gattung gattung;

    /** The epoch. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Epoch.class, optional = true)
    @JoinColumn(name = DBNames.EPOCH_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk1"))
    private Epoch epoch;

    /** The achsfolg. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class, optional = true)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk4"))
    private Achsfolg achsfolg;

    /** The Sondermodell. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Sondermodell.class, optional = true)
    @JoinColumn(name = DBNames.SONDERMODELL_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk8"))
    private Sondermodell sondermodell;

    /** The aufbau. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Aufbau.class, optional = true)
    @JoinColumn(name = DBNames.AUFBAU_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk9"))
    private Aufbau aufbau;

    /** The licht. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class, optional = true)
    @JoinColumn(name = DBNames.LICHT_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk10"))
    private Licht licht;

    /** The kupplung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class, optional = true)
    @JoinColumn(name = DBNames.KUPPLUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk11"))
    private Kupplung kupplung;

    /** The vorbild. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Vorbild.class, optional = true)
    @JoinColumn(name = DBNames.VORBILD_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk12"))
    private Vorbild vorbild;

    /** The steuerung. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class, optional = true)
    @JoinColumn(name = DBNames.STEUERUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk13"))
    private Steuerung steuerung;

    /** The decoder typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class, optional = true)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk14"))
    private DecoderTyp decoderTyp;

    /** The motor typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class, optional = true)
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
    @Builder.Default
    private Set<ProduktTeil> teilen = new HashSet<>();
    
    public void addTeil(ProduktTeil teil) {
        if (teilen == null) { 
            teilen = new HashSet<>(); 
        }
        teil.setProdukt(this);
        teil.setDeleted(false);
        teilen.add(teil);
    }

    
    public void removeTeil(ProduktTeil teil) {
        teilen.remove(teil);
    }

    @Override
    public int compareTo(Produkt other) {
        return new CompareToBuilder()
            .append(getHersteller().getName(), other.getHersteller().getName())
            .append(getBestellNr(), other.getBestellNr())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getHersteller().hashCode())
          .append(getBestellNr())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Produkt)) {
        return false;
      }

      Produkt other = (Produkt) obj;

      return new EqualsBuilder()
          .append(getHersteller(), other.getHersteller())
          .append(getBestellNr(), other.getBestellNr())
          .isEquals();
    }
}