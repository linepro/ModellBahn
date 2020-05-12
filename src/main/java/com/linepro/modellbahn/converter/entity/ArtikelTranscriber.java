package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;

public class ArtikelTranscriber implements Transcriber<Artikel,ArtikelModel> {

    
    public ArtikelTranscriber() {
        
    }
    
    public ArtikelModel apply(Artikel source, ArtikelModel destination) {
        destination.setArtikelId(source.getArtikelId());
        destination.setKaufdatum(source.getKaufdatum());
        destination.setWahrung(source.getWahrung());
        destination.setSteuerung(source.getSteuerung());
        destination.setMotorTyp(source.getMotorTyp());
        destination.setLicht(source.getLicht());
        destination.setKupplung(source.getKupplung());
        destination.setDecoder(source.getDecoder());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setPreis(source.getPreis());
        destination.setStuck(source.getStuck());
        destination.setVerbleibende(source.getVerbleibende());
        destination.setAnmerkung(source.getAnmerkung());
        destination.setBeladung(source.getBeladung());
        destination.setAbbildung(source.getAbbildung());
        destination.setStatus(source.getStatus());
        destination.setDeleted(source.getDeleted());
        return destination;
    }
}
