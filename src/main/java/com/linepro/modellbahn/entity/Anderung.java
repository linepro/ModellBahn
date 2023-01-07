package com.linepro.modellbahn.entity;

import static com.linepro.modellbahn.util.ToStringBuilder.summary;

import java.io.Serializable;
import java.time.LocalDate;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Artikel.
 * An article.
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ANDERUNG)
@Table(name = DBNames.ANDERUNG,
    indexes = {
        @Index(name = DBNames.ANDERUNG + "_IX1", columnList = DBNames.ARTIKEL_ID),
        @Index(name = DBNames.ANDERUNG + "_IX2", columnList = DBNames.ANDERUNG_ID)
    },uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ANDERUNG + "_UC1", columnNames = {DBNames.ARTIKEL_ID, DBNames.ANDERUNG_ID})
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="anderung",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "artikel", subgraph = "anderung.artikel")
        }, subgraphs = {
            @NamedSubgraph(name = "anderung.artikel",
                attributeNodes = {
                    @NamedAttributeNode(value = "artikelId")
            })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Anderung extends ItemImpl implements Comparable<Anderung>, Serializable {

    private static final long serialVersionUID = 538555390577591608L;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class, optional = false)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ANDERUNG + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.artikel.notnull}")
    private Artikel artikel;

    @Column(name = DBNames.ANDERUNG_ID, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.anderungId.notnull}")
    @Min(value = 1, message = "{com.linepro.modellbahn.validator.constraints.anderungId.positive}")
    private Integer anderungId;

    @Column(name = DBNames.ANDERUNGSDATUM)
    @PastOrPresent(message = "{com.linepro.modellbahn.validator.constraints.anderungsDatum.past}")
    private LocalDate anderungsDatum;

    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.ANDERUNGS_TYP, nullable = false, length = 14)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.anderungsTyp.notnull}")
    private AnderungsTyp anderungsTyp;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    private String bezeichnung;

    @Column(name = DBNames.MENGE)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.menge.positive}")
    private Integer menge;

    @Column(name = DBNames.ANMERKUNG, length = 255)
    @Size(max = 255, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    private String anmerkung;

    @Override
    public int compareTo(Anderung other) {
        return new CompareToBuilder()
            .append(getAnderungId(), other.getAnderungId())
            .toComparison();
    }

    @Override
    public int hashCode() {
      return new HashCodeBuilder()
          .append(getArtikel().hashCode())
          .append(getAnderungId())
          .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Anderung)) {
        return false;
      }

      Anderung other = (Anderung) obj;

      return new EqualsBuilder()
          .append(getArtikel(), other.getArtikel())
          .append(getAnderungId(), other.getAnderungId())
          .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("artikel", summary(artikel))
            .append("anderungId", anderungId)
            .append("anderungsDatum", anderungsDatum)
            .append("anderungsTyp",  anderungsTyp)
            .append("bezeichnung",  bezeichnung)
            .append("menge", menge)
            .append("anmerkung",  anmerkung)
            .toString();
    }
}
