package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ProduktModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ArtikelMutator")
public class ArtikelMutator implements Mutator<Artikel, ArtikelModel> {

    @Autowired
    private final AnderungMutator anderungMutator;

    @Autowired
    private final ProduktMutator produktMutator;

    @Autowired
    private final PathMutator pathMutator;

    @Override
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
            destination.setStatus(source.getStatus());
            destination.setDeleted(source.getDeleted());
            destination.setLicht(getCode(source.getLicht()));
            destination.setKupplung(getCode(source.getKupplung()));
            destination.setSteuerung(getCode(source.getSteuerung()));
            destination.setDecoder(Optional.ofNullable(source.getDecoder()).map(d -> d.getDecoderId()).orElse(null));
            destination.setMotorTyp(getCode(source.getMotorTyp()));
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setPreis(source.getPreis());
            destination.setStuck(source.getStuck());
            destination.setVerbleibende(source.getVerbleibende());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBeladung(source.getBeladung());
            destination.setDeleted(source.getDeleted());
            
            if (isAvailable(source.getAnderungen())) {
               destination.setAnderungen(source.getAnderungen()
                                                .stream()
                                                .sorted()
                                                .map(a -> attempt(() -> anderungMutator.convert(a)))
                                                .collect(success())
                                                .orElseThrow());
            }
        }
        
        return destination;
    }

    @Override
    public ArtikelModel get() {
        return new ArtikelModel();
    }
}
