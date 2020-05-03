package com.linepro.modellbahn.entity;

import java.math.BigDecimal;
import java.nio.file.Path;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.entity.base.ItemImpl;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.model.ProtokollModel;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.model.validation.Fahrstufe;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.persistence.util.PathConverter;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * DecoderTyp. Represents a Decoder type (manufacturer : part numer)
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.DECODER_TYP)
@Table(name = DBNames.DECODER_TYP, indexes = { @Index(columnList = DBNames.HERSTELLER_ID),
        @Index(columnList = DBNames.PROTOKOLL_ID) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.HERSTELLER_ID, DBNames.BESTELL_NR }) })
public class DecoderTyp extends ItemImpl {

    /** The hersteller. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.hersteller.notnull}")
    private HerstellerModel hersteller;

    @Pattern(regexp = "^[A-Z0-9\\-.]+$", message = "{com.linepro.modellbahn.validator.constraints.bestellNr.invalid}")
    private String bestellNr;

    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notempty}")
    private String bezeichnung;

    /** The i max. */
    @Range(max = 10, min = 0, message = "{com.linepro.modellbahn.validator.constraints.imax.range}")
    private BigDecimal iMax;

    /** The protokoll. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notnull}")
    private ProtokollModel protokoll;

    /** The fahrstufe. */
    @Fahrstufe
    private Integer fahrstufe;

    /** The sound. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.sound.notnull}")
    private Boolean sound;

    /** The konfiguration. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.konfiguration.notnull}")
    private Konfiguration konfiguration;

    private Stecker stecker;

    /** The anleitungen. */
    private Path anleitungen;

    /** The adressen. */
    private Set<DecoderTypAdress> adressen;

    /** The cvs. */
    private Set<DecoderTypCv> CVs;

    /** The funktion. */
    private Set<DecoderTypFunktion> funktionen;

    /**
     * Instantiates a new decoder typ.
     */
    public DecoderTyp() {
        super();
    }

    public DecoderTyp(Long id, HerstellerModel hersteller, ProtokollModel protokoll, String bestellNr, String bezeichnung,
            Boolean sound, Konfiguration konfiguration, Stecker stecker, Boolean deleted) {
        super(id, deleted);

        setHersteller(hersteller);
        setBestellNr(bestellNr);
        setBezeichnung(bezeichnung);
        setProtokoll(protokoll);
        setSound(sound);
        setKonfiguration(konfiguration);
        setStecker(stecker);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hersteller.class)
    @JoinColumn(name = DBNames.HERSTELLER_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk2"))
    public HerstellerModel getHersteller() {
        return hersteller;
    }

    
    public void setHersteller(HerstellerModel hersteller) {
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

    
    @Column(name = DBNames.I_MAX, precision = 6, scale = 2)
    public BigDecimal getiMax() {
        return iMax;
    }

    
    public void setiMax(BigDecimal iMax) {
        this.iMax = iMax;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER_TYP + "_fk3"))
    public ProtokollModel getProtokoll() {
        return protokoll;
    }

    
    public void setProtokoll(ProtokollModel protokoll) {
        this.protokoll = protokoll;
    }

    
    @Column(name = DBNames.FAHRSTUFE)
    public Integer getFahrstufe() {
        return fahrstufe;
    }

    
    public void setFahrstufe(Integer fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    
    @Column(name = DBNames.SOUND, nullable = false)
    public Boolean getSound() {
        return sound;
    }

    
    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    
    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.KONFIGURATION, nullable = false, length = 15)
    public Konfiguration getKonfiguration() {
        return konfiguration;
    }

    
    public void setKonfiguration(Konfiguration konfiguration) {
        this.konfiguration = konfiguration;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = DBNames.STECKER, nullable = false, length = 10)
    public Stecker getStecker() {
        return stecker;
    }

    
    public void setStecker(Stecker stecker) {
        this.stecker = stecker;
    }

    
    @Column(name = DBNames.ANLEITUNGEN, length = 512)
    @Convert(converter = PathConverter.class)
    public Path getAnleitungen() {
        return anleitungen;
    }

    
    public void setAnleitungen(Path anleitungen) {
        this.anleitungen = anleitungen;
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypAdress.class, orphanRemoval = true)
    public Set<DecoderTypAdress> getAdressen() {
        return adressen;
    }

    
    @Transient
    public Set<DecoderTypAdress> getSortedAdressen() {
        return new TreeSet<>(getAdressen());
    }

    
    public void setAdressen(Set<DecoderTypAdress> adressen) {
        this.adressen = adressen;
    }

    
    public void addAdress(DecoderTypAdress adress) {
        adress.setDecoderTyp(this);
        adress.setIndex(getAdressen().size()+1);
        adress.setDeleted(false);

        getAdressen().add(adress);
    }

    
    public void removeAdress(DecoderTypAdress adress) {
        getAdressen().remove(adress);
        /*
        int index = 1;

        for (DecoderTypAdress add : getAdressen()) {
            add.setIndex(index++);
        }
        */
    }


    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypCv.class, orphanRemoval = true)
    public Set<DecoderTypCv> getCVs() {
        return CVs;
    }

    
    @Transient
    public Set<DecoderTypCv> getSortedCVs() {
        return new TreeSet<>(getCVs());
    }

    
    public void setCVs(Set<DecoderTypCv> CVs) {
        this.CVs = CVs;
    }

    
    public void addCV(DecoderTypCv cv) {
        cv.setDecoderTyp(this);
        cv.setDeleted(false);
        CVs.add(cv);
    }

    
    public void removeCV(DecoderTypCv cv) {
        CVs.remove(cv);
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER_TYP, targetEntity = DecoderTypFunktion.class, orphanRemoval = true)
    public Set<DecoderTypFunktion> getFunktionen() {
        return funktionen;
    }

    
    @Transient
    public Set<DecoderTypFunktion> getSortedFunktionen() {
        return new TreeSet<>(getFunktionen());
    }

    
    public void setFunktionen(Set<DecoderTypFunktion> funktionen) {
        this.funktionen = funktionen;
    }

    
    public void addFunktion(DecoderTypFunktion funktion) {
        funktion.setDecoderTyp(this);
        funktion.setDeleted(false);
        getFunktionen().add(funktion);
    }

    
    public void removeFunktion(DecoderTypFunktion funktion) {
        getFunktionen().remove(funktion);
    }

    
    public int compareTo(Item other) {
        if (other instanceof DecoderTyp) {
            return new CompareToBuilder()
                    .append(getHersteller(), ((DecoderTyp) other).getHersteller())
                    .append(getBestellNr(), ((DecoderTyp) other).getBestellNr())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getHersteller())
                .append(getBestellNr())
                .hashCode();
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof DecoderTyp)) {
            return false;
        }

        DecoderTyp other = (DecoderTyp) obj;
        
        return new EqualsBuilder()
                .append(getHersteller(), other.getHersteller())
                .append(getBestellNr(), other.getBestellNr())
                .isEquals();
    }

    
    public String toString() {
      return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.HERSTELLER, getHersteller())
                .append(ApiNames.BESTELL_NR, getBestellNr())
                .append(ApiNames.BEZEICHNUNG, getBezeichnung())
                .append(ApiNames.PROTOKOLL, getProtokoll())
                .append(ApiNames.I_MAX, getiMax())
                .append(ApiNames.FAHRSTUFE, getFahrstufe())
                .append(ApiNames.GERAUSCH, getSound())
                .append(ApiNames.KONFIGURATION, getKonfiguration())
                .append(ApiNames.ANLEITUNGEN, getAnleitungen())
                .append(ApiNames.ADRESSEN, getAdressen())
                .append(ApiNames.CVS, getCVs())
                .append(ApiNames.FUNKTIONEN, getFunktionen())
                .toString();
    }
}