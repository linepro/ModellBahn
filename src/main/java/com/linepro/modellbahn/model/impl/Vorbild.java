package com.linepro.modellbahn.model.impl;

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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "vorbilder", indexes = { @Index(columnList = "gattung_id", unique = true),
        @Index(columnList = "unter_kategorie_id"),
        @Index(columnList = "antrieb_id"),
        @Index(columnList = "achsfolg_id"),
        @Index(columnList = "steuerung_id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "gattung_id" }) })
public class Vorbild extends AbstractItem implements IVorbild {

    private static final long serialVersionUID = -4852882107081643608L;

    private IGattung gattung;

    private IUnterKategorie unterKategorie;

    private String hersteller;

    private Date bauzeit;

    private Integer anzahl;

    private String betriebsNummer;

    private IAntrieb antrieb;

    private IAchsfolg achsfolg;

    private Double anfahrzugkraft;

    private Double leistung;

    private Double dienstgewicht;

    private Long geschwindigkeit;

    private Double lange;

    private Date ausserdienst;

    private Double dmTreibrad;

    private Double dmLaufradVorn;

    private Double dmLaufradHinten;

    private Integer zylinder;

    private Double dmZylinder;

    private Double kolbenhub;

    private Double kesselueberdruck;

    private Double rostflaeche;

    private Double ueberhitzerflaeche;

    private Double wasservorrat;

    private Double verdampfung;

    private ISteuerung steuerung;

    private Integer fahrmotoren;

    private String motorbauart;

    private Double leistungsuebertragung;

    private Double reichweite;

    private Double kapazitaet;

    private Integer klasse;

    private Long sitzplatzeKL1;

    private Long sitzplatzeKL2;

    private Long sitzplatzeKL3;

    private Long sitzplatzeKL4;

    private String aufbauten;

    private Boolean triebzugAnzeigen;

    private Long triebkoepfe;

    private Long mittelwagen;

    private Long sitzplatzeTZKL1;

    private Long sitzplatzeTzKL2;

    private String drehgestellBauart;

    public Vorbild() {
        super();
    }

    public Vorbild(Long id, IGattung gattung, IUnterKategorie unterKategorie, String hersteller, Date bauzeit,
            Integer anzahl,
            String betriebsNummer, IAntrieb antrieb, IAchsfolg achsfolg, Double anfahrzugkraft,
            Double leistung, Double dienstgewicht, Long geschwindigkeit, Double lange, Date ausserdienst,
            Double dmTreibrad, Double dmLaufradVorn, Double dmLaufradHinten, Integer zylinder, Double dmZylinder,
            Double kolbenhub,
            Double kesselueberdruck, Double rostflaeche, Double ueberhitzerflaeche, Double wasservorrat,
            Double verdampfung, ISteuerung steuerung, Integer fahrmotoren, String motorbauart,
            Double leistungsuebertragung, Double reichweite, Double kapazitaet, Integer klasse, Long sitzPlatzeKL1,
            Long sitzPlatzeKL2, Long sitzPlatzeKL3, Long sitzPlatzeKL4, String aufbauten, Boolean triebzugAnzeigen,
            Long triebkoepfe, Long mittelwagen, Long sitzPlatzeTZKL1, Long sitzPlatzeTzKL2,
            String drehgestellbauart, Boolean deleted) {
        super(id, deleted);

        this.gattung = gattung;
        this.unterKategorie = unterKategorie;
        this.hersteller = hersteller;
        this.bauzeit = bauzeit;
        this.anzahl = anzahl;
        this.betriebsNummer = betriebsNummer;
        this.antrieb = antrieb;
        this.achsfolg = achsfolg;
        this.anfahrzugkraft = anfahrzugkraft;
        this.leistung = leistung;
        this.dienstgewicht = dienstgewicht;
        this.geschwindigkeit = geschwindigkeit;
        this.lange = lange;
        this.ausserdienst = ausserdienst;
        this.dmTreibrad = dmTreibrad;
        this.dmLaufradVorn = dmLaufradVorn;
        this.dmLaufradHinten = dmLaufradHinten;
        this.zylinder = zylinder;
        this.dmZylinder = dmZylinder;
        this.kolbenhub = kolbenhub;
        this.kesselueberdruck = kesselueberdruck;
        this.rostflaeche = rostflaeche;
        this.ueberhitzerflaeche = ueberhitzerflaeche;
        this.wasservorrat = wasservorrat;
        this.verdampfung = verdampfung;
        this.steuerung = steuerung;
        this.fahrmotoren = fahrmotoren;
        this.motorbauart = motorbauart;
        this.leistungsuebertragung = leistungsuebertragung;
        this.reichweite = reichweite;
        this.kapazitaet = kapazitaet;
        this.klasse = klasse;
        this.sitzplatzeKL1 = sitzPlatzeKL1;
        this.sitzplatzeKL2 = sitzPlatzeKL2;
        this.sitzplatzeKL3 = sitzPlatzeKL3;
        this.sitzplatzeKL4 = sitzPlatzeKL4;
        this.aufbauten = aufbauten;
        this.triebzugAnzeigen = triebzugAnzeigen;
        this.triebkoepfe = triebkoepfe;
        this.mittelwagen = mittelwagen;
        this.sitzplatzeTZKL1 = sitzPlatzeTZKL1;
        this.sitzplatzeTzKL2 = sitzPlatzeTzKL2;
        this.drehgestellBauart = drehgestellbauart;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = "gattung_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk1"))
    @JsonBackReference
    public IGattung getGattung() {
        return gattung;
    }

    @Override
    public void setGattung(IGattung gattung) {
        this.gattung = gattung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UnterKategorie.class)
    @JoinColumn(name = "unter_kategorie_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk2"))
    @JsonBackReference
    public IUnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    @Override
    public void setUnterKategorie(IUnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    @Override
    @Column(name = "hersteller", nullable = true, length = 100)
    public String getHersteller() {
        return hersteller;
    }

    @Override
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    @Column(name = "bauzeit", nullable = true)
    @Temporal(TemporalType.DATE)
    public Date getBauzeit() {
        return bauzeit;
    }

    @Override
    public void setBauzeit(Date bauzeit) {
        this.bauzeit = bauzeit;
    }

    @Override
    @Column(name = "anzahl", nullable = true)
    public Integer getAnzahl() {
        return anzahl;
    }

    @Override
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    @Column(name = "betriebsnummer", nullable = true, length = 100)
    public String getBetriebsNummer() {
        return betriebsNummer;
    }

    @Override
    public void setBetriebsNummer(String betriebsNummer) {
        this.betriebsNummer = betriebsNummer;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Antrieb.class)
    @JoinColumn(name = "antrieb_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk3"))
    @JsonBackReference
    public IAntrieb getAntrieb() {
        return antrieb;
    }

    public void setAntrieb(IAntrieb antrieb) {
        this.antrieb = antrieb;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = "achsfolg_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk4"))
    @JsonBackReference
    public IAchsfolg getAchsfolg() {
        return achsfolg;
    }

    @Override
    public void setAchsfolg(IAchsfolg achsfolg) {
        this.achsfolg = achsfolg;
    }

    @Override
    @Column(name = "anfahrzugkraft", nullable = true)
    public Double getAnfahrzugkraft() {
        return anfahrzugkraft;
    }

    @Override
    public void setAnfahrzugkraft(Double anfahrzugkraft) {
        this.anfahrzugkraft = anfahrzugkraft;
    }

    @Override
    @Column(name = "leistung", nullable = true)
    public Double getLeistung() {
        return leistung;
    }

    @Override
    public void setLeistung(Double leistung) {
        this.leistung = leistung;
    }

    @Override
    @Column(name = "dienstgewicht", nullable = true)
    public Double getDienstgewicht() {
        return dienstgewicht;
    }

    @Override
    public void setDienstgewicht(Double dienstgewicht) {
        this.dienstgewicht = dienstgewicht;
    }

    @Override
    @Column(name = "geschwindigkeit", nullable = true)
    public Long getGeschwindigkeit() {
        return geschwindigkeit;
    }

    @Override
    public void setGeschwindigkeit(Long geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    @Override
    @Column(name = "lange", nullable = true)
    public Double getLange() {
        return lange;
    }

    @Override
    public void setLange(Double lange) {
        this.lange = lange;
    }

    @Override
    @Column(name = "ausserdienst", nullable = true)
    @Temporal(TemporalType.DATE)
    public Date getAusserdienst() {
        return ausserdienst;
    }

    @Override
    public void setAusserdienst(Date ausserdienst) {
        this.ausserdienst = ausserdienst;
    }

    @Override
    @Column(name = "dmtreibrad", nullable = true)
    public Double getDmTreibrad() {
        return dmTreibrad;
    }

    @Override
    public void setDmTreibrad(Double dmTreibrad) {
        this.dmTreibrad = dmTreibrad;
    }

    @Override
    @Column(name = "dmlaufradvorn", nullable = true)
    public Double getDmLaufradVorn() {
        return dmLaufradVorn;
    }

    @Override
    public void setDmLaufradVorn(Double dmLaufradVorn) {
        this.dmLaufradVorn = dmLaufradVorn;
    }

    @Override
    @Column(name = "dmlaufradhinten", nullable = true)
    public Double getDmLaufradHinten() {
        return dmLaufradHinten;
    }

    @Override
    public void setDmLaufradHinten(Double dmLaufradHinten) {
        this.dmLaufradHinten = dmLaufradHinten;
    }

    @Override
    @Column(name = "zylinder", nullable = true)
    public Integer getZylinder() {
        return zylinder;
    }

    @Override
    public void setZylinder(Integer zylinder) {
        this.zylinder = zylinder;
    }

    @Override
    @Column(name = "dmzylinder", nullable = true)
    public Double getDmZylinder() {
        return dmZylinder;
    }

    @Override
    public void setDmZylinder(Double dmZylinder) {
        this.dmZylinder = dmZylinder;
    }

    @Override
    @Column(name = "kolbenhub", nullable = true)
    public Double getKolbenhub() {
        return kolbenhub;
    }

    @Override
    public void setKolbenhub(Double kolbenhub) {
        this.kolbenhub = kolbenhub;
    }

    @Override
    @Column(name = "kesselueberdruck", nullable = true)
    public Double getKesselueberdruck() {
        return kesselueberdruck;
    }

    @Override
    public void setKesselueberdruck(Double kesselueberdruck) {
        this.kesselueberdruck = kesselueberdruck;
    }

    @Override
    @Column(name = "rostflaeche", nullable = true)
    public Double getRostflaeche() {
        return rostflaeche;
    }

    @Override
    public void setRostflaeche(Double rostflaeche) {
        this.rostflaeche = rostflaeche;
    }

    @Override
    @Column(name = "ueberhitzerflaeche", nullable = true)
    public Double getUeberhitzerflaeche() {
        return ueberhitzerflaeche;
    }

    @Override
    public void setUeberhitzerflaeche(Double ueberhitzerflaeche) {
        this.ueberhitzerflaeche = ueberhitzerflaeche;
    }

    @Override
    @Column(name = "wasservorrat", nullable = true)
    public Double getWasservorrat() {
        return wasservorrat;
    }

    @Override
    public void setWasservorrat(Double wasservorrat) {
        this.wasservorrat = wasservorrat;
    }

    @Column(name = "verdampfung", nullable = true)
    public Double getVerdampfung() {
        return verdampfung;
    }

    @Override
    public void setVerdampfung(Double verdampfung) {
        this.verdampfung = verdampfung;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = "steuerung_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "vorbild_fk5"))
    @JsonBackReference
    public ISteuerung getSteuerung() {
        return steuerung;
    }

    @Override
    public void setSteuerung(ISteuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    @Column(name = "fahrmotoren", nullable = true)
    public Integer getFahrmotoren() {
        return fahrmotoren;
    }

    @Override
    public void setFahrmotoren(Integer fahrmotoren) {
        this.fahrmotoren = fahrmotoren;
    }

    @Override
    @Column(name = "motorbauart", nullable = true, length = 100)
    public String getMotorbauart() {
        return motorbauart;
    }

    @Override
    public void setMotorbauart(String motorbauart) {
        this.motorbauart = motorbauart;
    }

    @Override
    @Column(name = "leistungsuebertragung", nullable = true)
    public Double getLeistungsuebertragung() {
        return leistungsuebertragung;
    }

    @Override
    public void setLeistungsuebertragung(Double leistungsuebertragung) {
        this.leistungsuebertragung = leistungsuebertragung;
    }

    @Override
    @Column(name = "reichweite", nullable = true)
    public Double getReichweite() {
        return reichweite;
    }

    @Override
    public void setReichweite(Double reichweite) {
        this.reichweite = reichweite;
    }

    @Override
    @Column(name = "kapazitaet", nullable = true)
    public Double getKapazitaet() {
        return kapazitaet;
    }

    @Override
    public void setKapazitaet(Double kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    @Override
    @Column(name = "klasse", nullable = true)
    public Integer getKlasse() {
        return klasse;
    }

    @Override
    public void setKlasse(Integer klasse) {
        this.klasse = klasse;
    }

    @Override
    @Column(name = "sitzplatzekl1", nullable = true)
    public Long getSitzPlatzeKL1() {
        return sitzplatzeKL1;
    }

    @Override
    public void setSitzPlatzeKL1(Long sitzPlatzeKL1) {
        this.sitzplatzeKL1 = sitzPlatzeKL1;
    }

    @Override
    @Column(name = "sitzplatzekl2", nullable = true)
    public Long getSitzPlatzeKL2() {
        return sitzplatzeKL2;
    }

    public void setSitzPlatzeKL2(Long sitzPlatzeKL2) {
        sitzplatzeKL2 = sitzPlatzeKL2;
    }

    @Override
    @Column(name = "sitzplatzekl3", nullable = true)
    public Long getSitzPlatzeKL3() {
        return sitzplatzeKL3;
    }

    @Override
    public void setSitzPlatzeKL3(Long sitzPlatzeKL3) {
        sitzplatzeKL3 = sitzPlatzeKL3;
    }

    @Override
    @Column(name = "sitzplatzekl4", nullable = true)
    public Long getSitzPlatzeKL4() {
        return sitzplatzeKL4;
    }

    @Override
    public void setSitzPlatzeKL4(Long sitzPlatzeKL4) {
        this.sitzplatzeKL4 = sitzPlatzeKL4;
    }

    @Override
    @Column(name = "aufbauten", nullable = true, length = 100)
    public String getAufbauten() {
        return aufbauten;
    }

    @Override
    public void setAufbauten(String aufbauten) {
        this.aufbauten = aufbauten;
    }

    @Override
    @Column(name = "triebzuganzeigen", nullable = true)
    public Boolean getTriebzugAnzeigen() {
        return triebzugAnzeigen;
    }

    @Override
    public void setTriebzugAnzeigen(Boolean triebzugAnzeigen) {
        this.triebzugAnzeigen = triebzugAnzeigen;
    }

    @Override
    @Column(name = "triebkoepfe", nullable = true)
    public Long getTriebkoepfe() {
        return triebkoepfe;
    }

    @Override
    public void setTriebkoepfe(Long triebkoepfe) {
        this.triebkoepfe = triebkoepfe;
    }

    @Override
    @Column(name = "mittelwagen", nullable = true)
    public Long getMittelwagen() {
        return mittelwagen;
    }

    @Override
    public void setMittelwagen(Long mittelwagen) {
        this.mittelwagen = mittelwagen;
    }

    @Override
    @Column(name = "sitzplatzetzkl1", nullable = true)
    public Long getSitzPlatzeTZKL1() {
        return sitzplatzeTZKL1;
    }

    @Override
    public void setSitzPlatzeTZKL1(Long sitzPlatzeTZKL1) {
        this.sitzplatzeTZKL1 = sitzPlatzeTZKL1;
    }

    @Override
    @Column(name = "sitzplatzetzkl2", nullable = true)
    public Long getSitzPlatzeTzKL2() {
        return sitzplatzeTzKL2;
    }

    @Override
    public void setSitzPlatzeTzKL2(Long sitzPlatzeTzKL2) {
        this.sitzplatzeTzKL2 = sitzPlatzeTzKL2;
    }

    @Override
    @Column(name = "drehgestellbauart", nullable = true, length = 100)
    public String getDrehgestellBauart() {
        return drehgestellBauart;
    }

    @Override
    public void setDrehgestellBauart(String drehgestellbauart) {
        this.drehgestellBauart = drehgestellbauart;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
                .append("Gattung", getGattung()).append("UnterKategorie", getUnterKategorie())
                .append("Hersteller", getHersteller()).append("bauzeit", getBauzeit()).append("Anzahl", getAnzahl())
                .append("BetriebsNummer", getBetriebsNummer()).append("Antrieb", getAntrieb())
                .append("Achsfolge", getAchsfolg()).append("Anfahrzugkraft", getAnfahrzugkraft())
                .append("Leistung", getLeistung()).append("Dienstgewicht", getDienstgewicht())
                .append("Geschwindigkeit", getGeschwindigkeit()).append("lange", getLange())
                .append("Ausserdienst", getAusserdienst()).append("DmTreibrad", getDmTreibrad())
                .append("DmLaufradVorn", getDmLaufradVorn()).append("DmLaufradHinten", getDmLaufradHinten())
                .append("Zylinder", getZylinder()).append("DmZylinder", getDmZylinder())
                .append("Kolbenhub", getKolbenhub()).append("Kesselueberdruck", getKesselueberdruck())
                .append("Rostflaeche", getRostflaeche()).append("Ueberhitzerflaeche", getUeberhitzerflaeche())
                .append("Wasservorrat", getWasservorrat()).append("Verdampfung", getVerdampfung())
                .append("Steuerung", getSteuerung()).append("Fahrmotoren", getFahrmotoren())
                .append("Motorbauart", getMotorbauart()).append("Leistungsuebertragung", getLeistungsuebertragung())
                .append("Reichweite", getReichweite()).append("Kapazitaet", getKapazitaet())
                .append("Klasse", getKlasse()).append("sitzplatzeKL1", getSitzPlatzeKL1())
                .append("sitzplatzeKL2", getSitzPlatzeKL2()).append("sitzplatzeKL3", getSitzPlatzeKL3())
                .append("sitzplatzeKL4", getSitzPlatzeKL4()).append("Aufbauten", getAufbauten())
                .append("TriebzugAnzeigen", getTriebzugAnzeigen()).append("Triebkoepfe", getTriebkoepfe())
                .append("Mittelwagen", getMittelwagen()).append("sitzplatzeTZKL1", getSitzPlatzeTZKL1())
                .append("sitzplatzeTzKL2", getSitzPlatzeTzKL2()).append("drehgestellBauart", getDrehgestellBauart())
                .toString();
    }
}