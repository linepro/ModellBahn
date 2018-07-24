package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Vorbild.
 * The prototype for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Vorbild")
@Table(name = "vorbilder", indexes = { @Index(columnList = "gattung_id", unique = true),
        @Index(columnList = "unter_kategorie_id"),
        @Index(columnList = "antrieb_id"),
        @Index(columnList = "achsfolg_id"),
        @Index(columnList = "steuerung_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "gattung_id" }) })
public class Vorbild extends AbstractItem implements IVorbild {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4852882107081643608L;

    /** The gattung. */
    private IGattung gattung;

    /** The unter kategorie. */
    private IUnterKategorie unterKategorie;

    /** The hersteller. */
    private String hersteller;

    /** The bauzeit. */
    private Date bauzeit;

    /** The anzahl. */
    private Integer anzahl;

    /** The betriebs nummer. */
    private String betriebsNummer;

    /** The antrieb. */
    private IAntrieb antrieb;

    /** The achsfolg. */
    private IAchsfolg achsfolg;

    /** The anfahrzugkraft. */
    private BigDecimal anfahrzugkraft;

    /** The leistung. */
    private BigDecimal leistung;

    /** The dienstgewicht. */
    private BigDecimal dienstgewicht;

    /** The geschwindigkeit. */
    private Long geschwindigkeit;

    /** The lange. */
    private BigDecimal lange;

    /** The ausserdienst. */
    private Date ausserdienst;

    /** The dm treibrad. */
    private BigDecimal dmTreibrad;

    /** The dm laufrad vorn. */
    private BigDecimal dmLaufradVorn;

    /** The dm laufrad hinten. */
    private BigDecimal dmLaufradHinten;

    /** The zylinder. */
    private Integer zylinder;

    /** The dm zylinder. */
    private BigDecimal dmZylinder;

    /** The kolbenhub. */
    private BigDecimal kolbenhub;

    /** The kesselueberdruck. */
    private BigDecimal kesselueberdruck;

    /** The rostflaeche. */
    private BigDecimal rostflaeche;

    /** The ueberhitzerflaeche. */
    private BigDecimal ueberhitzerflaeche;

    /** The wasservorrat. */
    private BigDecimal wasservorrat;

    /** The verdampfung. */
    private BigDecimal verdampfung;

    /** The steuerung. */
    private ISteuerung steuerung;

    /** The fahrmotoren. */
    private Integer fahrmotoren;

    /** The motorbauart. */
    private String motorbauart;

    /** The leistungsuebertragung. */
    private BigDecimal leistungsuebertragung;

    /** The reichweite. */
    private BigDecimal reichweite;

    /** The kapazitaet. */
    private BigDecimal kapazitaet;

    /** The klasse. */
    private Integer klasse;

    /** The sitzplatze KL 1. */
    private Long sitzplatzeKL1;

    /** The sitzplatze KL 2. */
    private Long sitzplatzeKL2;

    /** The sitzplatze KL 3. */
    private Long sitzplatzeKL3;

    /** The sitzplatze KL 4. */
    private Long sitzplatzeKL4;

    /** The aufbauten. */
    private String aufbauten;

    /** The triebzug anzeigen. */
    private Boolean triebzugAnzeigen;

    /** The triebkoepfe. */
    private Long triebkoepfe;

    /** The mittelwagen. */
    private Long mittelwagen;

    /** The sitzplatze TZKL 1. */
    private Long sitzplatzeTZKL1;

    /** The sitzplatze tz KL 2. */
    private Long sitzplatzeTzKL2;

    /** The drehgestell bauart. */
    private String drehgestellBauart;

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
     * @param hersteller the hersteller
     * @param bauzeit the bauzeit
     * @param anzahl the anzahl
     * @param betriebsNummer the betriebs nummer
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
     * @param kesselueberdruck the kesselueberdruck
     * @param rostflaeche the rostflaeche
     * @param ueberhitzerflaeche the ueberhitzerflaeche
     * @param wasservorrat the wasservorrat
     * @param verdampfung the verdampfung
     * @param steuerung the steuerung
     * @param fahrmotoren the fahrmotoren
     * @param motorbauart the motorbauart
     * @param leistungsuebertragung the leistungsuebertragung
     * @param reichweite the reichweite
     * @param kapazitaet the kapazitaet
     * @param klasse the klasse
     * @param sitzPlatzeKL1 the sitz platze KL 1
     * @param sitzPlatzeKL2 the sitz platze KL 2
     * @param sitzPlatzeKL3 the sitz platze KL 3
     * @param sitzPlatzeKL4 the sitz platze KL 4
     * @param aufbauten the aufbauten
     * @param triebzugAnzeigen the triebzug anzeigen
     * @param triebkoepfe the triebkoepfe
     * @param mittelwagen the mittelwagen
     * @param sitzPlatzeTZKL1 the sitz platze TZKL 1
     * @param sitzPlatzeTzKL2 the sitz platze tz KL 2
     * @param drehgestellbauart the drehgestellbauart
     * @param deleted the deleted
     */
    public Vorbild(Long id, IGattung gattung, IUnterKategorie unterKategorie, String hersteller, Date bauzeit,
            Integer anzahl,
            String betriebsNummer, IAntrieb antrieb, IAchsfolg achsfolg, BigDecimal anfahrzugkraft,
            BigDecimal leistung, BigDecimal dienstgewicht, Long geschwindigkeit, BigDecimal lange, Date ausserdienst,
            BigDecimal dmTreibrad, BigDecimal dmLaufradVorn, BigDecimal dmLaufradHinten, Integer zylinder, BigDecimal dmZylinder,
            BigDecimal kolbenhub,
            BigDecimal kesselueberdruck, BigDecimal rostflaeche, BigDecimal ueberhitzerflaeche, BigDecimal wasservorrat,
            BigDecimal verdampfung, ISteuerung steuerung, Integer fahrmotoren, String motorbauart,
            BigDecimal leistungsuebertragung, BigDecimal reichweite, BigDecimal kapazitaet, Integer klasse, Long sitzPlatzeKL1,
            Long sitzPlatzeKL2, Long sitzPlatzeKL3, Long sitzPlatzeKL4, String aufbauten, Boolean triebzugAnzeigen,
            Long triebkoepfe, Long mittelwagen, Long sitzPlatzeTZKL1, Long sitzPlatzeTzKL2,
            String drehgestellbauart, Boolean deleted) {
        super(id, deleted);

        setGattung(gattung);
        setUnterKategorie(unterKategorie);
        setHersteller(hersteller);
        setBauzeit(bauzeit);
        setAnzahl(anzahl);
        setBetriebsNummer(betriebsNummer);
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
        setKesselueberdruck(kesselueberdruck);
        setRostflaeche(rostflaeche);
        setUeberhitzerflaeche(ueberhitzerflaeche);
        setWasservorrat(wasservorrat);
        setVerdampfung(verdampfung);
        setSteuerung(steuerung);
        setFahrmotoren(fahrmotoren);
        setMotorbauart(motorbauart);
        setLeistungsuebertragung(leistungsuebertragung);
        setReichweite(reichweite);
        setKapazitaet(kapazitaet);
        setKlasse(klasse);
        setSitzPlatzeKL1(sitzPlatzeKL1);
        setSitzPlatzeKL2(sitzPlatzeKL2);
        setSitzPlatzeKL3(sitzPlatzeKL3);
        setSitzPlatzeKL4(sitzPlatzeKL4);
        setAufbauten(aufbauten);
        setTriebzugAnzeigen(triebzugAnzeigen);
        setTriebkoepfe(triebkoepfe);
        setMittelwagen(mittelwagen);
        setSitzPlatzeTZKL1(sitzPlatzeTZKL1);
        setSitzPlatzeTzKL2(sitzPlatzeTzKL2);
        setDrehgestellBauart(drehgestellbauart);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = "gattung_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk1"))
    @JsonGetter("gattung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IGattung getGattung() {
        return gattung;
    }

    @Override
    @JsonSetter("gattung")
    public void setGattung(IGattung gattung) {
        this.gattung = gattung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = "unter_kategorie_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk2"))
    @JsonGetter("unterKategorie")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IUnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    @Override
    @JsonSetter("unterKategorie")
    public void setUnterKategorie(IUnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    @Override
    @Column(name = "hersteller", nullable = true, length = 100)
    @JsonGetter("hersteller")
    public String getHersteller() {
        return hersteller;
    }

    @Override
    @JsonSetter("hersteller")
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @Column(name = "bauzeit", nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter("bauzeit")
    public Date getBauzeit() {
        return bauzeit;
    }

    @Override
    @JsonSetter("bauzeit")
    public void setBauzeit(Date bauzeit) {
        this.bauzeit = bauzeit;
    }

    @Override
    @Column(name = "anzahl", nullable = true)
    @JsonGetter("anzahl")
    public Integer getAnzahl() {
        return anzahl;
    }

    @Override
    @JsonSetter("anzahl")
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    @Column(name = "betriebsnummer", nullable = true, length = 100)
    @JsonGetter("betriebsNummer")
    public String getBetriebsNummer() {
        return betriebsNummer;
    }

    @Override
    @JsonSetter("betriebsNummer")
    public void setBetriebsNummer(String betriebsNummer) {
        this.betriebsNummer = betriebsNummer;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Antrieb.class)
    @JoinColumn(name = "antrieb_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk3"))
    @JsonGetter("antrieb")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IAntrieb getAntrieb() {
        return antrieb;
    }

    @JsonSetter("antrieb")
    public void setAntrieb(IAntrieb antrieb) {
        this.antrieb = antrieb;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = "achsfolg_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk4"))
    @JsonGetter("achsfolg")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public IAchsfolg getAchsfolg() {
        return achsfolg;
    }

    @Override
    @JsonSetter("achsfolg")
    public void setAchsfolg(IAchsfolg achsfolg) {
        this.achsfolg = achsfolg;
    }

    @Override
    @Column(name = "anfahrzugkraft", nullable = true)
    @JsonGetter("anfahrzugkraft")
    public BigDecimal getAnfahrzugkraft() {
        return anfahrzugkraft;
    }

    @Override
    @JsonSetter("anfahrzugkraft")
    public void setAnfahrzugkraft(BigDecimal anfahrzugkraft) {
        this.anfahrzugkraft = anfahrzugkraft;
    }

    @Override
    @Column(name = "leistung", nullable = true)
    @JsonGetter("leistung")
    public BigDecimal getLeistung() {
        return leistung;
    }

    @Override
    @JsonSetter("leistung")
    public void setLeistung(BigDecimal leistung) {
        this.leistung = leistung;
    }

    @Override
    @Column(name = "dienstgewicht", nullable = true)
    @JsonGetter("dienstgewicht")
    public BigDecimal getDienstgewicht() {
        return dienstgewicht;
    }

    @Override
    @JsonSetter("dienstgewicht")
    public void setDienstgewicht(BigDecimal dienstgewicht) {
        this.dienstgewicht = dienstgewicht;
    }

    @Override
    @Column(name = "geschwindigkeit", nullable = true)
    @JsonGetter("geschwindigkeit")
    public Long getGeschwindigkeit() {
        return geschwindigkeit;
    }

    @Override
    @JsonSetter("geschwindigkeit")
    public void setGeschwindigkeit(Long geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    @Override
    @Column(name = "lange", nullable = true)
    @JsonGetter("lange")
    public BigDecimal getLange() {
        return lange;
    }

    @Override
    @JsonSetter("lange")
    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    @Override
    @Column(name = "ausserdienst", nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter("ausserdienst")
    public Date getAusserdienst() {
        return ausserdienst;
    }

    @Override
    @JsonSetter("ausserdienst")
    public void setAusserdienst(Date ausserdienst) {
        this.ausserdienst = ausserdienst;
    }

    @Override
    @Column(name = "dmtreibrad", nullable = true)
    @JsonGetter("dmTreibrad")
    public BigDecimal getDmTreibrad() {
        return dmTreibrad;
    }

    @Override
    @JsonSetter("dmTreibrad")
    public void setDmTreibrad(BigDecimal dmTreibrad) {
        this.dmTreibrad = dmTreibrad;
    }

    @Override
    @Column(name = "dmlaufradvorn", nullable = true)
    @JsonGetter("dmLaufradVorn")
    public BigDecimal getDmLaufradVorn() {
        return dmLaufradVorn;
    }

    @Override
    @JsonSetter("dmLaufradVorn")
    public void setDmLaufradVorn(BigDecimal dmLaufradVorn) {
        this.dmLaufradVorn = dmLaufradVorn;
    }

    @Override
    @Column(name = "dmlaufradhinten", nullable = true)
    @JsonGetter("dmLaufradHinten")
    public BigDecimal getDmLaufradHinten() {
        return dmLaufradHinten;
    }

    @Override
    @JsonSetter("dmLaufradHinten")
    public void setDmLaufradHinten(BigDecimal dmLaufradHinten) {
        this.dmLaufradHinten = dmLaufradHinten;
    }

    @Override
    @Column(name = "zylinder", nullable = true)
    @JsonGetter("zylinder")
    public Integer getZylinder() {
        return zylinder;
    }

    @Override
    @JsonSetter("zylinder")
    public void setZylinder(Integer zylinder) {
        this.zylinder = zylinder;
    }

    @Override
    @Column(name = "dmzylinder", nullable = true)
    @JsonGetter("dmZylinder")
    public BigDecimal getDmZylinder() {
        return dmZylinder;
    }

    @Override
    @JsonSetter("dmZylinder")
    public void setDmZylinder(BigDecimal dmZylinder) {
        this.dmZylinder = dmZylinder;
    }

    @Override
    @Column(name = "kolbenhub", nullable = true)
    @JsonGetter("kolbenhub")
    public BigDecimal getKolbenhub() {
        return kolbenhub;
    }

    @Override
    @JsonSetter("kolbenhub")
    public void setKolbenhub(BigDecimal kolbenhub) {
        this.kolbenhub = kolbenhub;
    }

    @Override
    @Column(name = "kesselueberdruck", nullable = true)
    @JsonGetter("kesselueberdruck")
    public BigDecimal getKesselueberdruck() {
        return kesselueberdruck;
    }

    @Override
    @JsonSetter("kesselueberdruck")
    public void setKesselueberdruck(BigDecimal kesselueberdruck) {
        this.kesselueberdruck = kesselueberdruck;
    }

    @Override
    @Column(name = "rostflaeche", nullable = true)
    @JsonGetter("rostflaeche")
    public BigDecimal getRostflaeche() {
        return rostflaeche;
    }

    @Override
    @JsonSetter("rostflaeche")
    public void setRostflaeche(BigDecimal rostflaeche) {
        this.rostflaeche = rostflaeche;
    }

    @Override
    @Column(name = "ueberhitzerflaeche", nullable = true)
    @JsonGetter("ueberhitzerflaeche")
    public BigDecimal getUeberhitzerflaeche() {
        return ueberhitzerflaeche;
    }

    @Override
    @JsonSetter("ueberhitzerflaeche")
    public void setUeberhitzerflaeche(BigDecimal ueberhitzerflaeche) {
        this.ueberhitzerflaeche = ueberhitzerflaeche;
    }

    @Override
    @Column(name = "wasservorrat", nullable = true)
    @JsonGetter("wasservorrat")
    public BigDecimal getWasservorrat() {
        return wasservorrat;
    }

    @Override
    @JsonSetter("wasservorrat")
    public void setWasservorrat(BigDecimal wasservorrat) {
        this.wasservorrat = wasservorrat;
    }

    @Column(name = "verdampfung", nullable = true)
    @JsonGetter("verdampfung")
    public BigDecimal getVerdampfung() {
        return verdampfung;
    }

    @Override
    @JsonSetter("verdampfung")
    public void setVerdampfung(BigDecimal verdampfung) {
        this.verdampfung = verdampfung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = "steuerung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk5"))
    @JsonGetter("steuerung")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    @JsonSetter("steuerung")
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @Column(name = "fahrmotoren", nullable = true)
    @JsonGetter("fahrmotoren")
    public Integer getFahrmotoren() {
        return fahrmotoren;
    }

    @Override
    @JsonSetter("fahrmotoren")
    public void setFahrmotoren(Integer fahrmotoren) {
        this.fahrmotoren = fahrmotoren;
    }

    @Override
    @Column(name = "motorbauart", nullable = true, length = 100)
    @JsonGetter("motorbauart")
    public String getMotorbauart() {
        return motorbauart;
    }

    @Override
    @JsonSetter("motorbauart")
    public void setMotorbauart(String motorbauart) {
        this.motorbauart = motorbauart;
    }

    @Override
    @Column(name = "leistungsuebertragung", nullable = true)
    @JsonGetter("gattung")
    public BigDecimal getLeistungsuebertragung() {
        return leistungsuebertragung;
    }

    @Override
    @JsonGetter("reichweite")
    public void setLeistungsuebertragung(BigDecimal leistungsuebertragung) {
        this.leistungsuebertragung = leistungsuebertragung;
    }

    @Override
    @Column(name = "reichweite", nullable = true)
    @JsonGetter("reichweite")
    public BigDecimal getReichweite() {
        return reichweite;
    }

    @Override
    @JsonSetter("reichweite")
    public void setReichweite(BigDecimal reichweite) {
        this.reichweite = reichweite;
    }

    @Override
    @Column(name = "kapazitaet", nullable = true)
    @JsonGetter("kapazitaet")
    public BigDecimal getKapazitaet() {
        return kapazitaet;
    }

    @Override
    @JsonSetter("kapazitaet")
    public void setKapazitaet(BigDecimal kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    @Override
    @Column(name = "klasse", nullable = true)
    @JsonGetter("klasse")
    public Integer getKlasse() {
        return klasse;
    }

    @Override
    @JsonSetter("klasse")
    public void setKlasse(Integer klasse) {
        this.klasse = klasse;
    }

    @Override
    @Column(name = "sitzplatzekl1", nullable = true)
    @JsonGetter("sitzplatzeKL1")
    public Long getSitzPlatzeKL1() {
        return sitzplatzeKL1;
    }

    @Override
    @JsonSetter("sitzplatzeKL1")
    public void setSitzPlatzeKL1(Long sitzPlatzeKL1) {
        this.sitzplatzeKL1 = sitzPlatzeKL1;
    }

    @Override
    @Column(name = "sitzplatzekl2", nullable = true)
    @JsonGetter("gattung")
    public Long getSitzPlatzeKL2() {
        return sitzplatzeKL2;
    }

    @Override
    @JsonSetter("sitzplatzeKL2")
    public void setSitzPlatzeKL2(Long sitzPlatzeKL2) {
        sitzplatzeKL2 = sitzPlatzeKL2;
    }

    @Override
    @Column(name = "sitzplatzekl3", nullable = true)
    @JsonGetter("sitzplatzeKL3")
    public Long getSitzPlatzeKL3() {
        return sitzplatzeKL3;
    }

    @Override
    @JsonSetter("sitzplatzeKL3")
    public void setSitzPlatzeKL3(Long sitzPlatzeKL3) {
        sitzplatzeKL3 = sitzPlatzeKL3;
    }

    @Override
    @Column(name = "sitzplatzekl4", nullable = true)
    @JsonGetter("sitzplatzeKL4")
    public Long getSitzPlatzeKL4() {
        return sitzplatzeKL4;
    }

    @Override
    @JsonSetter("sitzplatzeKL4")
    public void setSitzPlatzeKL4(Long sitzPlatzeKL4) {
        this.sitzplatzeKL4 = sitzPlatzeKL4;
    }

    @Override
    @Column(name = "aufbauten", nullable = true, length = 100)
    @JsonGetter("aufbauten")
    public String getAufbauten() {
        return aufbauten;
    }

    @Override
    @JsonSetter("aufbauten")
    public void setAufbauten(String aufbauten) {
        this.aufbauten = aufbauten;
    }

    @Override
    @Column(name = "triebzuganzeigen", nullable = true)
    @JsonGetter("triebzugAnzeigen")
    public Boolean getTriebzugAnzeigen() {
        return triebzugAnzeigen;
    }

    @Override
    @JsonSetter("triebzugAnzeigen")
    public void setTriebzugAnzeigen(Boolean triebzugAnzeigen) {
        this.triebzugAnzeigen = triebzugAnzeigen;
    }

    @Override
    @Column(name = "triebkoepfe", nullable = true)
    @JsonGetter("triebkoepfe")
    public Long getTriebkoepfe() {
        return triebkoepfe;
    }

    @Override
    @JsonSetter("triebkoepfe")
    public void setTriebkoepfe(Long triebkoepfe) {
        this.triebkoepfe = triebkoepfe;
    }

    @Override
    @Column(name = "mittelwagen", nullable = true)
    @JsonGetter("mittelwagen")
    public Long getMittelwagen() {
        return mittelwagen;
    }

    @Override
    @JsonSetter("mittelwagen")
    public void setMittelwagen(Long mittelwagen) {
        this.mittelwagen = mittelwagen;
    }

    @Override
    @Column(name = "sitzplatzetzkl1", nullable = true)
    @JsonGetter("sitzplatzeTZKL1")
    public Long getSitzPlatzeTZKL1() {
        return sitzplatzeTZKL1;
    }

    @Override
    @JsonSetter("sitzplatzeTZKL1")
    public void setSitzPlatzeTZKL1(Long sitzPlatzeTZKL1) {
        this.sitzplatzeTZKL1 = sitzPlatzeTZKL1;
    }

    @Override
    @Column(name = "sitzplatzetzkl2", nullable = true)
    @JsonGetter("sitzplatzeTzKL2")
    public Long getSitzPlatzeTzKL2() {
        return sitzplatzeTzKL2;
    }

    @Override
    @JsonSetter("sitzplatzeTzKL2")
    public void setSitzPlatzeTzKL2(Long sitzPlatzeTzKL2) {
        this.sitzplatzeTzKL2 = sitzPlatzeTzKL2;
    }

    @Override
    @Column(name = "drehgestellbauart", nullable = true, length = 100)
    @JsonGetter("drehgestellBauart")
    public String getDrehgestellBauart() {
        return drehgestellBauart;
    }

    @Override
    @JsonSetter("drehgestellBauart")
    public void setDrehgestellBauart(String drehgestellbauart) {
        this.drehgestellBauart = drehgestellbauart;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("Gattung", getGattung())
                .append("UnterKategorie", getUnterKategorie())
                .append("Hersteller", getHersteller())
                .append("bauzeit", getBauzeit())
                .append("Anzahl", getAnzahl())
                .append("BetriebsNummer", getBetriebsNummer())
                .append("Antrieb", getAntrieb())
                .append("Achsfolge", getAchsfolg())
                .append("Anfahrzugkraft", getAnfahrzugkraft())
                .append("Leistung", getLeistung())
                .append("Dienstgewicht", getDienstgewicht())
                .append("Geschwindigkeit", getGeschwindigkeit())
                .append("lange", getLange())
                .append("Ausserdienst", getAusserdienst())
                .append("DmTreibrad", getDmTreibrad())
                .append("DmLaufradVorn", getDmLaufradVorn())
                .append("DmLaufradHinten", getDmLaufradHinten())
                .append("Zylinder", getZylinder())
                .append("DmZylinder", getDmZylinder())
                .append("Kolbenhub", getKolbenhub())
                .append("Kesselueberdruck", getKesselueberdruck())
                .append("Rostflaeche", getRostflaeche())
                .append("Ueberhitzerflaeche", getUeberhitzerflaeche())
                .append("Wasservorrat", getWasservorrat())
                .append("Verdampfung", getVerdampfung())
                .append("Steuerung", getSteuerung())
                .append("Fahrmotoren", getFahrmotoren())
                .append("Motorbauart", getMotorbauart())
                .append("Leistungsuebertragung", getLeistungsuebertragung())
                .append("Reichweite", getReichweite())
                .append("Kapazitaet", getKapazitaet())
                .append("Klasse", getKlasse())
                .append("sitzplatzeKL1", getSitzPlatzeKL1())
                .append("sitzplatzeKL2", getSitzPlatzeKL2())
                .append("sitzplatzeKL3", getSitzPlatzeKL3())
                .append("sitzplatzeKL4", getSitzPlatzeKL4())
                .append("Aufbauten", getAufbauten())
                .append("TriebzugAnzeigen", getTriebzugAnzeigen())
                .append("Triebkoepfe", getTriebkoepfe())
                .append("Mittelwagen", getMittelwagen())
                .append("sitzplatzeTZKL1", getSitzPlatzeTZKL1())
                .append("sitzplatzeTzKL2", getSitzPlatzeTzKL2())
                .append("drehgestellBauart", getDrehgestellBauart())
                .toString();
    }
}