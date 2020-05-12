package com.linepro.modellbahn.entity;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.EpochModel;
import com.linepro.modellbahn.model.MassstabModel;
import com.linepro.modellbahn.model.SpurweiteModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * Produkt. A product.
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PRODUKT)
@Table(name = DBNames.PRODUKT,
    indexes = {
        @Index(name = DBNames.PRODUKT + "_IX1", columnList = DBNames.HERSTELLER_ID),
        @Index(name = DBNames.PRODUKT + "_IX2", columnList = DBNames.EPOCH_ID),
        @Index(name = DBNames.PRODUKT + "_IX3", columnList = DBNames.GATTUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX4", columnList = DBNames.BAHNVERWALTUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX5", columnList = DBNames.ACHSFOLG_ID),
        @Index(name = DBNames.PRODUKT + "_IX6", columnList = DBNames.MASSSTAB_ID),
        @Index(name = DBNames.PRODUKT + "_IX7", columnList = DBNames.SPURWEITE_ID),
        @Index(name = DBNames.PRODUKT + "_IX8", columnList = DBNames.UNTER_KATEGORIE_ID),
        @Index(name = DBNames.PRODUKT + "_IX9", columnList = DBNames.SONDERMODELL_ID),
        @Index(name = DBNames.PRODUKT + "_IX10", columnList = DBNames.AUFBAU_ID),
        @Index(name = DBNames.PRODUKT + "_IX11", columnList = DBNames.LICHT_ID),
        @Index(name = DBNames.PRODUKT + "_IX12", columnList = DBNames.KUPPLUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX13", columnList = DBNames.VORBILD_ID),
        @Index(name = DBNames.PRODUKT + "_IX14", columnList = DBNames.STEUERUNG_ID),
        @Index(name = DBNames.PRODUKT + "_IX15", columnList = DBNames.DECODER_TYP_ID),
        @Index(name = DBNames.PRODUKT + "_IX16", columnList = DBNames.MOTOR_TYP_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PRODUKT + "_UC1", columnNames = { DBNames.HERSTELLER_ID, DBNames.BESTELL_NR })
    })
//@formatter:on
public class Produkt extends ItemImpl {

    /** The hersteller. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.hersteller.notnull}")
    private Hersteller hersteller;

    @Pattern(regexp = "^[A-Z0-9\\-.]+$", message = "{com.linepro.modellbahn.validator.constraints.bestellNr.invalid}")
    private String bestellNr;

    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The unter kategorie. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.unterKategorie.notnull}")
    private UnterKategorie unterKategorie;

    /** The massstab. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.massstab.notnull}")
    private MassstabModel massstab;

    /** The spurweite. */
    private SpurweiteModel spurweite;

    /** The bahnverwaltung. */
    private Bahnverwaltung bahnverwaltung;

    /** The gattung. */
    private Gattung gattung;

    /** The epoch. */
    private EpochModel epoch;

    /** The achsfolg. */
    private Achsfolg achsfolg;

    /** The Sondermodel. */
    private SonderModell sondermodell;

    /** The aufbau. */
    private Aufbau aufbau;

    /** The licht. */
    private Licht licht;

    /** The kupplung. */
    private Kupplung kupplung;

    /** The vorbild. */
    private Vorbild vorbild;

    /** The steuerung. */
    private Steuerung steuerung;

    /** The decoder typ. */
    private DecoderTyp decoderTyp;

    /** The motor typ. */
    private MotorTyp motorTyp;

    /** The anmerkung. */
    private String anmerkung;

    /** The betreibsnummer. */
    private String betreibsnummer;

    /** The bauzeit. */
    @Past(message = "{com.linepro.modellbahn.validator.constraints.bauzeit.past}")
    private LocalDate bauzeit;

    /** The anleitungen. */
    private Path anleitungen;

    /** The explosionszeichnung. */
    private Path explosionszeichnung;

    /** The lange. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.lange.positive}")
    private BigDecimal lange;

    /** The abbildung. */
    private Path abbildung;

    /** The teilen. */
    private Set<ProduktTeil> teilen;

    public Produkt() {
        super();
    }

    public Produkt(Long id, Hersteller hersteller, String bestellNr, String bezeichnung, UnterKategorie unterKategorie,
            MassstabModel massstab, SpurweiteModel spurweite, EpochModel epoch, Bahnverwaltung bahnverwaltung, Gattung gattung,
            String betreibsnummer, LocalDate bauzeit, Vorbild vorbild, Achsfolg achsfolg, String anmerkung,
            SonderModell sondermodel, Aufbau aufbau, Licht licht, Kupplung kupplung, Steuerung steuerung,
            DecoderTyp decoderTyp, MotorTyp motorTyp, BigDecimal lange, Boolean deleted) {
        super(id, deleted);
        setHersteller(hersteller);
        setBestellNr(bestellNr);
        setBezeichnung(bezeichnung);
        setUnterKategorie(unterKategorie);
        setMassstab(massstab);
        setSpurweite(spurweite);
        setEpoch(epoch);
        setBahnverwaltung(bahnverwaltung);
        setGattung(gattung);
        setBetreibsnummer(betreibsnummer);
        setBauzeit(bauzeit);
        setVorbild(vorbild);
        setAchsfolg(achsfolg);
        setAnmerkung(anmerkung);
        setSondermodell(sondermodel);
        setAufbau(aufbau);
        setLicht(licht);
        setKupplung(kupplung);
        setSteuerung(steuerung);
        setDecoderTyp(decoderTyp);
        setMotorTyp(motorTyp);
        setLange(lange);
    }

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hersteller.class)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk16"))
    @BusinessKey
    public Hersteller getHersteller() {
        return hersteller;
    }

    
    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    
    @BusinessKey
    public String getBestellNr() {
        return bestellNr;
    }

    
    public void setBestellNr(String bestellNr) {
        this.bestellNr = bestellNr;
    }

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
      return bezeichnung;
    }

    
    public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
    }

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UnterKategorie.class)
    @JoinColumn(name = DBNames.UNTER_KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk7"))
    public UnterKategorie getUnterKategorie() {
        return unterKategorie;
    }

    
    public void setUnterKategorie(UnterKategorie unterKategorie) {
        this.unterKategorie = unterKategorie;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Massstab.class)
    @JoinColumn(name = DBNames.MASSSTAB_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk5"))
    public MassstabModel getMassstab() {
        return massstab;
    }

    
    public void setMassstab(MassstabModel massstab) {
        this.massstab = massstab;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Spurweite.class)
    @JoinColumn(name = DBNames.SPURWEITE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk6"))
    public SpurweiteModel getSpurweite() {
        return spurweite;
    }

    
    public void setSpurweite(SpurweiteModel spurweite) {
        this.spurweite = spurweite;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Epoch.class)
    @JoinColumn(name = DBNames.EPOCH_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk1"))
    public EpochModel getEpoch() {
        return epoch;
    }

    
    public void setEpoch(EpochModel epoch) {
        this.epoch = epoch;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bahnverwaltung.class)
    @JoinColumn(name = DBNames.BAHNVERWALTUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk3"))
    public Bahnverwaltung getBahnverwaltung() {
        return bahnverwaltung;
    }

    
    public void setBahnverwaltung(Bahnverwaltung bahnverwaltung) {
        this.bahnverwaltung = bahnverwaltung;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gattung.class)
    @JoinColumn(name = DBNames.GATTUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk2"))
    public Gattung getGattung() {
        return gattung;
    }

    
    public void setGattung(Gattung gattung) {
        this.gattung = gattung;
    }

    
    @Column(name = DBNames.BETREIBSNUMMER, length = 100)
    public String getBetreibsnummer() {
        return betreibsnummer;
    }

    
    public void setBetreibsnummer(String betreibsnummer) {
        this.betreibsnummer = betreibsnummer;
    }

    
    @Column(name = DBNames.BAUZEIT)
    public LocalDate getBauzeit() {
        return bauzeit;
    }

    
    public void setBauzeit(LocalDate bauzeit) {
        this.bauzeit = bauzeit;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Vorbild.class)
    @JoinColumn(name = DBNames.VORBILD_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk12"))
    public Vorbild getVorbild() {
        return vorbild;
    }

    
    public void setVorbild(Vorbild vorbild) {
        this.vorbild = vorbild;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Achsfolg.class)
    @JoinColumn(name = DBNames.ACHSFOLG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk4"))
    public Achsfolg getAchsfolg() {
        return achsfolg;
    }

    
    public void setAchsfolg(Achsfolg achsfolg) {
        this.achsfolg = achsfolg;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SonderModell.class)
    @JoinColumn(name = DBNames.SONDERMODELL_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk8"))
    public SonderModell getSondermodell() {
        return sondermodell;
    }

    
    public void setSondermodell(SonderModell sondermodell) {
        this.sondermodell = sondermodell;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Aufbau.class)
    @JoinColumn(name = DBNames.AUFBAU_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk9"))
    public Aufbau getAufbau() {
        return aufbau;
    }

    
    public void setAufbau(Aufbau aufbau) {
        this.aufbau = aufbau;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = DBNames.LICHT_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk10"))
    public Licht getLicht() {
        return licht;
    }

    
    public void setLicht(Licht licht) {
        this.licht = licht;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = DBNames.KUPPLUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk11"))
    public Kupplung getKupplung() {
        return kupplung;
    }

    
    public void setKupplung(Kupplung kupplung) {
        this.kupplung = kupplung;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = DBNames.STEUERUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk13"))
    public Steuerung getSteuerung() {
        return steuerung;
    }

    
    public void setSteuerung(Steuerung steuerung) {
        this.steuerung = steuerung;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk14"))
    public DecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    
    public void setDecoderTyp(DecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = DBNames.MOTOR_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT + "_fk15"))
    public MotorTyp getMotorTyp() {
        return motorTyp;
    }

    
    public void setMotorTyp(MotorTyp motorTyp) {
        this.motorTyp = motorTyp;
    }

    
    @Column(name = DBNames.LANGE, precision = 6, scale = 2)
    public BigDecimal getLange() {
        return lange;
    }

    
    public void setLange(BigDecimal lange) {
        this.lange = lange;
    }

    
    @Column(name = DBNames.ANMERKUNG, length = 100)
    public String getAnmerkung() {
        return anmerkung;
    }

    
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    
    @Column(name = DBNames.ANLEITUNGEN, length = 512)
    @Convert(converter = PathConverter.class)
    public Path getAnleitungen() {
        return anleitungen;
    }

    
    public void setAnleitungen(Path anleitungen) {
        this.anleitungen = anleitungen;
    }

    
    @Column(name = DBNames.EXPLOSIONSZEICHNUNG, length = 512)
    @Convert(converter = PathConverter.class)
    public Path getExplosionszeichnung() {
        return explosionszeichnung;
    }

    
    public void setExplosionszeichnung(Path explosionszeichnung) {
        this.explosionszeichnung = explosionszeichnung;
    }

    
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    public Path getAbbildung() {
        return abbildung;
    }

    
    public void setAbbildung(Path abbildung) {
        this.abbildung = abbildung;
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.PRODUKT, targetEntity = ProduktTeil.class, orphanRemoval = true)
    public Set<ProduktTeil> getTeilen() {
        return teilen;
    }

    
    public void setTeilen(Set<ProduktTeil> teilen) {
        this.teilen = teilen;
    }

    
    @Transient
    public Set<ProduktTeil> getSortedTeilen() {
        return new TreeSet<>(getTeilen());
    }

    
    public void addTeil(ProduktTeil teil) {
        teil.setProdukt(this);
        teil.setDeleted(false);
        getTeilen().add(teil);
    }

    
    public void removeTeil(ProduktTeil teil) {
        getTeilen().remove(teil);
    }

    @Override
    public int compareTo(Item other) {
        if (other instanceof Produkt) {
            return new CompareToBuilder()
                    .append(getHersteller(), ((Produkt) other).getHersteller())
                    .append(getBestellNr(), ((Produkt) other).getBestellNr())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getHersteller())
                .append(getBestellNr())
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Produkt)) {
            return false;
        }

        Produkt other = (Produkt) obj;

        return new EqualsBuilder()
                .append(getHersteller(), other.getHersteller())
                .append(getBestellNr(), other.getBestellNr())
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.HERSTELLER, getHersteller())
                .append(DBNames.BESTELL_NR, getBestellNr())
                .append(DBNames.UNTER_KATEGORIE, getUnterKategorie())
                .append(DBNames.MASSSTAB, getMassstab())
                .append(DBNames.SPURWEITE, getSpurweite())
                .append(DBNames.BETREIBSNUMMER, getBetreibsnummer())
                .append(DBNames.EPOCH, getEpoch())
                .append(DBNames.BAHNVERWALTUNG, getBahnverwaltung())
                .append(DBNames.GATTUNG, getGattung())
                .append(DBNames.BAUZEIT, getBauzeit())
                .append(DBNames.ACHSFOLG, getAchsfolg())
                .append(DBNames.VORBILD, getVorbild())
                .append(DBNames.ANMERKUNG, getAnmerkung())
                .append(DBNames.SONDERMODELL, getSondermodell())
                .append(DBNames.AUFBAU, getAufbau())
                .append(DBNames.LICHT, getLicht())
                .append(DBNames.KUPPLUNG, getKupplung())
                .append(DBNames.STEUERUNG, getSteuerung())
                .append(DBNames.DECODER_TYP, getDecoderTyp())
                .append(DBNames.MOTOR_TYP, getMotorTyp())
                .append(DBNames.LANGE, getLange())
                .append(DBNames.ANLEITUNGEN, getAnleitungen())
                .append(DBNames.EXPLOSIONSZEICHNUNG, getExplosionszeichnung())
                .append(DBNames.ABBILDUNG, getAbbildung())
                .append(DBNames.PRODUKT_TEIL, getTeilen())
                .toString();
    }
}