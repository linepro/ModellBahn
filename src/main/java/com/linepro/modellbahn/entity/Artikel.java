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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.ArtikelId;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Currency;

/**
 * Artikel.
 * An article. 
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ARTIKEL)
@Table(name = DBNames.ARTIKEL,
    indexes = { 
        @Index(name = DBNames.ARTIKEL + "_IX1", columnList = DBNames.PRODUKT_ID),
        @Index(name = DBNames.ARTIKEL + "_IX2", columnList = DBNames.WAHRUNG_ID),
        @Index(name = DBNames.ARTIKEL + "_IX3", columnList = DBNames.STEUERUNG_ID),
        @Index(name = DBNames.ARTIKEL + "_IX4", columnList = DBNames.MOTOR_TYP_ID),
        @Index(name = DBNames.ARTIKEL + "_IX5", columnList = DBNames.LICHT_ID),
        @Index(name = DBNames.ARTIKEL + "_IX6", columnList = DBNames.KUPPLUNG_ID),
        @Index(name = DBNames.ARTIKEL + "_IX7", columnList = DBNames.DECODER_ID) 
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ARTIKEL + "_UC1", columnNames = { DBNames.ARTIKEL_ID }) 
    })
//@formatter:on
public class Artikel extends ItemImpl {

    /** The abbildung. */
    @Pattern(regexp = "^[A-Z0-9]+$", message = "{com.linepro.modellbahn.validator.constraints.artikelId.invalid}")
    private String artikelId;

    /** The produkt. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The Kaufdatum. */
    @Past(message = "{com.linepro.modellbahn.validator.constraints.kaufdatum.past}")
    private LocalDate kaufdatum;

    /** The wahrung. */
    @Currency(message = "{com.linepro.modellbahn.validator.constraints.wahrung.invalid}")
    private String wahrung;

    /** The steuerung. */
    private Steuerung steuerung;

    /** The motor typ. */
    private MotorTyp motorTyp;

    /** The licht. */
    private Licht licht;

    /** The kupplung. */
    private Kupplung kupplung;

    /** The decoder. */
    private Decoder decoder;

    /** The bezeichnung. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The preis. */
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.preis.positive}")
    private BigDecimal preis;

    /** The stuck. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.stuck.notnull}")
    @Min(value=1, message = "{com.linepro.modellbahn.validator.constraints.stuck.positive}")
    private Integer stuck;

    /** The stuck. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.verbleibende.notnull}")
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.verbleibende.positive}")
    private Integer verbleibende;

    /** The anmerkung. */
    private String anmerkung;

    /** The beladung. */
    private String beladung;

    /** The abbildung. */
    private Path abbildung;

    /** The status. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.status.notnull}")
    private Status status;

    /** The anderung. */
    private Set<Anderung> anderungen;

    /**
     * Instantiates a new artikel.
     */
    public Artikel() {
        super();
    }

    /**
     * Instantiates a new artikel.
     *
     * @param id the id
     * @param produkt the produkt
     * @param kaufdatum the kaufdatum
     * @param wahrung the wahrung
     * @param preis the preis
     * @param stuck the stuck
     * @param steuerung the steuerung
     * @param motorTyp the motor typ
     * @param licht the licht
     * @param kupplung the kupplung
     * @param decoder the decoder
     * @param artikelId the artikel nr
     * @param bezeichnung the bezeichnung
     * @param anmerkung the anmerkung
     * @param beladung the beladung
     * @param status the status
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public Artikel(Long id, Produkt produkt, LocalDate kaufdatum, String wahrung, BigDecimal preis, Integer stuck,
            Integer verbleibende, Steuerung steuerung, MotorTyp motorTyp, Licht licht, Kupplung kupplung, 
            Decoder decoder, String artikelId, String bezeichnung, String anmerkung, String beladung, Status status, 
            Boolean deleted) {
        super(id, deleted);

        setArtikelId(artikelId);
        setBezeichnung(bezeichnung);
        setProdukt(produkt);
        setKaufdatum(kaufdatum);
        setWahrung(wahrung);
        setPreis(preis);
        setStuck(stuck);
        setVerbleibende(verbleibende);
        setSteuerung(steuerung);
        setMotorTyp(motorTyp);
        setLicht(licht);
        setKupplung(kupplung);
        setDecoder(decoder);
        setAnmerkung(anmerkung);
        setBeladung(beladung);
        setStatus(status);
    }

    @BusinessKey
    @ArtikelId
    @Column(name = DBNames.ARTIKEL_ID, unique = true, length = 6, nullable = false, updatable = false)
    public String getArtikelId() {
      return artikelId;
    }

    
    public void setArtikelId(String artikelId) {
      this.artikelId = (artikelId != null ? artikelId.toUpperCase() : artikelId);
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk1"))
    public Produkt getProdukt() {
        return produkt;
    }

    
    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    
    @Column(name = DBNames.KAUFDATUM)
    public LocalDate getKaufdatum() {
        return kaufdatum;
    }

    
    public void setKaufdatum(LocalDate kaufdatum) {
        this.kaufdatum = kaufdatum;
    }

    
    @Column(name = DBNames.WAHRUNG, length = 3)
    public String getWahrung() {
        return wahrung;
    }

    
    public void setWahrung(String wahrung) {
        this.wahrung = wahrung;
    }

    
    @Column(name = DBNames.PREIS, precision = 6, scale = 2)
    public BigDecimal getPreis() {
        return preis;
    }

    
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    
    @Column(name = DBNames.STUCK, nullable = false)
    public Integer getStuck() {
        return stuck;
    }

    
    public void setStuck(Integer stuck) {
        this.stuck = stuck;
    }

    
    @Column(name = DBNames.VERBLEIBENDE, nullable = false)
    public Integer getVerbleibende() {
        return verbleibende;
    }

    
    public void setVerbleibende(Integer verbleibende) {
        this.verbleibende = verbleibende;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Steuerung.class)
    @JoinColumn(name = DBNames.STEUERUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk3"))
    public Steuerung getSteuerung() {
        return steuerung;
    }

    
    public void setSteuerung(Steuerung steuerung) {
        this.steuerung = steuerung;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MotorTyp.class)
    @JoinColumn(name = DBNames.MOTOR_TYP_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk4"))
    public MotorTyp getMotorTyp() {
        return motorTyp;
    }

    
    public void setMotorTyp(MotorTyp motorTyp) {
        this.motorTyp = motorTyp;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Licht.class)
    @JoinColumn(name = DBNames.LICHT_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk5"))
    public Licht getLicht() {
        return licht;
    }

    
    public void setLicht(Licht licht) {
        this.licht = licht;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Kupplung.class)
    @JoinColumn(name = DBNames.KUPPLUNG_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk6"))
    public Kupplung getKupplung() {
        return kupplung;
    }

    
    public void setKupplung(Kupplung kupplung) {
        this.kupplung = kupplung;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Decoder.class)
    @JoinColumn(name = DBNames.DECODER_ID, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ARTIKEL + "_fk7"))
    public Decoder getDecoder() {
        return decoder;
    }

    
    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
      return bezeichnung;
    }

    
    public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
    }
    
    
    @Column(name = DBNames.ANMERKUNG, length = 100)
    public String getAnmerkung() {
        return anmerkung;
    }

    
    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    
    @Column(name = DBNames.BELADUNG, length = 100)
    public String getBeladung() {
        return beladung;
    }

    
    public void setBeladung(String beladung) {
        this.beladung = beladung;
    }

    
    @Column(name = DBNames.ABBILDUNG)
    @Convert(converter = PathConverter.class)
    public Path getAbbildung() {
        return abbildung;
    }

    
    public void setAbbildung(Path abbildung) {
        this.abbildung = abbildung;
    }

    
    @Column(name = DBNames.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    
    public void setStatus(Status status) {
        this.status = status;
    }

    
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = DBNames.ARTIKEL, targetEntity=Anderung.class, orphanRemoval = true)
    public Set<Anderung> getAnderungen() {
        return anderungen;
    }

    
    @Transient
    public Set<Anderung> getSortedAnderungen() {
        return new TreeSet<Anderung>(getAnderungen());
    }

    
    public void setAnderungen(Set<Anderung> anderungen) {
        this.anderungen = anderungen;
    }
   
    
    public void addAnderung(Anderung anderung) {
        // Add at end semantics
        anderung.setArtikel(this);
        anderung.setAnderungId(getAnderungen().size()+1);
        anderung.setDeleted(false);

        getAnderungen().add(anderung);
    }

    
    public void removeAnderung(Anderung anderung) {
        getAnderungen().remove(anderung);
        
        // Just renumber the whole lot; don't try and work out from where - it's just as expensive
        /*
        int anderungsId = 1;

        for (IAnderung and : getAnderungen()) {
            and.setAnderungsId(anderungsId++);
        }
        */
    }

    
    @Transient
    public String getLinkId() {
        return getArtikelId();
    }

    
    public int compareTo(Item other) {
      if (other instanceof Artikel) {
        return new CompareToBuilder()
            .append(getArtikelId(), ((Artikel) other).getArtikelId())
            .toComparison();
      }

      return super.compareTo(other);
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.PRODUKT, getProdukt())
                .append(DBNames.KAUFDATUM, getKaufdatum())
                .append(DBNames.WAHRUNG, getWahrung())
                .append(DBNames.PREIS, getPreis())
                .append(DBNames.STUCK, getStuck())
                .append(DBNames.STEUERUNG, getSteuerung())
                .append(DBNames.MOTOR_TYP, getMotorTyp())
                .append(DBNames.LICHT, getLicht())
                .append(DBNames.KUPPLUNG, getKupplung())
                .append(DBNames.DECODER, getDecoder())
                .append(DBNames.ANMERKUNG, getAnmerkung())
                .append(DBNames.BELADUNG, getBeladung())
                .append(DBNames.ABBILDUNG, getAbbildung())
                .append(DBNames.STATUS, getStatus())
                .toString();
    }
}
