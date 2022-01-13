package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.AnderungMapper;
import com.linepro.modellbahn.converter.entity.DecoderMapper;
import com.linepro.modellbahn.converter.entity.ProduktMapper;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.ProduktModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtikelTranscriber implements Transcriber<Artikel, ArtikelModel> {

    private static final ArrayList<AnderungModel> KEIN_ANDERUNGEN = new ArrayList<>();

    private static final ArrayList<DecoderModel> KEIN_DECODERS = new ArrayList<>();

    private final AnderungMapper anderungMapper;

    private final DecoderMapper decoderMapper;

    private final ProduktMapper produktMapper;

    private final PathMapper pathMapper;

    public ArtikelModel apply(Artikel source, ArtikelModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final ProduktModel produkt = produktMapper.convert(source.getProdukt());

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
            destination.setVorbild(produkt.getVorbild());
            destination.setGattung(produkt.getGattung());
            destination.setBetreibsnummer(produkt.getBetreibsnummer());
            destination.setAchsfolg(produkt.getAchsfolg());
            destination.setSondermodell(produkt.getSondermodell());
            destination.setAufbau(produkt.getAufbau());
            destination.setAbbildung(source.getAbbildung() != null ? pathMapper.convert(source.getAbbildung()) : produkt.getAbbildung());
            destination.setGrossansicht(source.getGrossansicht() != null ? pathMapper.convert(source.getGrossansicht()) : produkt.getGrossansicht());
            destination.setAnleitungen(produkt.getAnleitungen());
            destination.setExplosionszeichnung(produkt.getExplosionszeichnung());
            destination.setStatus(source.getStatus());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
            destination.setLicht(source.getLicht() != null ? getCode(source.getLicht()) : produkt.getLicht());
            destination.setKupplung(source.getKupplung() != null ? getCode(source.getKupplung()) : produkt.getKupplung());
            destination.setSteuerung(source.getSteuerung() != null ? getCode(source.getSteuerung()) : produkt.getSteuerung());
            destination.setMotorTyp(source.getMotorTyp() != null ? getCode(source.getMotorTyp()) : produkt.getMotorTyp());
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setPreis(source.getPreis());
            destination.setMenge(source.getMenge());
            destination.setVerbleibende(source.getVerbleibende());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBeladung(source.getBeladung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            destination.setAnderungen(
                            isAvailable(source.getAnderungen()) ?
                                source.getAnderungen()
                                      .stream()
                                      .sorted()
                                      .map(a -> attempt(() -> anderungMapper.convert(a)))
                                      .collect(success())
                                      .getValue()
                                      .orElse(KEIN_ANDERUNGEN) :
                                KEIN_ANDERUNGEN
                                );

            destination.setDecoders(
                            isAvailable(source.getDecoders()) ?
                                source.getDecoders()
                                      .stream()
                                      .map(d -> attempt(() -> decoderMapper.convert(d)))
                                      .collect(success())
                                      .getValue()
                                      .orElse(KEIN_DECODERS) :
                                KEIN_DECODERS
                                );

            destination.setFunktionen(
                            destination.getDecoders()
                                       .stream()
                                       .map(DecoderModel::getFunktionen)
                                       .flatMap(List::stream)
                                       .collect(Collectors.toList())
                                       );

            destination.getDecoders()
                       .forEach(d -> d.setFunktionen(null));
        }

        return destination;
    }
}
