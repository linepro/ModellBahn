package com.linepro.modellbahn.entity;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Vorbild.
 * The prototype for a product
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.VORBILD)
@Table(name = DBNames.VORBILD,
    indexes = { 
        @Index(name = DBNames.VORBILD + "_IX1", columnList = DBNames.UNTER_KATEGORIE_ID),
        @Index(name = DBNames.VORBILD + "_IX2", columnList = DBNames.ANTRIEB_ID),
        @Index(name = DBNames.VORBILD + "_IX3", columnList = DBNames.ACHSFOLG_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.VORBILD + "_UC1", columnNames = { DBNames.GATTUNG_ID })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="vorbild.lookup",
        attributeNodes = {
            @NamedAttributeNode(value = "gattung", subgraph = "vorbild.gattung"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "unterKategorie", subgraph = "vorbild.unterKategorie"),
            @NamedAttributeNode(value = "bahnverwaltung", subgraph = "vorbild.bahnverwaltung"),
            @NamedAttributeNode(value = "betreibsNummer"),
            @NamedAttributeNode(value = "abbildung")
        },
        subgraphs = {
            @NamedSubgraph(name = "vorbild.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.unterKategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "vorbild.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            }),
    @NamedEntityGraph(name="vorbild.summary",
        attributeNodes = {
            @NamedAttributeNode(value = "gattung", subgraph = "vorbild.gattung"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "unterKategorie", subgraph = "vorbild.unterKategorie"),
            @NamedAttributeNode(value = "bahnverwaltung", subgraph = "vorbild.bahnverwaltung"),
            @NamedAttributeNode(value = "bauzeit"),
            @NamedAttributeNode(value = "betreibsNummer"),
            @NamedAttributeNode(value = "antrieb", subgraph = "vorbild.antrieb"),
            @NamedAttributeNode(value = "achsfolg", subgraph = "vorbild.achsfolg"),
            @NamedAttributeNode(value = "ausserdienst"),
            @NamedAttributeNode(value = "abbildung"),
            @NamedAttributeNode(value = "deleted")
        },
        subgraphs = {
            @NamedSubgraph(name = "vorbild.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.unterKategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "vorbild.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.antrieb",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.achsfolg",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                })
        }),
    @NamedEntityGraph(name="vorbild.detail",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "gattung", subgraph = "vorbild.gattung"),
            @NamedAttributeNode(value = "unterKategorie", subgraph = "vorbild.unterKategorie"),
            @NamedAttributeNode(value = "bahnverwaltung", subgraph = "vorbild.bahnverwaltung"),
            @NamedAttributeNode(value = "antrieb", subgraph = "vorbild.antrieb"),
            @NamedAttributeNode(value = "achsfolg", subgraph = "vorbild.achsfolg")
        },
        subgraphs = {
            @NamedSubgraph(name = "vorbild.gattung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.unterKategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "kategorie", subgraph = "vorbild.kategorie"),
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "vorbild.bahnverwaltung",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.antrieb",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
                }),
            @NamedSubgraph(name = "vorbild.achsfolg",
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
@Unique
public class Vorbild extends ItemImpl implements Comparable<Vorbild> {

    /** The gattung. */
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.gattung.notnull}")
    private Gattung gattung;
    
    /** The bezeichnung. */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    private String bezeichnung;

    /** The unter kategorie. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.unterKategorie.notnull}")
    private UnterKategorie unterKategorie;

    /** The bahnverwaltung */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class, optional = true)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk3"))
    private Bahnverwaltung bahnverwaltung;
    
    /** The hersteller. */
    @Column(name = DBNames.HERSTELLER, length = 100)
    private String hersteller;

    /** The bauzeit. */
    @Column(name = DBNames.BAUZEIT)
    @Past(message = "{com.linepro.modellbahn.validator.constraints.kaufdatum.past}")
    private LocalDate bauzeit;

    /** The anzahl. */
    @Column(name = DBNames.ANZAHL)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anzahl.positive}")
    private Integer anzahl;

    /** The betreibs nummer. */
    @Column(name = DBNames.BETREIBSNUMMER, length = 100)
    private String betreibsNummer;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Antrieb.class, optional = true)
    @JoinColumn(name = DBNames.ANTRIEB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk4"))
    private Antrieb antrieb;

    /** The achsfolg. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class, optional = true)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk5"))
    private Achsfolg achsfolg;

    /** The anfahrzugkraft. */
    @Column(name = DBNames.ANFAHRZUGKRAFT)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anfahrzugkraft.positive}")
    private BigDecimal anfahrzugkraft;

    /** The leistung. */
    @Column(name = DBNames.LEISTUNG)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.leistung.positive}")
    private BigDecimal leistung;

    /** The dienstgewicht. */
    @Column(name = DBNames.DIENSTGEWICHT)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dienstgewicht.positive}")
    private BigDecimal dienstgewicht;

    /** The geschwindigkeit. */
    @Column(name = DBNames.GESCHWINDIGKEIT)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.geschwindigkeit.positive}")
    private Integer geschwindigkeit;

    /** The lange. */
    @Column(name = DBNames.LANGE)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.lange.positive}")
    private BigDecimal lange;

    /** The ausserdienst. */
    @Column(name = DBNames.AUSSERDIENST)
    @Past(message = "{com.linepro.modellbahn.validator.constraints.ausserdienst.past}")
    private LocalDate ausserdienst;

    /** The dm treibrad. */
    @Column(name = DBNames.DMTREIBRAD)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmTreibrad.positive}")
    private BigDecimal dmTreibrad;

    /** The dm laufrad vorn. */
    @Column(name = DBNames.DMLAUFRADVORN)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmLaufradVorn.positive}")
    private BigDecimal dmLaufradVorn;

    /** The dm laufrad hinten. */
    @Column(name = DBNames.DMLAUFRADHINTEN)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmLaufradHinten.positive}")
    private BigDecimal dmLaufradHinten;

    /** The zylinder. */
    @Column(name = DBNames.ZYLINDER)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.zylinder.positive}")
    private Integer zylinder;

    /** The dm zylinder. */
    @Column(name = DBNames.DMZYLINDER)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmZylinder.positive}")
    private BigDecimal dmZylinder;

    /** The kolbenhub. */
    @Column(name = DBNames.KOLBENHUB)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.kolbenhub.positive}")
    private BigDecimal kolbenhub;

    /** The kesseluberdruck. */
    @Column(name = DBNames.KESSELUBERDRUCK)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.kesseluberdruck.positive}")
    private BigDecimal kesseluberdruck;

    /** The rostflache. */
    @Column(name = DBNames.ROSTFLACHE)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.rostflache.positive}")
    private BigDecimal rostflache;

    /** The uberhitzerflache. */
    @Column(name = DBNames.UBERHITZERFLACHE)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.uberhitzerflache.positive}")
    private BigDecimal uberhitzerflache;

    /** The wasservorrat. */
    @Column(name = DBNames.WASSERVORRAT)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.wasservorrat.positive}")
    private BigDecimal wasservorrat;

    /** The verdampfung. */
    @Column(name = DBNames.VERDAMPFUNG)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.verdampfung.positive}")
    private BigDecimal verdampfung;

    /** The fahrmotoren. */
    @Column(name = DBNames.FAHRMOTOREN)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.fahrmotoren.positive}")
    private Integer fahrmotoren;

    /** The motorbauart. */
    @Column(name = DBNames.MOTORBAUART, length = 100)
    private String motorbauart;

    /** The leistungsubertragung. */
    @Column(name = DBNames.LEISTUNGSUBERTRAGUNG)
    @Enumerated(EnumType.STRING)
    private LeistungsUbertragung leistungsUbertragung;

    /** The reichweite. */
    @Column(name = DBNames.REICHWEITE)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.reichweite.positive}")
    private BigDecimal reichweite;

    /** The kapazitaet. */
    @Column(name = DBNames.KAPAZITAT)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.kapazitat.positive}")
    private BigDecimal kapazitat;

    /** The klasse. */
    @Column(name = DBNames.KLASSE)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.klasse.positive}")
    private Integer klasse;

    /** The sitzplatze KL 1. */
    @Column(name = DBNames.SITZPLATZEKL1)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL1.positive}")
    private Integer sitzplatzeKL1;

    /** The sitzplatze KL 2. */
    @Column(name = DBNames.SITZPLATZEKL2)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL2.positive}")
    private Integer sitzplatzeKL2;

    /** The sitzplatze KL 3. */
    @Column(name = DBNames.SITZPLATZEKL3)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL3.positive}")
    private Integer sitzplatzeKL3;

    /** The sitzplatze KL 4. */
    @Column(name = DBNames.SITZPLATZEKL4)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL4.positive}")
    private Integer sitzplatzeKL4;

    /** The aufbauten. */
    @Column(name = DBNames.AUFBAU, length = 100)
    private String aufbau;

    /** The triebkopf. */
    @Column(name = DBNames.TRIEBKOPF)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.triebkopf.positive}")
    private Integer triebkopf;

    /** The mittelwagen. */
    @Column(name = DBNames.MITTELWAGEN)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.mittelwagen.positive}")
    private Integer mittelwagen;

    /** The drehgestell bauart. */
    @Column(name = DBNames.DREHGESTELLBAUART, length = 100)
    private String drehgestellBauart;

    /** The abbildung. */
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    private Path abbildung;

    @Override
    public int compareTo(Vorbild other) {
        return new CompareToBuilder()
            .append(getGattung().getName(), other.getGattung().getName())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getGattung().hashCode())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Vorbild)) {
        return false;
      }

      Vorbild other = (Vorbild) obj;

      return new EqualsBuilder()
          .append(getGattung(), other.getGattung())
          .isEquals();
    }
}