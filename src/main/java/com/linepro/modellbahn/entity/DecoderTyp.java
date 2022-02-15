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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.WithAdress;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Adress;
import com.linepro.modellbahn.validation.Fahrstufe;
import com.linepro.modellbahn.validation.Unique;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@NamedEntityGraphs({
    @NamedEntityGraph(name="decoderTyp.lookup",
        attributeNodes = {
            @NamedAttributeNode(value = "hersteller", subgraph = "decoderTyp.hersteller"),
            @NamedAttributeNode(value = "bestellNr"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "sound"),
            @NamedAttributeNode(value = "stecker")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderTyp.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
        }),
    @NamedEntityGraph(name="decoderTyp.noChildren",
        attributeNodes = {
            @NamedAttributeNode(value = "hersteller", subgraph = "decoderTyp.hersteller"),
            @NamedAttributeNode(value = "bestellNr"),
            @NamedAttributeNode(value = "bezeichnung"),
            @NamedAttributeNode(value = "iMax"),
            @NamedAttributeNode(value = "protokoll", subgraph = "decoderTyp.protokoll"),
            @NamedAttributeNode(value = "fahrstufe"),
            @NamedAttributeNode(value = "sound"),
            @NamedAttributeNode(value = "konfiguration"),
            @NamedAttributeNode(value = "stecker"),
            @NamedAttributeNode(value = "anleitungen"),
            @NamedAttributeNode(value = "deleted")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderTyp.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            }),
            @NamedSubgraph(name = "decoderTyp.protokoll",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
            })
        }),
    @NamedEntityGraph(name="decoderTyp.withChildren",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "hersteller", subgraph = "decoderTyp.hersteller"),
            @NamedAttributeNode(value = "protokoll", subgraph = "decoderTyp.protokoll"),
            @NamedAttributeNode(value = "cvs", subgraph = "decoderTyp.cvs"),
            @NamedAttributeNode(value = "funktionen", subgraph = "decoderTyp.funktionen")
        }, subgraphs = {
            @NamedSubgraph(name = "decoderTyp.hersteller",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
             }),
             @NamedSubgraph(name = "decoderTyp.protokoll",
                attributeNodes = {
                    @NamedAttributeNode(value = "name")
             }),
             @NamedSubgraph(name = "decoderTyp.cvs",
                 attributeNodes = {
                     @NamedAttributeNode(value = "cv"),
                     @NamedAttributeNode(value = "bezeichnung"),
                     @NamedAttributeNode(value = "minimal"),
                     @NamedAttributeNode(value = "maximal"),
                     @NamedAttributeNode(value = "werkseinstellung"),
                     @NamedAttributeNode(value = "deleted")
             }),
             @NamedSubgraph(name = "decoderTyp.funktionen",
                 attributeNodes = {
                     @NamedAttributeNode(value = "funktion"),
                     @NamedAttributeNode(value = "bezeichnung"),
                     @NamedAttributeNode(value = "programmable"),
                     @NamedAttributeNode(value = "deleted")
             })
        })
    })
//@formatter:on
@Adress
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.decodertyp.notunique}")
public class DecoderTyp extends ItemImpl implements Comparable<DecoderTyp>, WithAdress {

    /** The hersteller. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hersteller.class, optional = false)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.hersteller.notnull}")
    private Hersteller hersteller;

    @Column(name = DBNames.BESTELL_NR, length = 50)
    @Size(max = 50, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    @Pattern(regexp = "^" + DBNames.BESTELL_NR_PATTERN + "$", message = "{com.linepro.modellbahn.validator.constraints.bestellNr.invalid}")
    private String bestellNr;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The i max. */
    @Column(name = DBNames.I_MAX, precision = 6, scale = 2)
    @Range(max = 10, min = 0, message = "{com.linepro.modellbahn.validator.constraints.imax.range}")
    private BigDecimal iMax;

    /** The protokoll. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class, optional = false)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk3"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notnull}")
    private Protokoll protokoll;

    /** The adressTyp. */
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.ADRESS_TYP, nullable = false, length = 14)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private AdressTyp adressTyp;

    /** The span. */
    @Column(name = DBNames.SPAN)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.span.notnull}")
    @Range(min=1, max=32, message = "{com.linepro.modellbahn.validator.constraints.span.range}")
    private Integer span;

    /** The adress. */
    @Column(name = DBNames.ADRESS)
    private Integer adress;

    /** The fahrstufe. */
    @Column(name = DBNames.FAHRSTUFE)
    @Fahrstufe
    private Integer fahrstufe;

    /** The sound. */
    @Column(name = DBNames.SOUND, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.sound.notnull}")
    private Boolean sound;

    /** The sound. */
    @Column(name = DBNames.MOTOR, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.motor.notnull}")
    private Boolean motor;

    /** The sound. */
    @Column(name = DBNames.OUTPUTS, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.output.notnull}")
    private Integer outputs;

    /** The sound. */
    @Column(name = DBNames.SERVOS, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.servo.notnull}")
    private Integer servos;

    /** The konfiguration. */
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.KONFIGURATION, nullable = false, length = 15)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.konfiguration.notnull}")
    private Konfiguration konfiguration;

    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.STECKER, nullable = false, length = 10)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.stecker.notnull}")
    private Stecker stecker;

    /** The anleitungen. */
    @Column(name = DBNames.ANLEITUNGEN, length = 512)
    @Convert(converter = PathConverter.class)
    private Path anleitungen;

    /** The cvs. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decoderTyp", targetEntity = DecoderTypCv.class, orphanRemoval = true)
    @Builder.Default
    private Set<DecoderTypCv> cvs = new HashSet<>();

    /** The funktion. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decoderTyp", targetEntity = DecoderTypFunktion.class, orphanRemoval = true)
    @Builder.Default
    private Set<DecoderTypFunktion> funktionen = new HashSet<>();

    public void addCv(DecoderTypCv cv) {
        if (cvs == null) {
            cvs = new HashSet<>();
        }
        cv.setDecoderTyp(this);
        cv.setDeleted(false);
        cvs.add(cv);
    }

    public void removeCv(DecoderTypCv cv) {
        cvs.remove(cv);
    }

    public void addFunktion(DecoderTypFunktion funktion) {
        if (funktionen == null) {
            funktionen = new HashSet<>();
        }
        funktion.setDecoderTyp(this);
        funktion.setDeleted(false);
        funktionen.add(funktion);
    }

    public void removeFunktion(DecoderTypFunktion funktion) {
        funktionen.remove(funktion);
    }

    @Override
    public int compareTo(DecoderTyp other) {
        return new CompareToBuilder()
            .append(getHersteller().getName(), other.getHersteller().getName())
            .append(getBestellNr(), other.getBestellNr())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getBestellNr())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof DecoderTyp)) {
        return false;
      }

      DecoderTyp other = (DecoderTyp) obj;

      return new EqualsBuilder()
          .append(getHersteller(), other.getHersteller())
          .append(getBestellNr(), other.getBestellNr())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("hersteller", hersteller)
            .append("bestellNr", bestellNr)
            .append("bezeichnung",  bezeichnung)
            .append("iMax",  iMax)
            .append("protokoll",  protokoll)
            .append("fahrstufe",  fahrstufe)
            .append("sound",  sound)
            .append("konfiguration",  konfiguration)
            .append("stecker",  stecker)
            .append("anleitungen",  anleitungen)
            .append("cvs", cvs)
            .append("funktionen", funktionen)
            .toString();
    }
}
