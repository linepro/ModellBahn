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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.AchsfolgResolver;
import com.linepro.modellbahn.rest.json.resolver.AntriebResolver;
import com.linepro.modellbahn.rest.json.resolver.GattungResolver;
import com.linepro.modellbahn.rest.json.resolver.SteuerungResolver;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Vorbild.
 * The prototype for a product
 * @author  $Author:$
 * @version $Id:$
 */
@Entity(name = "Vorbild")
@Table(name = "Vorbild", indexes = { @Index(columnList = DBNames.GATTUNG_ID, unique = true),
        @Index(columnList = DBNames.UNTER_KATEGORIE_ID),
        @Index(columnList = DBNames.ANTRIEB_ID),
        @Index(columnList = DBNames.ACHSFOLG_ID),
        @Index(columnList = DBNames.STEUERUNG_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.GATTUNG_ID }) })
@JsonRootName(value = ApiNames.VORBILD)
@JsonPropertyOrder({ApiNames.ID, ApiNames.GATTUNG, ApiNames.UNTER_KATEGORIE, ApiNames.HERSTELLER, ApiNames.BAUZEIT, ApiNames.ANZAHL, ApiNames.BETREIBSNUMMER, ApiNames.ANTRIEB, ApiNames.ACHSFOLG, ApiNames.ANFAHRZUGKRAFT, ApiNames.LEISTUNG, ApiNames.DIENSTGEWICHT, ApiNames.GESCHWINDIGKEIT, ApiNames.LANGE, ApiNames.AUSSERDIENST, ApiNames.DMTREIBRAD, ApiNames.DMLAUFRADVORN, ApiNames.DMLAUFRADHINTEN, ApiNames.ZYLINDER, ApiNames.DMZYLINDER, ApiNames.KOLBENHUB, ApiNames.KESSELUEBERDRUCK, ApiNames.ROSTFLAECHE, ApiNames.UEBERHITZERFLAECHE, ApiNames.WASSERVORRAT, ApiNames.VERDAMPFUNG, ApiNames.STEUERUNG, ApiNames.FAHRMOTOREN, ApiNames.MOTORBAUART, ApiNames.LEISTUNGSUEBERTRAGUNG, ApiNames.REICHWEITE, ApiNames.KAPAZITAT, ApiNames.KLASSE, ApiNames.SITZPLATZEKL1, ApiNames.SITZPLATZEKL2, ApiNames.SITZPLATZEKL3, ApiNames.SITZPLATZEKL4, ApiNames.AUFBAU, ApiNames.TRIEBZUGANZEIGEN, ApiNames.TRIEBKOEPFE, ApiNames.MITTELWAGEN, ApiNames.SITZPLATZETZKL1, ApiNames.SITZPLATZETZKL2, ApiNames.DREHGESTELLBAUART, ApiNames.DELETED, ApiNames.LINKS})
public class Vorbild extends AbstractNamedItem<NameKey> implements IVorbild {

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

    /** The betreibs nummer. */
    private String betreibsNummer;

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
    private BigDecimal kapazitat;

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
    private String aufbau;

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
     * @param anmerkung 
     */
    public Vorbild(Long id, IGattung gattung, IUnterKategorie unterKategorie, String hersteller, Date bauzeit,
            Integer anzahl, String betreibsNummer, IAntrieb antrieb, IAchsfolg achsfolg, BigDecimal anfahrzugkraft,
            BigDecimal leistung, BigDecimal dienstgewicht, Long geschwindigkeit, BigDecimal lange, Date ausserdienst,
            BigDecimal dmTreibrad, BigDecimal dmLaufradVorn, BigDecimal dmLaufradHinten, Integer zylinder, BigDecimal dmZylinder,
            BigDecimal kolbenhub, BigDecimal kesselueberdruck, BigDecimal rostflaeche, BigDecimal ueberhitzerflaeche, BigDecimal wasservorrat,
            BigDecimal verdampfung, ISteuerung steuerung, Integer fahrmotoren, String motorbauart, BigDecimal leistungsuebertragung, BigDecimal reichweite, BigDecimal kapazitaet, Integer klasse, Long sitzPlatzeKL1,
            Long sitzPlatzeKL2, Long sitzPlatzeKL3, Long sitzPlatzeKL4, String aufbauten, Boolean triebzugAnzeigen,
            Long triebkoepfe, Long mittelwagen, Long sitzPlatzeTZKL1, Long sitzPlatzeTzKL2,
            String drehgestellbauart, Boolean deleted, String anmerkung) {
        super(id, gattung.getName(), anmerkung, deleted);

        setGattung(gattung);
        setUnterKategorie(unterKategorie);
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
        setKapazitat(kapazitaet);
        setKlasse(klasse);
        setSitzPlatzeKL1(sitzPlatzeKL1);
        setSitzPlatzeKL2(sitzPlatzeKL2);
        setSitzPlatzeKL3(sitzPlatzeKL3);
        setSitzPlatzeKL4(sitzPlatzeKL4);
        setAufbau(aufbauten);
        setTriebzugAnzeigen(triebzugAnzeigen);
        setTriebkoepfe(triebkoepfe);
        setMittelwagen(mittelwagen);
        setSitzPlatzeTZKL1(sitzPlatzeTZKL1);
        setSitzPlatzeTzKL2(sitzPlatzeTzKL2);
        setDrehgestellBauart(drehgestellbauart);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "vorbild_fk1"))
    @JsonGetter(ApiNames.GATTUNG)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=GattungResolver.class)
    public IGattung getGattung() {
        return gattung;
    }

    @Override
    @JsonSetter(ApiNames.GATTUNG)
    @JsonDeserialize(as=Gattung.class)
    public void setGattung(IGattung gattung) {
        this.gattung = gattung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "vorbild_fk2"))
    @JsonGetter(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(using=UnterKategorieSerializer.class)
    public IUnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    @Override
    @JsonSetter(ApiNames.UNTER_KATEGORIE)
    @JsonDeserialize(as=UnterKategorie.class)
    public void setUnterKategorie(IUnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    @Override
    @Column(name = DBNames.HERSTELLER, nullable = true, length = 100)
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    public String getHersteller() {
        return hersteller;
    }

    @Override
    @JsonSetter(ApiNames.HERSTELLER)
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @Column(name = DBNames.BAUZEIT, nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter(ApiNames.BAUZEIT)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape=Shape.STRING, pattern=Formats.ISO8601_DATE)
    public Date getBauzeit() {
        return bauzeit;
    }

    @Override
    @JsonSetter(ApiNames.BAUZEIT)
    public void setBauzeit(Date bauzeit) {
        this.bauzeit = bauzeit;
    }

    @Override
    @Column(name = DBNames.ANZAHL, nullable = true)
    @JsonGetter(ApiNames.ANZAHL)
    @JsonView(Views.Public.class)
    public Integer getAnzahl() {
        return anzahl;
    }

    @Override
    @JsonSetter(ApiNames.ANZAHL)
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    @Column(name = DBNames.BETREIBSNUMMER, nullable = true, length = 100)
    @JsonGetter(ApiNames.BETREIBSNUMMER)
    @JsonView(Views.DropDown.class)
    public String getBetreibsNummer() {
        return betreibsNummer;
    }

    @Override
    @JsonSetter(ApiNames.BETREIBSNUMMER)
    public void setBetreibsNummer(String betreibsNummer) {
        this.betreibsNummer = betreibsNummer;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Antrieb.class)
    @JoinColumn(name = DBNames.ANTRIEB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "vorbild_fk3"))
    @JsonGetter(ApiNames.ANTRIEB)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=AntriebResolver.class)
    public IAntrieb getAntrieb() {
        return antrieb;
    }

    @Override
    @JsonSetter(ApiNames.ANTRIEB)
    @JsonDeserialize(as=Antrieb.class)
    public void setAntrieb(IAntrieb antrieb) {
        this.antrieb = antrieb;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "vorbild_fk4"))
    @JsonGetter(ApiNames.ACHSFOLG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=AchsfolgResolver.class)
    public IAchsfolg getAchsfolg() {
        return achsfolg;
    }

    @Override
    @JsonSetter(ApiNames.ACHSFOLG)
    @JsonDeserialize(as=Achsfolg.class)
    public void setAchsfolg(IAchsfolg achsfolg) {
        this.achsfolg = achsfolg;
    }

    @Override
    @Column(name = DBNames.ANFAHRZUGKRAFT, nullable = true)
    @JsonGetter(ApiNames.ANFAHRZUGKRAFT)
    @JsonView(Views.Public.class)
    public BigDecimal getAnfahrzugkraft() {
        return anfahrzugkraft;
    }

    @Override
    @JsonSetter(ApiNames.ANFAHRZUGKRAFT)
    public void setAnfahrzugkraft(BigDecimal anfahrzugkraft) {
        this.anfahrzugkraft = anfahrzugkraft;
    }

    @Override
    @Column(name = DBNames.LEISTUNG, nullable = true)
    @JsonGetter(ApiNames.LEISTUNG)
    @JsonView(Views.Public.class)
    public BigDecimal getLeistung() {
        return leistung;
    }

    @Override
    @JsonSetter(ApiNames.LEISTUNG)
    public void setLeistung(BigDecimal leistung) {
        this.leistung = leistung;
    }

    @Override
    @Column(name = DBNames.DIENSTGEWICHT, nullable = true)
    @JsonGetter(ApiNames.DIENSTGEWICHT)
    @JsonView(Views.Public.class)
    public BigDecimal getDienstgewicht() {
        return dienstgewicht;
    }

    @Override
    @JsonSetter(ApiNames.DIENSTGEWICHT)
    public void setDienstgewicht(BigDecimal dienstgewicht) {
        this.dienstgewicht = dienstgewicht;
    }

    @Override
    @Column(name = DBNames.GESCHWINDIGKEIT, nullable = true)
    @JsonGetter(ApiNames.GESCHWINDIGKEIT)
    @JsonView(Views.Public.class)
    public Long getGeschwindigkeit() {
        return geschwindigkeit;
    }

    @Override
    @JsonSetter(ApiNames.GESCHWINDIGKEIT)
    public void setGeschwindigkeit(Long geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    @Override
    @Column(name = DBNames.LANGE, nullable = true)
    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.Public.class)
    public BigDecimal getLange() {
        return lange;
    }

    @Override
    @JsonSetter(ApiNames.LANGE)
    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    @Override
    @Column(name = DBNames.AUSSERDIENST, nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonGetter(ApiNames.AUSSERDIENST)
    @JsonView(Views.DropDown.class)
    @JsonFormat(shape=Shape.STRING, pattern=Formats.ISO8601_DATE)
    public Date getAusserdienst() {
        return ausserdienst;
    }

    @Override
    @JsonSetter(ApiNames.AUSSERDIENST)
    public void setAusserdienst(Date ausserdienst) {
        this.ausserdienst = ausserdienst;
    }

    @Override
    @Column(name = DBNames.DMTREIBRAD, nullable = true)
    @JsonGetter(ApiNames.DMTREIBRAD)
    @JsonView(Views.Public.class)
    public BigDecimal getDmTreibrad() {
        return dmTreibrad;
    }

    @Override
    @JsonSetter(ApiNames.DMTREIBRAD)
    public void setDmTreibrad(BigDecimal dmTreibrad) {
        this.dmTreibrad = dmTreibrad;
    }

    @Override
    @Column(name = DBNames.DMLAUFRADVORN, nullable = true)
    @JsonGetter(ApiNames.DMLAUFRADVORN)
    @JsonView(Views.Public.class)
    public BigDecimal getDmLaufradVorn() {
        return dmLaufradVorn;
    }

    @Override
    @JsonSetter(ApiNames.DMLAUFRADVORN)
    public void setDmLaufradVorn(BigDecimal dmLaufradVorn) {
        this.dmLaufradVorn = dmLaufradVorn;
    }

    @Override
    @Column(name = DBNames.DMLAUFRADHINTEN, nullable = true)
    @JsonGetter(ApiNames.DMLAUFRADHINTEN)
    @JsonView(Views.Public.class)
    public BigDecimal getDmLaufradHinten() {
        return dmLaufradHinten;
    }

    @Override
    @JsonSetter(ApiNames.DMLAUFRADHINTEN)
    public void setDmLaufradHinten(BigDecimal dmLaufradHinten) {
        this.dmLaufradHinten = dmLaufradHinten;
    }

    @Override
    @Column(name = DBNames.ZYLINDER, nullable = true)
    @JsonGetter(ApiNames.ZYLINDER)
    @JsonView(Views.Public.class)
    public Integer getZylinder() {
        return zylinder;
    }

    @Override
    @JsonSetter(ApiNames.ZYLINDER)
    public void setZylinder(Integer zylinder) {
        this.zylinder = zylinder;
    }

    @Override
    @Column(name = DBNames.DMZYLINDER, nullable = true)
    @JsonGetter(ApiNames.DMZYLINDER)
    @JsonView(Views.Public.class)
    public BigDecimal getDmZylinder() {
        return dmZylinder;
    }

    @Override
    @JsonSetter(ApiNames.DMZYLINDER)
    public void setDmZylinder(BigDecimal dmZylinder) {
        this.dmZylinder = dmZylinder;
    }

    @Override
    @Column(name = DBNames.KOLBENHUB, nullable = true)
    @JsonGetter(ApiNames.KOLBENHUB)
    @JsonView(Views.Public.class)
    public BigDecimal getKolbenhub() {
        return kolbenhub;
    }

    @Override
    @JsonSetter(ApiNames.KOLBENHUB)
    public void setKolbenhub(BigDecimal kolbenhub) {
        this.kolbenhub = kolbenhub;
    }

    @Override
    @Column(name = DBNames.KESSELUEBERDRUCK, nullable = true)
    @JsonGetter(ApiNames.KESSELUEBERDRUCK)
    @JsonView(Views.Public.class)
    public BigDecimal getKesselueberdruck() {
        return kesselueberdruck;
    }

    @Override
    @JsonSetter(ApiNames.KESSELUEBERDRUCK)
    public void setKesselueberdruck(BigDecimal kesselueberdruck) {
        this.kesselueberdruck = kesselueberdruck;
    }

    @Override
    @Column(name = DBNames.ROSTFLAECHE, nullable = true)
    @JsonGetter(ApiNames.ROSTFLAECHE)
    @JsonView(Views.Public.class)
    public BigDecimal getRostflaeche() {
        return rostflaeche;
    }

    @Override
    @JsonSetter(ApiNames.ROSTFLAECHE)
    public void setRostflaeche(BigDecimal rostflaeche) {
        this.rostflaeche = rostflaeche;
    }

    @Override
    @Column(name = DBNames.UEBERHITZERFLAECHE, nullable = true)
    @JsonGetter(ApiNames.UEBERHITZERFLAECHE)
    @JsonView(Views.Public.class)
    public BigDecimal getUeberhitzerflaeche() {
        return ueberhitzerflaeche;
    }

    @Override
    @JsonSetter(ApiNames.UEBERHITZERFLAECHE)
    public void setUeberhitzerflaeche(BigDecimal ueberhitzerflaeche) {
        this.ueberhitzerflaeche = ueberhitzerflaeche;
    }

    @Override
    @Column(name = DBNames.WASSERVORRAT, nullable = true)
    @JsonGetter(ApiNames.WASSERVORRAT)
    @JsonView(Views.Public.class)
    public BigDecimal getWasservorrat() {
        return wasservorrat;
    }

    @Override
    @JsonSetter(ApiNames.WASSERVORRAT)
    public void setWasservorrat(BigDecimal wasservorrat) {
        this.wasservorrat = wasservorrat;
    }

    @Column(name = DBNames.VERDAMPFUNG, nullable = true)
    @JsonGetter(ApiNames.VERDAMPFUNG)
    @JsonView(Views.Public.class)
    public BigDecimal getVerdampfung() {
        return verdampfung;
    }

    @Override
    @JsonSetter(ApiNames.VERDAMPFUNG)
    public void setVerdampfung(BigDecimal verdampfung) {
        this.verdampfung = verdampfung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = DBNames.STEUERUNG_ID, nullable = true, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "vorbild_fk5"))
    @JsonGetter(ApiNames.STEUERUNG)
    @JsonView(Views.Public.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=SteuerungResolver.class)
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    @JsonSetter(ApiNames.STEUERUNG)
    @JsonDeserialize(as=Steuerung.class)
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @Column(name = DBNames.FAHRMOTOREN, nullable = true)
    @JsonGetter(ApiNames.FAHRMOTOREN)
    @JsonView(Views.Public.class)
    public Integer getFahrmotoren() {
        return fahrmotoren;
    }

    @Override
    @JsonSetter(ApiNames.FAHRMOTOREN)
    public void setFahrmotoren(Integer fahrmotoren) {
        this.fahrmotoren = fahrmotoren;
    }

    @Override
    @Column(name = DBNames.MOTORBAUART, nullable = true, length = 100)
    @JsonGetter(ApiNames.MOTORBAUART)
    @JsonView(Views.Public.class)
    public String getMotorbauart() {
        return motorbauart;
    }

    @Override
    @JsonSetter(ApiNames.MOTORBAUART)
    public void setMotorbauart(String motorbauart) {
        this.motorbauart = motorbauart;
    }

    @Override
    @Column(name = DBNames.LEISTUNGSUEBERTRAGUNG, nullable = true)
    @JsonGetter(ApiNames.LEISTUNGSUEBERTRAGUNG)
    @JsonView(Views.Public.class)
    public BigDecimal getLeistungsuebertragung() {
        return leistungsuebertragung;
    }

    @Override
    @JsonSetter(ApiNames.LEISTUNGSUEBERTRAGUNG)
    public void setLeistungsuebertragung(BigDecimal leistungsuebertragung) {
        this.leistungsuebertragung = leistungsuebertragung;
    }

    @Override
    @Column(name = DBNames.REICHWEITE, nullable = true)
    @JsonGetter(ApiNames.REICHWEITE)
    @JsonView(Views.Public.class)
    public BigDecimal getReichweite() {
        return reichweite;
    }

    @Override
    @JsonSetter(ApiNames.REICHWEITE)
    public void setReichweite(BigDecimal reichweite) {
        this.reichweite = reichweite;
    }

    @Override
    @Column(name = DBNames.KAPAZITAT, nullable = true)
    @JsonGetter(ApiNames.KAPAZITAT)
    @JsonView(Views.Public.class)
    public BigDecimal getKapazitat() {
        return kapazitat;
    }

    @Override
    @JsonSetter(ApiNames.KAPAZITAT)
    public void setKapazitat(BigDecimal kapazitat) {
        this.kapazitat = kapazitat;
    }

    @Override
    @Column(name = DBNames.KLASSE, nullable = true)
    @JsonGetter(ApiNames.KLASSE)
    @JsonView(Views.Public.class)
    public Integer getKlasse() {
        return klasse;
    }

    @Override
    @JsonSetter(ApiNames.KLASSE)
    public void setKlasse(Integer klasse) {
        this.klasse = klasse;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL1, nullable = true)
    @JsonGetter(ApiNames.SITZPLATZEKL1)
    @JsonView(Views.Public.class)
    public Long getSitzPlatzeKL1() {
        return sitzplatzeKL1;
    }

    @Override
    @JsonSetter(ApiNames.SITZPLATZEKL1)
    public void setSitzPlatzeKL1(Long sitzPlatzeKL1) {
        this.sitzplatzeKL1 = sitzPlatzeKL1;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL2, nullable = true)
    @JsonGetter(ApiNames.SITZPLATZEKL2)
    @JsonView(Views.Public.class)
    public Long getSitzPlatzeKL2() {
        return sitzplatzeKL2;
    }

    @Override
    @JsonSetter(ApiNames.SITZPLATZEKL2)
    public void setSitzPlatzeKL2(Long sitzPlatzeKL2) {
        sitzplatzeKL2 = sitzPlatzeKL2;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL3, nullable = true)
    @JsonGetter(ApiNames.SITZPLATZEKL3)
    @JsonView(Views.Public.class)
    public Long getSitzPlatzeKL3() {
        return sitzplatzeKL3;
    }

    @Override
    @JsonSetter(ApiNames.SITZPLATZEKL3)
    public void setSitzPlatzeKL3(Long sitzPlatzeKL3) {
        sitzplatzeKL3 = sitzPlatzeKL3;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL4, nullable = true)
    @JsonGetter(ApiNames.SITZPLATZEKL4)
    @JsonView(Views.Public.class)
    public Long getSitzPlatzeKL4() {
        return sitzplatzeKL4;
    }

    @Override
    @JsonSetter(ApiNames.SITZPLATZEKL4)
    public void setSitzPlatzeKL4(Long sitzPlatzeKL4) {
        this.sitzplatzeKL4 = sitzPlatzeKL4;
    }

    @Override
    @Column(name = DBNames.AUFBAU, nullable = true, length = 100)
    @JsonGetter(ApiNames.AUFBAU)
    @JsonView(Views.Public.class)
    public String getAufbau() {
        return aufbau;
    }

    @Override
    @JsonSetter(ApiNames.AUFBAU)
    public void setAufbau(String aufbau) {
        this.aufbau = aufbau;
    }

    @Override
    @Column(name = DBNames.TRIEBZUGANZEIGEN, nullable = true)
    @JsonGetter(ApiNames.TRIEBZUGANZEIGEN)
    @JsonView(Views.Public.class)
    public Boolean getTriebzugAnzeigen() {
        return triebzugAnzeigen;
    }

    @Override
    @JsonSetter(ApiNames.TRIEBZUGANZEIGEN)
    public void setTriebzugAnzeigen(Boolean triebzugAnzeigen) {
        this.triebzugAnzeigen = triebzugAnzeigen;
    }

    @Override
    @Column(name = DBNames.TRIEBKOEPFE, nullable = true)
    @JsonGetter(ApiNames.TRIEBKOEPFE)
    @JsonView(Views.Public.class)
    public Long getTriebkoepfe() {
        return triebkoepfe;
    }

    @Override
    @JsonSetter(ApiNames.TRIEBKOEPFE)
    public void setTriebkoepfe(Long triebkoepfe) {
        this.triebkoepfe = triebkoepfe;
    }

    @Override
    @Column(name = DBNames.MITTELWAGEN, nullable = true)
    @JsonGetter(ApiNames.MITTELWAGEN)
    @JsonView(Views.Public.class)
    public Long getMittelwagen() {
        return mittelwagen;
    }

    @Override
    @JsonSetter(ApiNames.MITTELWAGEN)
    public void setMittelwagen(Long mittelwagen) {
        this.mittelwagen = mittelwagen;
    }

    @Override
    @Column(name = DBNames.SITZPLATZETZKL1, nullable = true)
    @JsonGetter(ApiNames.SITZPLATZETZKL1)
    @JsonView(Views.Public.class)
    public Long getSitzPlatzeTZKL1() {
        return sitzplatzeTZKL1;
    }

    @Override
    @JsonSetter(ApiNames.SITZPLATZETZKL1)
    public void setSitzPlatzeTZKL1(Long sitzPlatzeTZKL1) {
        this.sitzplatzeTZKL1 = sitzPlatzeTZKL1;
    }

    @Override
    @Column(name = DBNames.SITZPLATZETZKL2, nullable = true)
    @JsonGetter(ApiNames.SITZPLATZETZKL2)
    @JsonView(Views.Public.class)
    public Long getSitzPlatzeTzKL2() {
        return sitzplatzeTzKL2;
    }

    @Override
    @JsonSetter(ApiNames.SITZPLATZETZKL2)
    public void setSitzPlatzeTzKL2(Long sitzPlatzeTzKL2) {
        this.sitzplatzeTzKL2 = sitzPlatzeTzKL2;
    }

    @Override
    @Column(name = DBNames.DREHGESTELLBAUART, nullable = true, length = 100)
    @JsonGetter(ApiNames.DREHGESTELLBAUART)
    @JsonView(Views.Public.class)
    public String getDrehgestellBauart() {
        return drehgestellBauart;
    }

    @Override
    @JsonSetter(ApiNames.DREHGESTELLBAUART)
    public void setDrehgestellBauart(String drehgestellbauart) {
        this.drehgestellBauart = drehgestellbauart;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.GATTUNG, getGattung())
                .append(ApiNames.UNTER_KATEGORIE, getUnterKategorie())
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
                .append(ApiNames.KESSELUEBERDRUCK, getKesselueberdruck())
                .append(ApiNames.ROSTFLAECHE, getRostflaeche())
                .append(ApiNames.UEBERHITZERFLAECHE, getUeberhitzerflaeche())
                .append(ApiNames.WASSERVORRAT, getWasservorrat())
                .append(ApiNames.VERDAMPFUNG, getVerdampfung())
                .append(ApiNames.STEUERUNG, getSteuerung())
                .append(ApiNames.FAHRMOTOREN, getFahrmotoren())
                .append(ApiNames.MOTORBAUART, getMotorbauart())
                .append(ApiNames.LEISTUNGSUEBERTRAGUNG, getLeistungsuebertragung())
                .append(ApiNames.REICHWEITE, getReichweite())
                .append(ApiNames.KAPAZITAT, getKapazitat())
                .append(ApiNames.KLASSE, getKlasse())
                .append(ApiNames.SITZPLATZEKL1, getSitzPlatzeKL1())
                .append(ApiNames.SITZPLATZEKL2, getSitzPlatzeKL2())
                .append(ApiNames.SITZPLATZEKL3, getSitzPlatzeKL3())
                .append(ApiNames.SITZPLATZEKL4, getSitzPlatzeKL4())
                .append(ApiNames.AUFBAU, getAufbau())
                .append(ApiNames.TRIEBZUGANZEIGEN, getTriebzugAnzeigen())
                .append(ApiNames.TRIEBKOEPFE, getTriebkoepfe())
                .append(ApiNames.MITTELWAGEN, getMittelwagen())
                .append(ApiNames.SITZPLATZETZKL1, getSitzPlatzeTZKL1())
                .append(ApiNames.SITZPLATZETZKL2, getSitzPlatzeTzKL2())
                .append(ApiNames.DREHGESTELLBAUART, getDrehgestellBauart())
                .toString();
    }
}