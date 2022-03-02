package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.ArtikelMapper;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ZugConsistModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ZugConsistTranscriber")
public class ZugConsistTranscriber implements Transcriber<ZugConsist, ZugConsistModel> {

    private final ArtikelMapper artikelMapper;

    @Override
    public ZugConsistModel apply(ZugConsist source, ZugConsistModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final ArtikelModel artikel = artikelMapper.convert(source.getArtikel());

            destination.setZug(source.getZug().getName());
            destination.setZugBezeichnung(source.getZug().getBezeichnung());
            destination.setPosition(source.getPosition());
            destination.setArtikelId(artikel.getArtikelId());
            destination.setArtikelBezeichnung(artikel.getBezeichnung());
            destination.setHersteller(artikel.getHersteller());
            destination.setHerstellerBezeichnung(artikel.getHerstellerBezeichnung());
            destination.setBestellNr(artikel.getBestellNr());
            destination.setBezeichnung(artikel.getBezeichnung());
            destination.setKategorie(artikel.getKategorie());
            destination.setKategorieBezeichnung(artikel.getKategorieBezeichnung());
            destination.setUnterKategorie(artikel.getUnterKategorie());
            destination.setUnterKategorieBezeichnung(artikel.getUnterKategorieBezeichnung());
            destination.setLange(artikel.getLange());
            destination.setBahnverwaltung(artikel.getBahnverwaltung());
            destination.setBahnverwaltungBezeichnung(artikel.getBahnverwaltungBezeichnung());
            destination.setGattung(artikel.getGattung());
            destination.setGattungBezeichnung(artikel.getGattungBezeichnung());
            destination.setBetreibsnummer(artikel.getBetreibsnummer());
            destination.setAbbildung(artikel.getAbbildung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
