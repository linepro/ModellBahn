package com.linepro.modellbahn.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Currency;
import com.linepro.modellbahn.validation.Fahrstufe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Decoder.
 * Represents a decoder (with its settings).
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER)
@Table(name = DBNames.DECODER, 
    indexes = {
        @Index(name = DBNames.DECODER + "_IX1", columnList = DBNames.DECODER_TYP_ID),
        @Index(name = DBNames.DECODER + "_IX2", columnList = DBNames.PROTOKOLL_ID),
        @Index(name = DBNames.DECODER + "_IX3", columnList = DBNames.ARTIKEL_ID) 
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER + "_UC1", columnNames = { DBNames.DECODER_ID }) 
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="decoder.lookup",
        attributeNodes = {
            @NamedAttributeNode(value = "decoderId"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "decoderTyp", subgraph = "decoder.decoderTyp"),
            @NamedAttributeNode(value = "status")
        },
        subgraphs = {
            @NamedSubgraph(name = "decoder.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoder.hersteller"),
                    @NamedAttributeNode(value = "bestellNr")
            }),
            @NamedSubgraph(name = "decoder.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
        }),
    @NamedEntityGraph(name="decoder.noChildren",
        attributeNodes = {
            @NamedAttributeNode(value = "decoderId"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "decoderTyp", subgraph = "decoder.decoderTyp"),
            @NamedAttributeNode(value = "artikel", subgraph = "decoder.artikel"),
            @NamedAttributeNode(value = "protokoll", subgraph = "decoder.protokoll"),
            @NamedAttributeNode(value = "fahrstufe"),
            @NamedAttributeNode(value = "status"),
            @NamedAttributeNode(value = "deleted")
        },
        subgraphs = {
            @NamedSubgraph(name = "decoder.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoder.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "iMax"),
                    @NamedAttributeNode(value = "sound"),
                    @NamedAttributeNode(value = "konfiguration"),
                    @NamedAttributeNode(value = "stecker"),
                    @NamedAttributeNode(value = "anleitungen")
            }),
            @NamedSubgraph(name = "decoder.artikel",
                attributeNodes = {
                    @NamedAttributeNode(value = "artikelId"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "decoder.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "decoder.protokoll",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            })
        }),
    @NamedEntityGraph(name="decoder.withChildren",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "decoderTyp", subgraph = "decoder.decoderTyp"),
            @NamedAttributeNode(value = "artikel", subgraph = "decoder.artikel"),
            @NamedAttributeNode(value = "protokoll", subgraph = "decoder.protokoll"),
            @NamedAttributeNode(value = "cvs", subgraph = "decoder.cvs"),
            @NamedAttributeNode(value = "funktionen", subgraph = "decoder.funktionen")
        },
        subgraphs = {
            @NamedSubgraph(name = "decoder.decoderTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "hersteller", subgraph = "decoder.hersteller"),
                    @NamedAttributeNode(value = "bestellNr"),
                    @NamedAttributeNode(value = "iMax"),
                    @NamedAttributeNode(value = "sound"),
                    @NamedAttributeNode(value = "konfiguration"),
                    @NamedAttributeNode(value = "stecker"),
                    @NamedAttributeNode(value = "anleitungen")
            }),
            @NamedSubgraph(name = "decoder.artikel",
            attributeNodes = {
                @NamedAttributeNode(value = "artikelId"),
                @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "decoder.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "decoder.protokoll",
                attributeNodes = {
                    @NamedAttributeNode(value = "name"),
                    @NamedAttributeNode(value = "bezeichnung")
            }),
            @NamedSubgraph(name = "decoder.cvs",
                attributeNodes = {
                    @NamedAttributeNode("id"),
                    @NamedAttributeNode(value = "cv", subgraph = "decoder.cv"),
                    @NamedAttributeNode(value = "wert"),
                    @NamedAttributeNode("deleted")
                }),
            @NamedSubgraph(name = "decoder.funktionen",
                attributeNodes = {
                    @NamedAttributeNode("id"),
                    @NamedAttributeNode(value = "funktion", subgraph = "decoder.funktion"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode("deleted")
                }),
            @NamedSubgraph(name = "decoder.adressTyp",
                attributeNodes = {
                    @NamedAttributeNode(value = "position"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "adressTyp"),
                    @NamedAttributeNode(value = "span"),
                    @NamedAttributeNode(value = "adress")
                }),
            @NamedSubgraph(name = "decoder.cv",
                attributeNodes = {
                    @NamedAttributeNode(value = "cv"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "minimal"),
                    @NamedAttributeNode(value = "maximal"),
                    @NamedAttributeNode(value = "werkseinstellung"),
                }),
            @NamedSubgraph(name = "decoder.funktion",
                attributeNodes = {
                    @NamedAttributeNode(value = "funktion"),
                    @NamedAttributeNode(value = "bezeichnung"),
                    @NamedAttributeNode(value = "programmable")
                })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Decoder extends ItemImpl implements Comparable<Decoder> {

    @Column(name = DBNames.DECODER_ID, unique = true, length = 6, nullable = false, updatable = false)
    @Pattern(regexp = "^" + DBNames.ID_PATTERN + "$", message = "{com.linepro.modellbahn.validator.constraints.decoderId.invalid}")
    private String decoderId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class, optional = true)
    @JoinColumn(name = DBNames.ARTIKEL_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER + "_fk3"))
    private Artikel artikel;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The typ. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class, optional = false)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The protokoll. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class, optional = false)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER
                    + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notnull}")
    private Protokoll protokoll;

    /** The adress. */
    @Column(name = DBNames.ADRESS, nullable = false)
    private Integer adress;

    /** The fahrstufe. */
    @Column(name = DBNames.FAHRSTUFE)
    @Fahrstufe
    private Integer fahrstufe;

    /** The Kaufdatum. */
    @Column(name = DBNames.KAUFDATUM)
    @PastOrPresent(message = "{com.linepro.modellbahn.validator.constraints.kaufdatum.past}")
    private LocalDate kaufdatum;

    /** The wahrung. */
    @Column(name = DBNames.WAHRUNG, length = 3)
    @Currency(message = "{com.linepro.modellbahn.validator.constraints.wahrung.invalid}")
    private String wahrung;

    /** The preis. */
    @Column(name = DBNames.PREIS, precision = 6, scale = 2)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.preis.positive}")
    private BigDecimal preis;

    /** The anmerkung. */
    @Column(name = DBNames.ANMERKUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    private String anmerkung;

    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.STATUS, nullable = false, length = 11)
    private DecoderStatus status;

    /** The cvs. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderCv.class, orphanRemoval = true)
    @Builder.Default
    private Set<DecoderCv> cvs = new HashSet<>();

    /** The funktionen. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderFunktion.class, orphanRemoval = true)
    @Builder.Default
    private Set<DecoderFunktion> funktionen = new HashSet<>();

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;

        if (artikel == null) {
            if (status == null || DecoderStatus.INSTALIERT == status) {
                status = DecoderStatus.FREI;
            }
        } else {
            if (status == null || DecoderStatus.FREI == status) {
                status = DecoderStatus.INSTALIERT;
            }
        }
    }

    public void addCv(DecoderCv cv) {
        if (cvs == null) {
            cvs = new HashSet<>();
        }
        cv.setDecoder(this);
        cvs.add(cv);
    }

    public void removeCv(DecoderCv cv) {
        cvs.remove(cv);
    }

    public void addFunktion(DecoderFunktion funktion) {
        if (funktionen == null) {
            funktionen = new HashSet<>();
        }
        funktion.setDecoder(this);
        funktion.setDeleted(false);
        funktionen.add(funktion);
    }

    public void removeFunktion(DecoderFunktion funktion) {
        funktionen.remove(funktion);
    }

    @Override
    public int compareTo(Decoder other) {
        return new CompareToBuilder().append(getDecoderId(), other.getDecoderId()).toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getDecoderId()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Decoder)) {
            return false;
        }

        Decoder other = (Decoder) obj;

        return new EqualsBuilder().append(getDecoderId(), other.getDecoderId()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("decoderId", decoderId)
            .append("bezeichnung", bezeichnung)
            .append("decoderTyp", decoderTyp)
            .append("protokoll", protokoll)
            .append("fahrstufe", fahrstufe)
            .append("status", status)
            .append("cvs", cvs)
            .append("funktionen", funktionen)
            .toString();
    }
}
