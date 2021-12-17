package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.DecoderTypCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypFunktionMapper;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypTranscriber implements Transcriber<DecoderTyp, DecoderTypModel> {

    private static final ArrayList<DecoderTypCvModel> KEIN_CV = new ArrayList<>();

    private static final ArrayList<DecoderTypFunktionModel> KEIN_FUNKTIONEN = new ArrayList<>();

    private final DecoderTypCvMapper cvMapper;

    private final DecoderTypFunktionMapper funktionMapper;

    private final PathMapper PathMapper;

    @Override
    public DecoderTypModel apply(DecoderTyp source, DecoderTypModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(getCode(source.getHersteller()));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setIMax(source.getIMax());
            destination.setFahrstufe(source.getFahrstufe());
            destination.setSound(source.getSound());
            destination.setKonfiguration(source.getKonfiguration());
            destination.setStecker(source.getStecker());
            destination.setAnleitungen(PathMapper.convert(source.getAnleitungen()));
            destination.setProtokoll(getCode(source.getProtokoll()));
            destination.setAdressTyp(source.getAdressTyp());
            destination.setAdress(source.getAdress());
            destination.setSpan(source.getSpan());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            destination.setCvs(
                            isAvailable(source.getCvs()) ?
                                source.getCvs()
                                      .stream()
                                      .sorted()
                                      .map(c -> attempt(() -> cvMapper.convert(c)))
                                      .collect(success())
                                      .getValue()
                                      .orElse(KEIN_CV) :
                                KEIN_CV
                                );

            destination.setFunktionen(
                            isAvailable(source.getFunktionen()) ?
                                 source.getFunktionen()
                                       .stream()
                                       .sorted()
                                       .map(f -> attempt(() -> funktionMapper.convert(f)))
                                       .collect(success())
                                       .getValue()
                                       .orElse(KEIN_FUNKTIONEN) :
                                 KEIN_FUNKTIONEN
                                 );
        }

        return destination;
    }
}
