package com.linepro.modellbahn.model.impl;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.rest.json.Formats;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.AchsfolgResolver;
import com.linepro.modellbahn.rest.json.resolver.AntriebResolver;
import com.linepro.modellbahn.rest.json.resolver.BahnverwaltungResolver;
import com.linepro.modellbahn.rest.json.resolver.GattungResolver;
import com.linepro.modellbahn.rest.json.serialization.PathSerializer;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import io.swagger.annotations.ApiModelProperty;

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
public class Vorbild extends AbstractNamedItem<NameKey> implements IVorbild {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4852882107081643608L;

    /** The gattung. */
    private IGattung gattung;

    /** The unter kategorie. */
    @NotNull
    private IUnterKategorie unterKategorie;

    /** The bahnverwaltung */
    private IBahnverwaltung bahnverwaltung;
    
    /** The hersteller. */
    private String hersteller;

    /** The bauzeit. */
    private Date bauzeit;

    /** The anzahl. */
    @Positive
    private Integer anzahl;

    /** The betreibs nummer. */
    private String betreibsNummer;

    /** The antrieb. */
    private IAntrieb antrieb;

    /** The achsfolg. */
    private IAchsfolg achsfolg;

    /** The anfahrzugkraft. */
    @Positive
    private BigDecimal anfahrzugkraft;

    /** The leistung. */
    @Positive
    private BigDecimal leistung;

    /** The dienstgewicht. */
    @Positive
    private BigDecimal dienstgewicht;

    /** The geschwindigkeit. */
    @Positive
    private Integer geschwindigkeit;

    /** The lange. */
    @Positive
    private BigDecimal lange;

    /** The ausserdienst. */
    private Date ausserdienst;

    /** The dm treibrad. */
    @Positive
    private BigDecimal dmTreibrad;

    /** The dm laufrad vorn. */
    @Positive
    private BigDecimal dmLaufradVorn;

    /** The dm laufrad hinten. */
    @Positive
    private BigDecimal dmLaufradHinten;

    /** The zylinder. */
    @Positive
    private Integer zylinder;

    /** The dm zylinder. */
    @Positive
    private BigDecimal dmZylinder;

    /** The kolbenhub. */
    @Positive
    private BigDecimal kolbenhub;

    /** The kesselueberdruck. */
    @Positive
    private BigDecimal kesselueberdruck;

    /** The rostflaeche. */
    @Positive
    private BigDecimal rostflaeche;

    /** The ueberhitzerflaeche. */
    @Positive
    private BigDecimal ueberhitzerflaeche;

    /** The wasservorrat. */
    @Positive
    private BigDecimal wasservorrat;

    /** The verdampfung. */
    @Positive
    private BigDecimal verdampfung;

    /** The fahrmotoren. */
    @Positive
    private Integer fahrmotoren;

    /** The motorbauart. */
    private String motorbauart;

    /** The leistungsuebertragung. */
    @Positive
    private BigDecimal leistungsuebertragung;

    /** The reichweite. */
    @Positive
    private BigDecimal reichweite;

    /** The kapazitaet. */
    @Positive
    private BigDecimal kapazitat;

    /** The klasse. */
    @Positive
    private Integer klasse;

    /** The sitzplatze KL 1. */
    @Positive
    private Integer sitzplatzeKL1;

    /** The sitzplatze KL 2. */
    @Positive
    private Integer sitzplatzeKL2;

    /** The sitzplatze KL 3. */
    @Positive
    private Integer sitzplatzeKL3;

    /** The sitzplatze KL 4. */
    @Positive
    private Integer sitzplatzeKL4;

    /** The aufbauten. */
    private String aufbau;

    /** The triebzug anzeigen. */
    private Boolean triebzugAnzeigen;

    /** The triebkoepfe. */
    @Positive
    private Integer triebkoepfe;

    /** The mittelwagen. */
    @Positive
    private Integer mittelwagen;

    /** The sitzplatze TZKL 1. */
    @Positive
    private Integer sitzplatzeTZKL1;

    /** The sitzplatze tz KL 2. */
    @Positive
    private Integer sitzplatzeTzKL2;

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
     * @param kesselueberdruck the kesselueberdruck
     * @param rostflaeche the rostflaeche
     * @param ueberhitzerflaeche the ueberhitzerflaeche
     * @param wasservorrat the wasservorrat
     * @param verdampfung the verdampfung
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
     * @param anmerkung remarks
     */
    public Vorbild(Long id, IGattung gattung, IUnterKategorie unterKategorie, IBahnverwaltung bahnverwaltung, String hersteller, Date bauzeit,
            Integer anzahl, String betreibsNummer, IAntrieb antrieb, IAchsfolg achsfolg, BigDecimal anfahrzugkraft,
            BigDecimal leistung, BigDecimal dienstgewicht, Integer geschwindigkeit, BigDecimal lange, Date ausserdienst,
            BigDecimal dmTreibrad, BigDecimal dmLaufradVorn, BigDecimal dmLaufradHinten, Integer zylinder, BigDecimal dmZylinder,
            BigDecimal kolbenhub, BigDecimal kesselueberdruck, BigDecimal rostflaeche, BigDecimal ueberhitzerflaeche, BigDecimal wasservorrat,
            BigDecimal verdampfung, Integer fahrmotoren, String motorbauart, BigDecimal leistungsuebertragung, BigDecimal reichweite, BigDecimal kapazitaet, Integer klasse, Integer sitzPlatzeKL1,
            Integer sitzPlatzeKL2, Integer sitzPlatzeKL3, Integer sitzPlatzeKL4, String aufbauten, Boolean triebzugAnzeigen,
            Integer triebkoepfe, Integer mittelwagen, Integer sitzPlatzeTZKL1, Integer sitzPlatzeTzKL2,
            String drehgestellbauart, String anmerkung, Boolean deleted) {
        super(id, gattung.getName(), anmerkung, deleted);

        setGattung(gattung);
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
        setKesselueberdruck(kesselueberdruck);
        setRostflaeche(rostflaeche);
        setUeberhitzerflaeche(ueberhitzerflaeche);
        setWasservorrat(wasservorrat);
        setVerdampfung(verdampfung);
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
        setAbbildung(abbildung);
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk1"))




    public IGattung getGattung() {
        return gattung;
    }

    @Override


    public void setGattung(IGattung gattung) {
        this.gattung = gattung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk2"))



    public IUnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    @Override


    public void setUnterKategorie(IUnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk3"))




    public IBahnverwaltung getBahnverwaltung() {
        return bahnverwaltung;
    }

    @Override


    public void setBahnverwaltung(IBahnverwaltung bahnverwaltung) {
        this.bahnverwaltung = bahnverwaltung;
    }

    @Override
    @Column(name = DBNames.HERSTELLER, length = 100)


    public String getHersteller() {
        return hersteller;
    }

    @Override

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @Column(name = DBNames.BAUZEIT)
    @Temporal(TemporalType.DATE)



    public Date getBauzeit() {
        return bauzeit;
    }

    @Override

    public void setBauzeit(Date bauzeit) {
        this.bauzeit = bauzeit;
    }

    @Override
    @Column(name = DBNames.ANZAHL)


    public Integer getAnzahl() {
        return anzahl;
    }

    @Override

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    @Column(name = DBNames.BETREIBSNUMMER, length = 100)


    public String getBetreibsNummer() {
        return betreibsNummer;
    }

    @Override

    public void setBetreibsNummer(String betreibsNummer) {
        this.betreibsNummer = betreibsNummer;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Antrieb.class)
    @JoinColumn(name = DBNames.ANTRIEB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk4"))




    public IAntrieb getAntrieb() {
        return antrieb;
    }

    @Override


    public void setAntrieb(IAntrieb antrieb) {
        this.antrieb = antrieb;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.VORBILD + "_fk5"))




    public IAchsfolg getAchsfolg() {
        return achsfolg;
    }

    @Override


    public void setAchsfolg(IAchsfolg achsfolg) {
        this.achsfolg = achsfolg;
    }

    @Override
    @Column(name = DBNames.ANFAHRZUGKRAFT)


    public BigDecimal getAnfahrzugkraft() {
        return anfahrzugkraft;
    }

    @Override

    public void setAnfahrzugkraft(BigDecimal anfahrzugkraft) {
        this.anfahrzugkraft = anfahrzugkraft;
    }

    @Override
    @Column(name = DBNames.LEISTUNG)


    public BigDecimal getLeistung() {
        return leistung;
    }

    @Override

    public void setLeistung(BigDecimal leistung) {
        this.leistung = leistung;
    }

    @Override
    @Column(name = DBNames.DIENSTGEWICHT)


    public BigDecimal getDienstgewicht() {
        return dienstgewicht;
    }

    @Override

    public void setDienstgewicht(BigDecimal dienstgewicht) {
        this.dienstgewicht = dienstgewicht;
    }

    @Override
    @Column(name = DBNames.GESCHWINDIGKEIT)


    public Integer getGeschwindigkeit() {
        return geschwindigkeit;
    }

    @Override

    public void setGeschwindigkeit(Integer geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    @Override
    @Column(name = DBNames.LANGE)


    public BigDecimal getLange() {
        return lange;
    }

    @Override

    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    @Override
    @Column(name = DBNames.AUSSERDIENST)
    @Temporal(TemporalType.DATE)



    public Date getAusserdienst() {
        return ausserdienst;
    }

    @Override

    public void setAusserdienst(Date ausserdienst) {
        this.ausserdienst = ausserdienst;
    }

    @Override
    @Column(name = DBNames.DMTREIBRAD)


    public BigDecimal getDmTreibrad() {
        return dmTreibrad;
    }

    @Override

    public void setDmTreibrad(BigDecimal dmTreibrad) {
        this.dmTreibrad = dmTreibrad;
    }

    @Override
    @Column(name = DBNames.DMLAUFRADVORN)


    public BigDecimal getDmLaufradVorn() {
        return dmLaufradVorn;
    }

    @Override

    public void setDmLaufradVorn(BigDecimal dmLaufradVorn) {
        this.dmLaufradVorn = dmLaufradVorn;
    }

    @Override
    @Column(name = DBNames.DMLAUFRADHINTEN)


    public BigDecimal getDmLaufradHinten() {
        return dmLaufradHinten;
    }

    @Override

    public void setDmLaufradHinten(BigDecimal dmLaufradHinten) {
        this.dmLaufradHinten = dmLaufradHinten;
    }

    @Override
    @Column(name = DBNames.ZYLINDER)


    public Integer getZylinder() {
        return zylinder;
    }

    @Override

    public void setZylinder(Integer zylinder) {
        this.zylinder = zylinder;
    }

    @Override
    @Column(name = DBNames.DMZYLINDER)


    public BigDecimal getDmZylinder() {
        return dmZylinder;
    }

    @Override

    public void setDmZylinder(BigDecimal dmZylinder) {
        this.dmZylinder = dmZylinder;
    }

    @Override
    @Column(name = DBNames.KOLBENHUB)


    public BigDecimal getKolbenhub() {
        return kolbenhub;
    }

    @Override

    public void setKolbenhub(BigDecimal kolbenhub) {
        this.kolbenhub = kolbenhub;
    }

    @Override
    @Column(name = DBNames.KESSELUEBERDRUCK)


    public BigDecimal getKesselueberdruck() {
        return kesselueberdruck;
    }

    @Override

    public void setKesselueberdruck(BigDecimal kesselueberdruck) {
        this.kesselueberdruck = kesselueberdruck;
    }

    @Override
    @Column(name = DBNames.ROSTFLAECHE)


    public BigDecimal getRostflaeche() {
        return rostflaeche;
    }

    @Override

    public void setRostflaeche(BigDecimal rostflaeche) {
        this.rostflaeche = rostflaeche;
    }

    @Override
    @Column(name = DBNames.UEBERHITZERFLAECHE)


    public BigDecimal getUeberhitzerflaeche() {
        return ueberhitzerflaeche;
    }

    @Override

    public void setUeberhitzerflaeche(BigDecimal ueberhitzerflaeche) {
        this.ueberhitzerflaeche = ueberhitzerflaeche;
    }

    @Override
    @Column(name = DBNames.WASSERVORRAT)


    public BigDecimal getWasservorrat() {
        return wasservorrat;
    }

    @Override

    public void setWasservorrat(BigDecimal wasservorrat) {
        this.wasservorrat = wasservorrat;
    }

    @Column(name = DBNames.VERDAMPFUNG)


    public BigDecimal getVerdampfung() {
        return verdampfung;
    }

    @Override

    public void setVerdampfung(BigDecimal verdampfung) {
        this.verdampfung = verdampfung;
    }

    @Override
    @Column(name = DBNames.FAHRMOTOREN)


    public Integer getFahrmotoren() {
        return fahrmotoren;
    }

    @Override

    public void setFahrmotoren(Integer fahrmotoren) {
        this.fahrmotoren = fahrmotoren;
    }

    @Override
    @Column(name = DBNames.MOTORBAUART, length = 100)


    public String getMotorbauart() {
        return motorbauart;
    }

    @Override

    public void setMotorbauart(String motorbauart) {
        this.motorbauart = motorbauart;
    }

    @Override
    @Column(name = DBNames.LEISTUNGSUEBERTRAGUNG)


    public BigDecimal getLeistungsuebertragung() {
        return leistungsuebertragung;
    }

    @Override

    public void setLeistungsuebertragung(BigDecimal leistungsuebertragung) {
        this.leistungsuebertragung = leistungsuebertragung;
    }

    @Override
    @Column(name = DBNames.REICHWEITE)


    public BigDecimal getReichweite() {
        return reichweite;
    }

    @Override

    public void setReichweite(BigDecimal reichweite) {
        this.reichweite = reichweite;
    }

    @Override
    @Column(name = DBNames.KAPAZITAT)


    public BigDecimal getKapazitat() {
        return kapazitat;
    }

    @Override

    public void setKapazitat(BigDecimal kapazitat) {
        this.kapazitat = kapazitat;
    }

    @Override
    @Column(name = DBNames.KLASSE)


    public Integer getKlasse() {
        return klasse;
    }

    @Override

    public void setKlasse(Integer klasse) {
        this.klasse = klasse;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL1)


    public Integer getSitzPlatzeKL1() {
        return sitzplatzeKL1;
    }

    @Override

    public void setSitzPlatzeKL1(Integer sitzPlatzeKL1) {
        this.sitzplatzeKL1 = sitzPlatzeKL1;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL2)


    public Integer getSitzPlatzeKL2() {
        return sitzplatzeKL2;
    }

    @Override

    public void setSitzPlatzeKL2(Integer sitzPlatzeKL2) {
        sitzplatzeKL2 = sitzPlatzeKL2;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL3)


    public Integer getSitzPlatzeKL3() {
        return sitzplatzeKL3;
    }

    @Override

    public void setSitzPlatzeKL3(Integer sitzPlatzeKL3) {
        sitzplatzeKL3 = sitzPlatzeKL3;
    }

    @Override
    @Column(name = DBNames.SITZPLATZEKL4)


    public Integer getSitzPlatzeKL4() {
        return sitzplatzeKL4;
    }

    @Override

    public void setSitzPlatzeKL4(Integer sitzPlatzeKL4) {
        this.sitzplatzeKL4 = sitzPlatzeKL4;
    }

    @Override
    @Column(name = DBNames.AUFBAU, length = 100)


    public String getAufbau() {
        return aufbau;
    }

    @Override

    public void setAufbau(String aufbau) {
        this.aufbau = aufbau;
    }

    @Override
    @Column(name = DBNames.TRIEBZUGANZEIGEN)


    public Boolean getTriebzugAnzeigen() {
        return triebzugAnzeigen;
    }

    @Override

    public void setTriebzugAnzeigen(Boolean triebzugAnzeigen) {
        this.triebzugAnzeigen = triebzugAnzeigen;
    }

    @Override
    @Column(name = DBNames.TRIEBKOEPFE)


    public Integer getTriebkoepfe() {
        return triebkoepfe;
    }

    @Override

    public void setTriebkoepfe(Integer triebkoepfe) {
        this.triebkoepfe = triebkoepfe;
    }

    @Override
    @Column(name = DBNames.MITTELWAGEN)


    public Integer getMittelwagen() {
        return mittelwagen;
    }

    @Override

    public void setMittelwagen(Integer mittelwagen) {
        this.mittelwagen = mittelwagen;
    }

    @Override
    @Column(name = DBNames.SITZPLATZETZKL1)


    public Integer getSitzPlatzeTZKL1() {
        return sitzplatzeTZKL1;
    }

    @Override

    public void setSitzPlatzeTZKL1(Integer sitzPlatzeTZKL1) {
        this.sitzplatzeTZKL1 = sitzPlatzeTZKL1;
    }

    @Override
    @Column(name = DBNames.SITZPLATZETZKL2)


    public Integer getSitzPlatzeTzKL2() {
        return sitzplatzeTzKL2;
    }

    @Override

    public void setSitzPlatzeTzKL2(Integer sitzPlatzeTzKL2) {
        this.sitzplatzeTzKL2 = sitzPlatzeTzKL2;
    }

    @Override
    @Column(name = DBNames.DREHGESTELLBAUART, length = 100)


    public String getDrehgestellBauart() {
        return drehgestellBauart;
    }

    @Override

    public void setDrehgestellBauart(String drehgestellbauart) {
        this.drehgestellBauart = drehgestellbauart;
    }

    @Override
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)



    @ApiModelProperty(dataType = "[Ljava.lang.String;", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    public Path getAbbildung() {
        return abbildung;
    }

    @Override
    public void setAbbildung(Path abbildung) {
        this.abbildung = abbildung;
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
                .append(ApiNames.KESSELUEBERDRUCK, getKesselueberdruck())
                .append(ApiNames.ROSTFLAECHE, getRostflaeche())
                .append(ApiNames.UEBERHITZERFLAECHE, getUeberhitzerflaeche())
                .append(ApiNames.WASSERVORRAT, getWasservorrat())
                .append(ApiNames.VERDAMPFUNG, getVerdampfung())
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
                .append(ApiNames.ABBILDUNG, getAbbildung())
                .toString();
    }
}