package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.ArtikelMutator;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ZugConsistModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZugConsistTranscriber implements Transcriber<ZugConsist, ZugConsistModel> {

    private final ArtikelMutator artikelMutator;

    @Override
    public ZugConsistModel apply(ZugConsist source, ZugConsistModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final ArtikelModel artikel = artikelMutator.convert(source.getArtikel());

            destination.setZug(source.getZug().getName());
            destination.setPosition(source.getPosition());
            destination.setArtikelId(artikel.getArtikelId());
            destination.setHersteller(artikel.getHersteller());
            destination.setBestellNr(artikel.getBestellNr());
            destination.setBezeichnung(artikel.getBezeichnung());
            destination.setKategorie(artikel.getKategorie());
            destination.setUnterKategorie(artikel.getUnterKategorie());
            destination.setLange(artikel.getLange());
            destination.setBahnverwaltung(artikel.getBahnverwaltung());
            destination.setGattung(artikel.getGattung());
            destination.setBetreibsnummer(artikel.getBetreibsnummer());
            destination.setAbbildung(artikel.getAbbildung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
