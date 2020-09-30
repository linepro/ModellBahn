package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.DecoderTypAdressMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypCvMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypFunktionMutator;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypTranscriber implements Transcriber<DecoderTyp, DecoderTypModel> {

    private static final ArrayList<DecoderTypAdressModel> KEIN_ADRESS = new ArrayList<>();

    private static final ArrayList<DecoderTypCvModel> KEIN_CV = new ArrayList<>();

    private static final ArrayList<DecoderTypFunktionModel> KEIN_FUNKTIONEN = new ArrayList<>();

    private final DecoderTypAdressMutator adressMutator;

    private final DecoderTypCvMutator cvMutator;

    private final DecoderTypFunktionMutator funktionMutator;

    private final PathMutator PathMutator;

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
            destination.setAnleitungen(PathMutator.convert(source.getAnleitungen()));
            destination.setProtokoll(getCode(source.getProtokoll()));
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            if (isAvailable(source.getAdressen())) {
                destination.setAdressen(source.getAdressen()
                                              .stream()
                                              .sorted()
                                              .map(a -> attempt(() -> adressMutator.convert(a)))
                                              .collect(success())
                                              .getValue()
                                              .orElse(KEIN_ADRESS));
            }

            if (isAvailable(source.getCvs())) {
                destination.setCvs(source.getCvs()
                                         .stream()
                                         .sorted()
                                         .map(c -> attempt(() -> cvMutator.convert(c)))
                                         .collect(success())
                                         .getValue()
                                         .orElse(KEIN_CV));
            }

            if (isAvailable(source.getFunktionen())) {
                destination.setFunktionen(source.getFunktionen()
                                                .stream()
                                                .sorted()
                                                .map(f -> attempt(() -> funktionMutator.convert(f)))
                                                .collect(success())
                                                .getValue()
                                                .orElse(KEIN_FUNKTIONEN));
            }
        }

        return destination;
    }
}
