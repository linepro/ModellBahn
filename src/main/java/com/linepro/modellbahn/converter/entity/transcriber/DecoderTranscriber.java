package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import java.util.ArrayList;
import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.ArtikelMapper;
import com.linepro.modellbahn.converter.entity.DecoderAdressMapper;
import com.linepro.modellbahn.converter.entity.DecoderCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypMapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTranscriber implements Transcriber<Decoder, DecoderModel> {

    private static final ArrayList<DecoderAdressModel> KEIN_ADRESS = new ArrayList<>();

    private static final ArrayList<DecoderCvModel> KEIN_CV = new ArrayList<>();

    private static final ArrayList<DecoderFunktionModel> KEIN_FUNKTIONEN = new ArrayList<>();

    private final ArtikelMapper artikelMapper;

    private final DecoderTypMapper decoderTypMapper;

    private final DecoderAdressMapper adressMapper;

    private final DecoderCvMapper cvMapper;

    private final DecoderFunktionMapper funktionMapper;

    @Override
    public DecoderModel apply(Decoder source, DecoderModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final DecoderTypModel decoderTyp = decoderTypMapper.convert(source.getDecoderTyp());
            final ArtikelModel artikel = artikelMapper.convert(source.getArtikel());

            destination.setDecoderId(source.getDecoderId());
            destination.setHersteller(decoderTyp.getHersteller());
            destination.setBestellNr(decoderTyp.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setArtikelId(artikel != null ? artikel.getArtikelId() : null);
            destination.setIMax(decoderTyp.getIMax());
            destination.setSound(decoderTyp.getSound());
            destination.setKonfiguration(decoderTyp.getKonfiguration());
            destination.setStecker(decoderTyp.getStecker());
            destination.setAnleitungen(decoderTyp.getAnleitungen());
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setPreis(source.getPreis());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setProtokoll(getCode(source.getProtokoll()));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setStatus(source.getStatus());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            if (isAvailable(source.getAdressen())) {
                destination.setAdressen(source.getAdressen()
                                              .stream()
                                              .sorted()
                                              .map(a -> attempt(() ->adressMapper.convert(a)))
                                              .collect(new ResultCollector<>())
                                              .getValue()
                                              .orElse(KEIN_ADRESS));
            }

            if (isAvailable(source.getCvs())) {
                destination.setCvs(source.getCvs()
                                         .stream()
                                         .sorted()
                                         .map(c -> attempt(() ->cvMapper.convert(c)))
                                         .collect(new ResultCollector<>())
                                         .getValue()
                                         .orElse(KEIN_CV));
            }

            if (isAvailable(source.getFunktionen())) {
                destination.setFunktionen(source.getFunktionen()
                                                .stream()
                                                .sorted()
                                                .map(f -> attempt(() -> funktionMapper.convert(f)))
                                                .collect(new ResultCollector<>())
                                                .getValue()
                                                .orElse(KEIN_FUNKTIONEN));
            }

            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
