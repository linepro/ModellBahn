package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ProduktTeilTranscriber")
public class ProduktTeilTranscriber implements Transcriber<ProduktTeil, ProduktTeilModel> {

    @Override
    public ProduktTeilModel apply(ProduktTeil source, ProduktTeilModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getProdukt().getHersteller().getName());
            destination.setHerstellerBezeichnung(source.getProdukt().getHersteller().getBezeichnung());
            destination.setBestellNr(source.getProdukt().getBestellNr());
            destination.setTeilHersteller(source.getTeil().getHersteller().getName());
            destination.setTeilHerstellerBezeichnung(source.getTeil().getHersteller().getBezeichnung());
            destination.setTeilBestellNr(source.getTeil().getBestellNr());
            destination.setBezeichnung(source.getTeil().getBezeichnung());
            destination.setKategorie(getCode(source.getTeil().getUnterKategorie().getKategorie()));
            destination.setKategorieBezeichnung(getBezeichnung(source.getTeil().getUnterKategorie().getKategorie()));
            destination.setUnterKategorie(getCode(source.getTeil().getUnterKategorie()));
            destination.setUnterKategorieBezeichnung(getBezeichnung(source.getTeil().getUnterKategorie()));
            destination.setMenge(source.getMenge());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
