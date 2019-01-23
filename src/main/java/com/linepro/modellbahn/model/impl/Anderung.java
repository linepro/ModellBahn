package com.linepro.modellbahn.model.impl;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.linepro.modellbahn.model.IAnderung;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.keys.AnderungKey;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Artikel.
 * An article. 
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.ANDERUNG)
@Table(name = DBNames.ANDERUNG, indexes = {
            @Index(columnList = DBNames.ARTIKEL_ID),
            @Index(columnList = DBNames.ANDERUNGS_ID) 
        }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.ARTIKEL_ID, DBNames.ANDERUNGS_ID } )
        })
public class Anderung extends AbstractItem<AnderungKey> implements IAnderung {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8652624782179487496L;

    public IArtikel artikel;

    public Integer anderungsId;

    public LocalDate anderungsDatum;

    public AnderungsTyp anderungsTyp;

    public String bezeichnung;

    public Integer stuck;

    public String anmerkung;

    /**
     * Instantiates a new artikel.
     */
    public Anderung() {
        super();
    }

    /**
     * Instantiates a new artikel.
     *
     * @param id the id
     * @param artikel 
     * @param anderungsId 
     * @param anderungsDatum 
     * @param anderungsTyp 
     * @param bezeichnung 
     * @param stuck 
     * @param anmerkung 
     * @param deleted the deleted
     */
    public Anderung(Long id, IArtikel artikel, Integer anderungsId, LocalDate anderungsDatum, AnderungsTyp anderungsTyp, String bezeichnung, Integer stuck, String anmerkung, Boolean deleted) {
        super(id, deleted);

        setArtikel(artikel);
        setAnderungsId(anderungsId);
        setAnderungsDatum(anderungsDatum);
        setAnderungsTyp(anderungsTyp);
        setBezeichnung(bezeichnung);
        setStuck(stuck);
        setAnmerkung(anmerkung);
    }

    @Override
    @BusinessKey
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ANDERUNG + "_fk1"))
    public IArtikel getArtikel() {
        return artikel;
    }

    @Override
    public void setArtikel(IArtikel artikel) {
        this.artikel = artikel;
    }

    @Override
    @BusinessKey
    @Column(name = DBNames.ANDERUNGS_ID)
    public Integer getAnderungsId() {
        return anderungsId;
    }

    @Override
    public void setAnderungsId(Integer anderungsId) {
        this.anderungsId = anderungsId;
    }

    @Override
    @Column(name = DBNames.ANDERUNGSDATUM)
    public LocalDate getAnderungsDatum() {
        return anderungsDatum;
    }

    @Override
    public void setAnderungsDatum(LocalDate anderungsDatum) {
        this.anderungsDatum = anderungsDatum;
    }

    @Override
    @Column(name = DBNames.ANDERUNGS_TYP)
    @Enumerated(EnumType.STRING)
    public AnderungsTyp getAnderungsTyp() {
        return anderungsTyp;
    }

    @Override
    public void setAnderungsTyp(AnderungsTyp changeTyp) {
        this.anderungsTyp = changeTyp;
    }

    @Override
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    @Column(name = DBNames.STUCK)
    public Integer getStuck() {
        return stuck;
    }

    @Override
    public void setStuck(Integer stuck) {
        this.stuck = stuck;
    }

    @Override
    @Column(name = DBNames.ANMERKUNG)
    public String getAnmerkung() {
        return anmerkung;
    }

    @Override
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    @Transient
    public String getLinkId() {
        return String.format(ApiPaths.ANDERUNG_LINK, getParentId(), getAnderungsId());
    }

    @Override
    @Transient
    public String getParentId() {
        return getArtikel().getLinkId();
    }

    @Override
    public int compareTo(IItem<?> other) {
      if (other instanceof IAnderung) {
        return new CompareToBuilder()
            .append(getArtikel(), ((IAnderung) other).getArtikel())
            .append(getAnderungsId(), ((IAnderung) other).getAnderungsId())
            .toComparison();
      }

      return super.compareTo(other);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.ARTIKEL, artikel)
                .append(ApiNames.ANDERUNGS_ID, anderungsId)
                .append(ApiNames.ANDERUNGSDATUM, anderungsDatum)
                .append(ApiNames.ANDERUNGS_TYP, anderungsTyp)
                .append(ApiNames.BEZEICHNUNG, bezeichnung)
                .append(ApiNames.STUCK, stuck)
                .append(ApiNames.ANMERKUNG, anmerkung)
                .toString();
    }
}
