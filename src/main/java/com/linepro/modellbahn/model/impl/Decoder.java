package com.linepro.modellbahn.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "decoder", indexes = { @Index(columnList = "name", unique = true), @Index(columnList = "decoder_typ_id"),
        @Index(columnList = "protokoll_id") }, uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Decoder extends AbstractNamedItem implements IDecoder {

    private static final long serialVersionUID = 44440227704021482L;

    private IDecoderTyp typ;

    private IProtokoll protokoll;

    private Long fahrstufe;

    private List<Long> adressen = new ArrayList<>();

    private List<DecoderCV> cv = new ArrayList<>();

    private List<DecoderFunktion> funktionen = new ArrayList<>();

    public Decoder() {
        super();
    }

    public Decoder(Long id, IDecoderTyp typ, IProtokoll protokoll, String decoderId, String beschreibung,
            Long fahrstufe,
            Boolean deleted) {
        super(id, decoderId, beschreibung, deleted);

        this.typ = typ;
        this.protokoll = protokoll;
        this.fahrstufe = fahrstufe;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DecoderTyp.class)
    @JoinColumn(name = "decoder_typ_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_fk1"))
    @JsonBackReference
    public IDecoderTyp getTyp() {
        return typ;
    }

    public void setTyp(IDecoderTyp typ) {
        this.typ = typ;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Protokoll.class)
    @JoinColumn(name = "protokoll_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "decoder_fk2"))
    @JsonBackReference
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    @Column(name = "fahrstufe")
    public Long getFahrstufe() {
        return fahrstufe;
    }

    public void setFahrstufe(Long fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "adress")
    @CollectionTable(name = "decoder_adressen", joinColumns = @JoinColumn(name = "decoder_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "decoder_fk3"))
    public List<Long> getAdressen() {
        return adressen;
    }

    public void setAdressen(List<Long> adressen) {
        this.adressen.clear();
        this.adressen.addAll(adressen);
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "decoder_cv", joinColumns = @JoinColumn(name = "decoder_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "decoder_fk4"))
    @JsonManagedReference
    public List<DecoderCV> getCv() {
        return cv;
    }

    public void setCv(List<DecoderCV> cv) {
        this.cv.clear();
        this.cv.addAll(cv);
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "decoder_funktionen", joinColumns = @JoinColumn(name = "decoder_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "decoder_fk5"))
    @JsonManagedReference
    public List<DecoderFunktion> getFunktionen() {
        return funktionen;
    }

    public void setFunktionen(List<DecoderFunktion> funktionen) {
        this.funktionen.clear();
        this.funktionen.addAll(funktionen);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
                .append("typ", getTyp()).append("protokoll", getProtokoll()).append("fahrstufe", getFahrstufe())
                .append("adressen", getAdressen()).append("cv", getCv()).append("funktionen", getFunktionen())
                .toString();
    }
}