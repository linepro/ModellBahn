package com.linepro.modellbahn.entity;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.entity.base.ItemImpl;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Artikel.
 * An article.
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.ANDERUNG)
@Table(name = DBNames.ANDERUNG, indexes = {@Index(columnList = DBNames.ARTIKEL_ID),
        @Index(columnList = DBNames.ANDERUNG_ID)}, uniqueConstraints = {
                @UniqueConstraint(columnNames = {DBNames.ARTIKEL_ID, DBNames.ANDERUNG_ID})})
public class Anderung extends ItemImpl {

    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.artikel.notnull}")
    private Artikel artikel;

    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.anderungId.notnull}")
    @Min(value = 1, message = "{com.linepro.modellbahn.validator.constraints.anderungId.positive}")
    private Integer anderungId;

    @Past(message = "{com.linepro.modellbahn.validator.constraints.anderungsDatum.past}")
    private LocalDate anderungsDatum;

    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.anderungsTyp.notnull}")
    private AnderungsTyp anderungsTyp;

    private String bezeichnung;

    @Positive(message = "{com.linepro.modellbahn.validator.constraints.stuck.positive}")
    private Integer stuck;

    private String anmerkung;

    /**
     * Instantiates a new artikel.
     */
    public Anderung() {
        super();
    }

    /**
     * Instantiates a new artikel.
     * @param id the id
     * @param artikel the parent artikel
     * @param anderungId the modification sequence
     * @param anderungsDatum the modification date
     * @param anderungsTyp the modification type
     * @param bezeichnung description of the modificiation
     * @param stuck number of items affected by the modification
     * @param anmerkung any remarks
     * @param deleted if { this item is soft deleted, otherwise it is active
     */
    public Anderung(Long id, Artikel artikel, Integer anderungId, LocalDate anderungsDatum, AnderungsTyp anderungsTyp,
            String bezeichnung, Integer stuck, String anmerkung, Boolean deleted) {
        super(id, deleted);

        setArtikel(artikel);
        setAnderungId(anderungId);
        setAnderungsDatum(anderungsDatum);
        setAnderungsTyp(anderungsTyp);
        setBezeichnung(bezeichnung);
        setStuck(stuck);
        setAnmerkung(anmerkung);
    }

    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ANDERUNG
            + "_fk1"))
    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    @BusinessKey
    @Column(name = DBNames.ANDERUNG_ID)
    public Integer getAnderungId() {
        return anderungId;
    }

    public void setAnderungId(Integer anderungId) {
        this.anderungId = anderungId;
    }

    @Column(name = DBNames.ANDERUNGSDATUM)
    public LocalDate getAnderungsDatum() {
        return anderungsDatum;
    }

    public void setAnderungsDatum(LocalDate anderungsDatum) {
        this.anderungsDatum = anderungsDatum;
    }

    @Column(name = DBNames.ANDERUNGS_TYP)
    @Enumerated(EnumType.STRING)
    public AnderungsTyp getAnderungsTyp() {
        return anderungsTyp;
    }

    public void setAnderungsTyp(AnderungsTyp changeTyp) {
        this.anderungsTyp = changeTyp;
    }

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Column(name = DBNames.STUCK)
    public Integer getStuck() {
        return stuck;
    }

    public void setStuck(Integer stuck) {
        this.stuck = stuck;
    }

    @Column(name = DBNames.ANMERKUNG)
    public String getAnmerkung() {
        return anmerkung;
    }

    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    public int compareTo(Item other) {
        if (other instanceof Anderung) {
            return new CompareToBuilder().append(getArtikel(), ((Anderung) other).getArtikel())
                    .append(getAnderungId(), ((Anderung) other).getAnderungId()).toComparison();
        }

        return super.compareTo(other);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getArtikel()).append(getAnderungId()).hashCode();
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

        return new EqualsBuilder().append(getArtikel(), other.getArtikel())
                .append(getAnderungId(), other.getAnderungId()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).append(ApiNames.ARTIKEL, getArtikel())
                .append(ApiNames.ANDERUNG_ID, getAnderungId()).append(ApiNames.ANDERUNGSDATUM, getAnderungsDatum())
                .append(ApiNames.ANDERUNGS_TYP, getAnderungsTyp()).append(ApiNames.BEZEICHNUNG, getBezeichnung())
                .append(ApiNames.STUCK, getStuck()).append(ApiNames.ANMERKUNG, getAnmerkung()).toString();
    }
}
