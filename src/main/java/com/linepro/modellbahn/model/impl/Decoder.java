package com.linepro.modellbahn.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.util.AbstractItem;

@Entity
@Table(name = "DECODER", indexes = { @Index(columnList = "ID", unique = true) })
public class Decoder extends AbstractItem implements IDecoder {

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

    public Decoder(Long id, IDecoderTyp typ, IProtokoll protokoll, Long fahrstufe,
            Boolean deleted) {
        super(id, deleted);

        this.typ = typ;
        this.protokoll = protokoll;
        this.fahrstufe = fahrstufe;
    }

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=DecoderTyp.class)
    @JoinColumn(name="DECODER_TYP_ID", referencedColumnName="ID")
    public IDecoderTyp getTyp() {
        return typ;
    }

    public void setTyp(IDecoderTyp typ) {
        this.typ = typ;
    }

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Protokoll.class)
    @JoinColumn(name="PROTOKOLL_ID", referencedColumnName="ID")
    public IProtokoll getProtokoll() {
        return protokoll;
    }

    public void setProtokoll(IProtokoll protokoll) {
        this.protokoll = protokoll;
    }

    @Column(name="FAHRSTUFE")
    public Long getFahrstufe() {
        return fahrstufe;
    }

    public void setFahrstufe(Long fahrstufe) {
        this.fahrstufe = fahrstufe;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "ADRESS")
    @CollectionTable(name = "DECODER_ADRESSEN", joinColumns = @JoinColumn(name = "DECODER_ID", referencedColumnName="ID"))
    public List<Long> getAdressen() {
        return adressen;
    }

    public void setAdressen(List<Long> adressen) {
        this.adressen.clear();
        this.adressen.addAll(adressen);
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "DECODER_CV", joinColumns = @JoinColumn(name = "DECODER_ID", referencedColumnName="ID"))
    public List<DecoderCV> getCv() {
        return cv;
    }

    public void setCv(List<DecoderCV> cv) {
        this.cv.clear();
        this.cv.addAll(cv);
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "DECODER_FUNKTIONEN", joinColumns = @JoinColumn(name = "DECODER_ID", referencedColumnName="ID"))
    public List<DecoderFunktion> getFunktionen() {
        return funktionen;
    }

    public void setFunktionen(List<DecoderFunktion> funktionen) {
        this.funktionen.clear();
        this.funktionen.addAll(funktionen);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
                .append("typ", getTyp()).append("protokoll", getProtokoll()).append("fahrstufe", getFahrstufe())
                .append("adressen", getAdressen()).append("cv", getCv()).append("funktionen", getFunktionen())
                .toString();
    }
}