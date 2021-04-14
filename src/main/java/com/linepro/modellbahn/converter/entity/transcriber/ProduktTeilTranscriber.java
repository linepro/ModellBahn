package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktTeilTranscriber implements Transcriber<ProduktTeil, ProduktTeilModel> {

    @Override
    public ProduktTeilModel apply(ProduktTeil source, ProduktTeilModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getProdukt().getHersteller().getName());
            destination.setBestellNr(source.getProdukt().getBestellNr());
            destination.setTeilHersteller(source.getTeil().getHersteller().getName());
            destination.setTeilBestellNr(source.getTeil().getBestellNr());
            destination.setBezeichnung(source.getTeil().getBezeichnung());
            destination.setKategorie(getCode(source.getTeil().getUnterKategorie().getKategorie()));
            destination.setUnterKategorie(getCode(source.getTeil().getUnterKategorie()));
            destination.setMenge(source.getMenge());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
