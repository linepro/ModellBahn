package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.AnderungMutator;
import com.linepro.modellbahn.converter.entity.ProduktMutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ProduktModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtikelTranscriber implements Transcriber<Artikel, ArtikelModel> {

    private static final ArrayList<AnderungModel> KEIN_ANDERUNGEN = new ArrayList<>();

    private final AnderungMutator anderungMutator;

    private final ProduktMutator produktMutator;

    private final PathMutator pathMutator;

    public ArtikelModel apply(Artikel source, ArtikelModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final ProduktModel produkt = produktMutator.convert(source.getProdukt());

            destination.setArtikelId(source.getArtikelId());
            destination.setHersteller(produkt.getHersteller());
            destination.setBestellNr(produkt.getBestellNr());
            destination.setBezeichnung(StringUtils.hasText(source.getBezeichnung()) ? source.getBezeichnung() : produkt.getBezeichnung());
            destination.setLange(produkt.getLange());
            destination.setBahnverwaltung(produkt.getBahnverwaltung());
            destination.setKategorie(produkt.getKategorie());
            destination.setUnterKategorie(produkt.getUnterKategorie());
            destination.setMassstab(produkt.getMassstab());
            destination.setSpurweite(produkt.getSpurweite());
            destination.setEpoch(produkt.getEpoch());
            destination.setGattung(produkt.getGattung());
            destination.setBetreibsnummer(produkt.getBetreibsnummer());
            destination.setAchsfolg(produkt.getAchsfolg());
            destination.setSondermodell(produkt.getSondermodell());
            destination.setAufbau(produkt.getAufbau());
            destination.setAbbildung(source.getAbbildung() != null ? pathMutator.convert(source.getAbbildung()) : produkt.getAbbildung());
            destination.setGrossansicht(source.getGrossansicht() != null ? pathMutator.convert(source.getGrossansicht()) : produkt.getGrossansicht());
            destination.setStatus(source.getStatus());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
            destination.setLicht(source.getLicht() != null ? getCode(source.getLicht()) : produkt.getLicht());
            destination.setKupplung(source.getKupplung() != null ? getCode(source.getKupplung()) : produkt.getKupplung());
            destination.setSteuerung(source.getSteuerung() != null ? getCode(source.getSteuerung()) : produkt.getSteuerung());
            destination.setDecoder(Optional.ofNullable(source.getDecoder()).map(d -> d.getDecoderId()).orElse(null));
            destination.setMotorTyp(source.getMotorTyp() != null ? getCode(source.getMotorTyp()) : produkt.getMotorTyp());
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setPreis(source.getPreis());
            destination.setStuck(source.getStuck());
            destination.setVerbleibende(source.getVerbleibende());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBeladung(source.getBeladung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            if (isAvailable(source.getAnderungen())) {
               destination.setAnderungen(source.getAnderungen()
                                                .stream()
                                                .sorted()
                                                .map(a -> attempt(() -> anderungMutator.convert(a)))
                                                .collect(success())
                                                .getValue()
                                                .orElse(KEIN_ANDERUNGEN));
            }
        }

        return destination;
    }
}
