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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.entity.base.ItemImpl;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Vorbild.
 * The prototype for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.VORBILD)
@Table(name = DBNames.VORBILD, indexes = { @Index(columnList = DBNames.GATTUNG_ID, unique = true),
        @Index(columnList = DBNames.UNTER_KATEGORIE_ID),
        @Index(columnList = DBNames.ANTRIEB_ID),
        @Index(columnList = DBNames.ACHSFOLG_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.GATTUNG_ID }) })
public class Vorbild extends ItemImpl {

    /** The gattung. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.gattung.notnull}")
    private Gattung gattung;
    
    /** The bezeichnung. */
    private String bezeichnung;

    /** The unter kategorie. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.unterKategorie.notnull}")
    private UnterKategorie unterKategorie;

    /** The bahnverwaltung */
    private Bahnverwaltung bahnverwaltung;
    
    /** The hersteller. */
    private String hersteller;

    /** The bauzeit. */
    @Past(message = "{com.linepro.modellbahn.validator.constraints.kaufdatum.past}")
    private LocalDate bauzeit;

    /** The anzahl. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anzahl.positive}")
    private Integer anzahl;

    /** The betreibs nummer. */
    private String betreibsNummer;

    /** The antrieb. */
    private Antrieb antrieb;

    /** The achsfolg. */
    private Achsfolg achsfolg;

    /** The anfahrzugkraft. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anfahrzugkraft.positive}")
    private BigDecimal anfahrzugkraft;

    /** The leistung. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.leistung.positive}")
    private BigDecimal leistung;

    /** The dienstgewicht. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dienstgewicht.positive}")
    private BigDecimal dienstgewicht;

    /** The geschwindigkeit. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.geschwindigkeit.positive}")
    private Integer geschwindigkeit;

    /** The lange. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.lange.positive}")
    private BigDecimal lange;

    /** The ausserdienst. */
    @Past(message = "{com.linepro.modellbahn.validator.constraints.ausserdienst.past}")
    private LocalDate ausserdienst;

    /** The dm treibrad. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmTreibrad.positive}")
    private BigDecimal dmTreibrad;

    /** The dm laufrad vorn. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmLaufradVorn.positive}")
    private BigDecimal dmLaufradVorn;

    /** The dm laufrad hinten. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmLaufradHinten.positive}")
    private BigDecimal dmLaufradHinten;

    /** The zylinder. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.zylinder.positive}")
    private Integer zylinder;

    /** The dm zylinder. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.dmZylinder.positive}")
    private BigDecimal dmZylinder;

    /** The kolbenhub. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.kolbenhub.positive}")
    private BigDecimal kolbenhub;

    /** The kesseluberdruck. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.kesseluberdruck.positive}")
    private BigDecimal kesseluberdruck;

    /** The rostflache. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.rostflache.positive}")
    private BigDecimal rostflache;

    /** The uberhitzerflache. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.uberhitzerflache.positive}")
    private BigDecimal uberhitzerflache;

    /** The wasservorrat. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.wasservorrat.positive}")
    private BigDecimal wasservorrat;

    /** The verdampfung. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.verdampfung.positive}")
    private BigDecimal verdampfung;

    /** The fahrmotoren. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.fahrmotoren.positive}")
    private Integer fahrmotoren;

    /** The motorbauart. */
    private String motorbauart;

    /** The leistungsubertragung. */
    private LeistungsUbertragung leistungsUbertragung;

    /** The reichweite. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.reichweite.positive}")
    private BigDecimal reichweite;

    /** The kapazitaet. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.kapazitat.positive}")
    private BigDecimal kapazitat;

    /** The klasse. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.klasse.positive}")
    private Integer klasse;

    /** The sitzplatze KL 1. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL1.positive}")
    private Integer sitzplatzeKL1;

    /** The sitzplatze KL 2. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL2.positive}")
    private Integer sitzplatzeKL2;

    /** The sitzplatze KL 3. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL3.positive}")
    private Integer sitzplatzeKL3;

    /** The sitzplatze KL 4. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.sitzplatzeKL4.positive}")
    private Integer sitzplatzeKL4;

    /** The aufbauten. */
    private String aufbau;

    /** The triebkopf. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.triebkopf.positive}")
    private Integer triebkopf;

    /** The mittelwagen. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.mittelwagen.positive}")
    private Integer mittelwagen;

    /** The drehgestell bauart. */
    private String drehgestellBauart;

    /** The abbildung. */
    private Path abbildung;

    /**
     * Instantiates a new vorbild.
     */
    public Vorbild() {
        super();
    }

    /**
     * Instantiates a new vorbild.
     *
     * @param id the id
     * @param gattung the gattung
     * @param unterKategorie the unter kategorie
     * @param bahnverwaltung the bahnverwaltung
     * @param hersteller the hersteller
     * @param bauzeit the bauzeit
     * @param anzahl the anzahl
     * @param betreibsNummer the betreibs nummer
     * @param antrieb the antrieb
     * @param achsfolg the achsfolg
     * @param anfahrzugkraft the anfahrzugkraft
     * @param leistung the leistung
     * @param dienstgewicht the dienstgewicht
     * @param geschwindigkeit the geschwindigkeit
     * @param lange the lange
     * @param ausserdienst the ausserdienst
     * @param dmTreibrad the dm treibrad
     * @param dmLaufradVorn the dm laufrad vorn
     * @param dmLaufradHinten the dm laufrad hinten
     * @param zylinder the zylinder
     * @param dmZylinder the dm zylinder
     * @param kolbenhub the kolbenhub
     * @param kesseluberdruck the kesseluberdruck
     * @param rostflache the rostflache
     * @param uberhitzerflache the uberhitzerflache
     * @param wasservorrat the wasservorrat
     * @param verdampfung the verdampfung
     * @param fahrmotoren the fahrmotoren
     * @param motorbauart the motorbauart
     * @param leistungsubertragung the leistungsubertragung
     * @param reichweite the reichweite
     * @param kapazitaet the kapazitaet
     * @param klasse the klasse
     * @param sitzPlatzeKL1 the sitz platze KL 1
     * @param sitzPlatzeKL2 the sitz platze KL 2
     * @param sitzPlatzeKL3 the sitz platze KL 3
     * @param sitzPlatzeKL4 the sitz platze KL 4
     * @param aufbauten the aufbauten
     * @param triebkopf the triebkopf
     * @param mittelwagen the mittelwagen
     * @param drehgestellbauart the drehgestellbauart
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public Vorbild(Long id, Gattung gattung, UnterKategorie unterKategorie, Bahnverwaltung bahnverwaltung, String hersteller, LocalDate bauzeit,
            Integer anzahl, String betreibsNummer, Antrieb antrieb, Achsfolg achsfolg, String bezeichnung, BigDecimal anfahrzugkraft,
            BigDecimal leistung, BigDecimal dienstgewicht, Integer geschwindigkeit, BigDecimal lange, LocalDate ausserdienst,
            BigDecimal dmTreibrad, BigDecimal dmLaufradVorn, BigDecimal dmLaufradHinten, Integer zylinder, BigDecimal dmZylinder,
            BigDecimal kolbenhub, BigDecimal kesseluberdruck, BigDecimal rostflache, BigDecimal uberhitzerflache, BigDecimal wasservorrat,
            BigDecimal verdampfung, Integer fahrmotoren, String motorbauart, LeistungsUbertragung leistungsubertragung, BigDecimal reichweite, BigDecimal kapazitaet, Integer klasse, Integer sitzPlatzeKL1,
            Integer sitzPlatzeKL2, Integer sitzPlatzeKL3, Integer sitzPlatzeKL4, String aufbauten, Integer triebkopf, Integer mittelwagen, String drehgestellbauart, Boolean deleted) {
        super(id, deleted);

        setGattung(gattung);
        setBezeichnung(bezeichnung);
        setUnterKategorie(unterKategorie);
        setBahnverwaltung(bahnverwaltung);
        setHersteller(hersteller);
        setBauzeit(bauzeit);
        setAnzahl(anzahl);
        setBetreibsNummer(betreibsNummer);
        setAntrieb(antrieb);
        setAchsfolg(achsfolg);
        setAnfahrzugkraft(anfahrzugkraft);
        setLeistung(leistung);
        setDienstgewicht(dienstgewicht);
        setGeschwindigkeit(geschwindigkeit);
        setLange(lange);
        setAusserdienst(ausserdienst);
        setDmTreibrad(dmTreibrad);
        setDmLaufradVorn(dmLaufradVorn);
        setDmLaufradHinten(dmLaufradHinten);
        setZylinder(zylinder);
        setDmZylinder(dmZylinder);
        setKolbenhub(kolbenhub);
        setKesseluberdruck(kesseluberdruck);
        setRostflache(rostflache);
        setuberhitzerflache(uberhitzerflache);
        setWasservorrat(wasservorrat);
        setVerdampfung(verdampfung);
        setFahrmotoren(fahrmotoren);
        setMotorbauart(motorbauart);
        setLeistungsubertragung(leistungsubertragung);
        setReichweite(reichweite);
        setKapazitat(kapazitaet);
        setKlasse(klasse);
        setSitzPlatzeKL1(sitzPlatzeKL1);
        setSitzPlatzeKL2(sitzPlatzeKL2);
        setSitzPlatzeKL3(sitzPlatzeKL3);
        setSitzPlatzeKL4(sitzPlatzeKL4);
        setAufbau(aufbauten);
        setTriebkopf(triebkopf);
        setMittelwagen(mittelwagen);
        setDrehgestellBauart(drehgestellbauart);
    }

    
    @BusinessKey
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk1"))
    public Gattung getGattung() {
        return gattung;
    }

    
    public void setGattung(Gattung gattung) {
        this.gattung = gattung;
    }

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
      return bezeichnung;
    }

    
    public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk2"))
    public UnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    
    public void setUnterKategorie(UnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk3"))
    public Bahnverwaltung getBahnverwaltung() {
        return bahnverwaltung;
    }

    
    public void setBahnverwaltung(Bahnverwaltung bahnverwaltung) {
        this.bahnverwaltung = bahnverwaltung;
    }

    
    @Column(name = DBNames.HERSTELLER, length = 100)
    public String getHersteller() {
        return hersteller;
    }

    
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    
    @Column(name = DBNames.BAUZEIT)
    public LocalDate getBauzeit() {
        return bauzeit;
    }

    
    public void setBauzeit(LocalDate bauzeit) {
        this.bauzeit = bauzeit;
    }

    
    @Column(name = DBNames.ANZAHL)
    public Integer getAnzahl() {
        return anzahl;
    }

    
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    
    @Column(name = DBNames.BETREIBSNUMMER, length = 100)
    public String getBetreibsNummer() {
        return betreibsNummer;
    }

    
    public void setBetreibsNummer(String betreibsNummer) {
        this.betreibsNummer = betreibsNummer;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Antrieb.class)
    @JoinColumn(name = DBNames.ANTRIEB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk4"))
    public Antrieb getAntrieb() {
        return antrieb;
    }

    
    public void setAntrieb(Antrieb antrieb) {
        this.antrieb = antrieb;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk5"))
    public Achsfolg getAchsfolg() {
        return achsfolg;
    }

    
    public void setAchsfolg(Achsfolg achsfolg) {
        this.achsfolg = achsfolg;
    }

    
    @Column(name = DBNames.ANFAHRZUGKRAFT)
    public BigDecimal getAnfahrzugkraft() {
        return anfahrzugkraft;
    }

    
    public void setAnfahrzugkraft(BigDecimal anfahrzugkraft) {
        this.anfahrzugkraft = anfahrzugkraft;
    }

    
    @Column(name = DBNames.LEISTUNG)
    public BigDecimal getLeistung() {
        return leistung;
    }

    
    public void setLeistung(BigDecimal leistung) {
        this.leistung = leistung;
    }

    
    @Column(name = DBNames.DIENSTGEWICHT)
    public BigDecimal getDienstgewicht() {
        return dienstgewicht;
    }

    
    public void setDienstgewicht(BigDecimal dienstgewicht) {
        this.dienstgewicht = dienstgewicht;
    }

    
    @Column(name = DBNames.GESCHWINDIGKEIT)
    public Integer getGeschwindigkeit() {
        return geschwindigkeit;
    }

    
    public void setGeschwindigkeit(Integer geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    
    @Column(name = DBNames.LANGE)
    public BigDecimal getLange() {
        return lange;
    }

    
    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    
    @Column(name = DBNames.AUSSERDIENST)
    public LocalDate getAusserdienst() {
        return ausserdienst;
    }

    
    public void setAusserdienst(LocalDate ausserdienst) {
        this.ausserdienst = ausserdienst;
    }

    
    @Column(name = DBNames.DMTREIBRAD)
    public BigDecimal getDmTreibrad() {
        return dmTreibrad;
    }

    
    public void setDmTreibrad(BigDecimal dmTreibrad) {
        this.dmTreibrad = dmTreibrad;
    }

    
    @Column(name = DBNames.DMLAUFRADVORN)
    public BigDecimal getDmLaufradVorn() {
        return dmLaufradVorn;
    }

    
    public void setDmLaufradVorn(BigDecimal dmLaufradVorn) {
        this.dmLaufradVorn = dmLaufradVorn;
    }

    
    @Column(name = DBNames.DMLAUFRADHINTEN)
    public BigDecimal getDmLaufradHinten() {
        return dmLaufradHinten;
    }

    
    public void setDmLaufradHinten(BigDecimal dmLaufradHinten) {
        this.dmLaufradHinten = dmLaufradHinten;
    }

    
    @Column(name = DBNames.ZYLINDER)
    public Integer getZylinder() {
        return zylinder;
    }

    
    public void setZylinder(Integer zylinder) {
        this.zylinder = zylinder;
    }

    
    @Column(name = DBNames.DMZYLINDER)
    public BigDecimal getDmZylinder() {
        return dmZylinder;
    }

    
    public void setDmZylinder(BigDecimal dmZylinder) {
        this.dmZylinder = dmZylinder;
    }

    
    @Column(name = DBNames.KOLBENHUB)
    public BigDecimal getKolbenhub() {
        return kolbenhub;
    }

    
    public void setKolbenhub(BigDecimal kolbenhub) {
        this.kolbenhub = kolbenhub;
    }

    
    @Column(name = DBNames.KESSELUBERDRUCK)
    public BigDecimal getKesseluberdruck() {
        return kesseluberdruck;
    }

    
    public void setKesseluberdruck(BigDecimal kesseluberdruck) {
        this.kesseluberdruck = kesseluberdruck;
    }

    
    @Column(name = DBNames.ROSTFLACHE)
    public BigDecimal getRostflache() {
        return rostflache;
    }

    
    public void setRostflache(BigDecimal rostflache) {
        this.rostflache = rostflache;
    }

    
    @Column(name = DBNames.UBERHITZERFLACHE)
    public BigDecimal getuberhitzerflache() {
        return uberhitzerflache;
    }

    
    public void setuberhitzerflache(BigDecimal uberhitzerflache) {
        this.uberhitzerflache = uberhitzerflache;
    }

    
    @Column(name = DBNames.WASSERVORRAT)
    public BigDecimal getWasservorrat() {
        return wasservorrat;
    }

    
    public void setWasservorrat(BigDecimal wasservorrat) {
        this.wasservorrat = wasservorrat;
    }

    @Column(name = DBNames.VERDAMPFUNG)
    public BigDecimal getVerdampfung() {
        return verdampfung;
    }

    
    public void setVerdampfung(BigDecimal verdampfung) {
        this.verdampfung = verdampfung;
    }

    
    @Column(name = DBNames.FAHRMOTOREN)
    public Integer getFahrmotoren() {
        return fahrmotoren;
    }

    
    public void setFahrmotoren(Integer fahrmotoren) {
        this.fahrmotoren = fahrmotoren;
    }

    
    @Column(name = DBNames.MOTORBAUART, length = 100)
    public String getMotorbauart() {
        return motorbauart;
    }

    
    public void setMotorbauart(String motorbauart) {
        this.motorbauart = motorbauart;
    }

    
    @Column(name = DBNames.LEISTUNGSUBERTRAGUNG)
    @Enumerated(EnumType.STRING)
    public LeistungsUbertragung getLeistungsubertragung() {
        return leistungsUbertragung;
    }

    
    public void setLeistungsubertragung(LeistungsUbertragung leistungsubertragung) {
        this.leistungsUbertragung = leistungsubertragung;
    }

    
    @Column(name = DBNames.REICHWEITE)
    public BigDecimal getReichweite() {
        return reichweite;
    }

    
    public void setReichweite(BigDecimal reichweite) {
        this.reichweite = reichweite;
    }

    
    @Column(name = DBNames.KAPAZITAT)
    public BigDecimal getKapazitat() {
        return kapazitat;
    }

    
    public void setKapazitat(BigDecimal kapazitat) {
        this.kapazitat = kapazitat;
    }

    
    @Column(name = DBNames.KLASSE)
    public Integer getKlasse() {
        return klasse;
    }

    
    public void setKlasse(Integer klasse) {
        this.klasse = klasse;
    }

    
    @Column(name = DBNames.SITZPLATZEKL1)
    public Integer getSitzPlatzeKL1() {
        return sitzplatzeKL1;
    }

    
    public void setSitzPlatzeKL1(Integer sitzPlatzeKL1) {
        this.sitzplatzeKL1 = sitzPlatzeKL1;
    }

    
    @Column(name = DBNames.SITZPLATZEKL2)
    public Integer getSitzPlatzeKL2() {
        return sitzplatzeKL2;
    }

    
    public void setSitzPlatzeKL2(Integer sitzPlatzeKL2) {
        this.sitzplatzeKL2 = sitzPlatzeKL2;
    }

    
    @Column(name = DBNames.SITZPLATZEKL3)
    public Integer getSitzPlatzeKL3() {
        return sitzplatzeKL3;
    }

    
    public void setSitzPlatzeKL3(Integer sitzPlatzeKL3) {
        this.sitzplatzeKL3 = sitzPlatzeKL3;
    }

    
    @Column(name = DBNames.SITZPLATZEKL4)
    public Integer getSitzPlatzeKL4() {
        return sitzplatzeKL4;
    }

    
    public void setSitzPlatzeKL4(Integer sitzPlatzeKL4) {
        this.sitzplatzeKL4 = sitzPlatzeKL4;
    }

    
    @Column(name = DBNames.AUFBAU, length = 100)
    public String getAufbau() {
        return aufbau;
    }

    
    public void setAufbau(String aufbau) {
        this.aufbau = aufbau;
    }

    
    @Column(name = DBNames.TRIEBKOPF)
    public Integer getTriebkopf() {
        return triebkopf;
    }

    
    public void setTriebkopf(Integer triebkopf) {
        this.triebkopf = triebkopf;
    }

    
    @Column(name = DBNames.MITTELWAGEN)
    public Integer getMittelwagen() {
        return mittelwagen;
    }

    
    public void setMittelwagen(Integer mittelwagen) {
        this.mittelwagen = mittelwagen;
    }

    
    @Column(name = DBNames.DREHGESTELLBAUART, length = 100)
    public String getDrehgestellBauart() {
        return drehgestellBauart;
    }

    
    public void setDrehgestellBauart(String drehgestellbauart) {
        this.drehgestellBauart = drehgestellbauart;
    }

    
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    public Path getAbbildung() {
        return abbildung;
    }

    
    public void setAbbildung(Path abbildung) {
        this.abbildung = abbildung;
    }

    @Override
    public int compareTo(Item other) {
      if (other instanceof Vorbild) {
        return new CompareToBuilder()
            .append(getGattung().getName(), ((Vorbild) other).getGattung().getName())
            .toComparison();
      }

      return super.compareTo(other);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.GATTUNG, getGattung())
                .append(ApiNames.UNTER_KATEGORIE, getUnterKategorie())
                .append(ApiNames.BAHNVERWALTUNG, getBahnverwaltung())
                .append(ApiNames.HERSTELLER, getHersteller())
                .append(ApiNames.BAUZEIT, getBauzeit())
                .append(ApiNames.ANZAHL, getAnzahl())
                .append(ApiNames.BETREIBSNUMMER, getBetreibsNummer())
                .append(ApiNames.ANTRIEB, getAntrieb())
                .append(ApiNames.ACHSFOLG, getAchsfolg())
                .append(ApiNames.ANFAHRZUGKRAFT, getAnfahrzugkraft())
                .append(ApiNames.LEISTUNG, getLeistung())
                .append(ApiNames.DIENSTGEWICHT, getDienstgewicht())
                .append(ApiNames.GESCHWINDIGKEIT, getGeschwindigkeit())
                .append(ApiNames.LANGE, getLange())
                .append(ApiNames.AUSSERDIENST, getAusserdienst())
                .append(ApiNames.DMTREIBRAD, getDmTreibrad())
                .append(ApiNames.DMLAUFRADVORN, getDmLaufradVorn())
                .append(ApiNames.DMLAUFRADHINTEN, getDmLaufradHinten())
                .append(ApiNames.ZYLINDER, getZylinder())
                .append(ApiNames.DMZYLINDER, getDmZylinder())
                .append(ApiNames.KOLBENHUB, getKolbenhub())
                .append(ApiNames.KESSELUBERDRUCK, getKesseluberdruck())
                .append(ApiNames.ROSTFLACHE, getRostflache())
                .append(ApiNames.UBERHITZERFLACHE, getuberhitzerflache())
                .append(ApiNames.WASSERVORRAT, getWasservorrat())
                .append(ApiNames.VERDAMPFUNG, getVerdampfung())
                .append(ApiNames.FAHRMOTOREN, getFahrmotoren())
                .append(ApiNames.MOTORBAUART, getMotorbauart())
                .append(ApiNames.LEISTUNGSUBERTRAGUNG, getLeistungsubertragung())
                .append(ApiNames.REICHWEITE, getReichweite())
                .append(ApiNames.KAPAZITAT, getKapazitat())
                .append(ApiNames.KLASSE, getKlasse())
                .append(ApiNames.SITZPLATZEKL1, getSitzPlatzeKL1())
                .append(ApiNames.SITZPLATZEKL2, getSitzPlatzeKL2())
                .append(ApiNames.SITZPLATZEKL3, getSitzPlatzeKL3())
                .append(ApiNames.SITZPLATZEKL4, getSitzPlatzeKL4())
                .append(ApiNames.AUFBAU, getAufbau())
                .append(ApiNames.TRIEBKOPF, getTriebkopf())
                .append(ApiNames.MITTELWAGEN, getMittelwagen())
                .append(ApiNames.DREHGESTELLBAUART, getDrehgestellBauart())
                .append(ApiNames.ABBILDUNG, getAbbildung())
                .toString();
    }
}