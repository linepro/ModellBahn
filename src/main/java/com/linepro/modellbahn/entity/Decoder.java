package com.linepro.modellbahn.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.persistence.util.DecoderId;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Fahrstufe;

/**
 * Decoder.
 * Represents a decoder (with its settings).
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.DECODER)
@Table(name = DBNames.DECODER, 
    indexes = {
        @Index(name = DBNames.DECODER + "_IX1", columnList = DBNames.DECODER_ID, unique = true), 
        @Index(name = DBNames.DECODER + "_IX2", columnList = DBNames.DECODER_TYP_ID),
        @Index(name = DBNames.DECODER + "_IX3", columnList = DBNames.PROTOKOLL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.DECODER + "_UC1", columnNames = { DBNames.DECODER_ID }) 
        })
//@formatter:on
public class Decoder extends ItemImpl {

    @Pattern(regexp = "^[A-Z0-9]+$", message = "{com.linepro.modellbahn.validator.constraints.decoderId.invalid}")
    private String decoderId;

    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;

    /** The typ. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.decoderTyp.notnull}")
    private DecoderTyp decoderTyp;

    /** The protokoll. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.protokoll.notnull}")
    private Protokoll protokoll;

    /** The fahrstufe. */
    @Fahrstufe
    private Integer fahrstufe;

    private DecoderStatus status;

    /** The adressen. */
    private Set<DecoderAdress> adressen;

    /** The cvs. */
    private Set<DecoderCv> cvs;

    /** The funktionen. */
    private Set<DecoderFunktion> funktionen;

    /**
     * Instantiates a new decoder.
     */
    public Decoder() {
        super();
    }

    public Decoder(String decoderId) {
        this(null, null, null, decoderId, null, null, null, false);
    }

    /**
     * Instantiates a new decoder.
     *
     * @param id the id
     * @param typ the typ
     * @param protokoll the protokoll
     * @param decoderId the decoder id
     * @param bezeichnung the bezeichnung
     * @param fahrstufe the fahrstufe
     * @param deleted if  { this item is soft deleted, otherwise it is active
     */
    public Decoder(Long id, DecoderTyp typ, Protokoll protokoll, String decoderId, String bezeichnung, Integer fahrstufe, DecoderStatus status, Boolean deleted) {
        super(id, deleted);

        setDecoderId(decoderId);
        setBezeichnung(bezeichnung);
        setDecoderTyp(typ);
        setProtokoll(protokoll);
        setFahrstufe(fahrstufe);
        setStatus(status);
    }

    
    @BusinessKey
    @DecoderId
    @Column(name=DBNames.DECODER_ID, unique=true, length=6, nullable = false, updatable = false)
    public String getDecoderId() {
        return decoderId;
    }

    
    public void setDecoderId(String decoderId) {
        this.decoderId = decoderId;
    }

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    public String getBezeichnung() {
      return bezeichnung;
    }

    
    public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
    }
    
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DecoderTyp.class)
    @JoinColumn(name = DBNames.DECODER_TYP_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER + "_fk1"))
    public DecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    
    public void setDecoderTyp(DecoderTyp decoderTyp) {
        this.decoderTyp = decoderTyp;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = DBNames.PROTOKOLL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.DECODER + "_fk2"))
    public Protokoll getProtokoll() {
        return protokoll;
    }

    
    public void setProtokoll(Protokoll protokoll) {
        this.protokoll = protokoll;
    }

    
    @Column(name = DBNames.FAHRSTUFE)
    public Integer getFahrstufe() {
        return fahrstufe;
    }

    
    public void setFahrstufe(Integer fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    
    @Column(name = DBNames.STATUS)
    public DecoderStatus getStatus() { return status; }

    
    public void setStatus(DecoderStatus status) { this.status = status; }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderAdress.class, orphanRemoval = true)
    public Set<DecoderAdress> getAdressen() {
        return adressen;
    }

    
    public void setAdressen(Set<DecoderAdress>  adressen) {
        this.adressen = adressen;
    }

    
    @Transient
    public Set<DecoderAdress>  getSortedAdressen() {
        return new TreeSet<DecoderAdress>(getAdressen());
    }

    
    public void addAdress(DecoderAdress adress) {
        adress.setDecoder(this);
        getAdressen().add(adress);
    }

    
    public void removeAdress(DecoderAdress adress) {
        getAdressen().remove(adress);
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderCv.class, orphanRemoval = true)
    public Set<DecoderCv> getCVs() {
        return cvs;
    }

    
    public void setCVs(Set<DecoderCv> cvs) {
        this.cvs = cvs;
    }

    
    @Transient
    public Set<DecoderCv> getSortedCVs() {
        return new TreeSet<DecoderCv>(getCVs());
    }

    
    public void addCV(DecoderCv cv) {
        cv.setDecoder(this);
        getCVs().add(cv);
    }

    
    public void removeCV(DecoderCv cv) {
        getCVs().remove(cv);
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = DBNames.DECODER, targetEntity = DecoderFunktion.class, orphanRemoval = true)
    public Set<DecoderFunktion> getFunktionen() {
        return funktionen;
    }

    
    public void setFunktionen(Set<DecoderFunktion> funktionen) {
        this.funktionen = funktionen;
    }

    
    @Transient
    public Set<DecoderFunktion> getSortedFunktionen() {
        return new TreeSet<DecoderFunktion>(getFunktionen());
    }

    
    public void addFunktion(DecoderFunktion funktion) {
        funktion.setDecoder(this);
        getFunktionen().add(funktion);
    }

    
    public void removeFunktion(DecoderFunktion funktion) {
        getFunktionen().remove(funktion);
    }

    
    public int compareTo(Item other) {
      if (other instanceof Decoder) {
        return new CompareToBuilder()
            .append(getDecoderId(), ((Decoder) other).getDecoderId())
            .toComparison();
      }

      return super.compareTo(other);
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(DBNames.DECODER_TYP, getDecoderTyp())
                .append(DBNames.PROTOKOLL, getProtokoll())
                .append(DBNames.FAHRSTUFE, getFahrstufe())
                .append(DBNames.STATUS, getStatus())
                .append(DBNames.DECODER_ADRESS, getAdressen())
                .append(DBNames.DECODER_CV, getCVs())
                .append(DBNames.DECODER_FUNKTION, getFunktionen())
                .toString();
    }
}