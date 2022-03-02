package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.DecoderCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypMapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.DecoderTypModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTranscriber")
public class DecoderTranscriber implements Transcriber<Decoder, DecoderModel> {

    private static final ArrayList<DecoderCvModel> KEIN_CV = new ArrayList<>();

    private static final ArrayList<DecoderFunktionModel> KEIN_FUNKTIONEN = new ArrayList<>();

    private final DecoderTypMapper decoderTypMapper;

    private final DecoderCvMapper cvMapper;

    private final DecoderFunktionMapper funktionMapper;

    @Override
    public DecoderModel apply(Decoder source, DecoderModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final DecoderTypModel decoderTyp = decoderTypMapper.convert(source.getDecoderTyp());

            destination.setDecoderId(source.getDecoderId());
            destination.setHersteller(decoderTyp.getHersteller());
            destination.setHerstellerBezeichnung(decoderTyp.getHerstellerBezeichnung());
            destination.setBestellNr(decoderTyp.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setArtikelId(source.getArtikel() != null ? source.getArtikel().getArtikelId() : null);
            destination.setIMax(decoderTyp.getIMax());
            destination.setSound(decoderTyp.getSound());
            destination.setMotor(decoderTyp.getMotor());
            destination.setOutputs(decoderTyp.getOutputs());
            destination.setServos(decoderTyp.getServos());
            destination.setKonfiguration(decoderTyp.getKonfiguration());
            destination.setStecker(decoderTyp.getStecker());
            destination.setAnleitungen(decoderTyp.getAnleitungen());
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setPreis(source.getPreis());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setProtokoll(getCode(source.getProtokoll()));
            destination.setProtokollBezeichnung(getBezeichnung(source.getProtokoll()));
            destination.setAdressTyp(decoderTyp.getAdressTyp());
            destination.setAdress(source.getAdress());
            destination.setSpan(decoderTyp.getSpan());
            destination.setFahrstufe(source.getFahrstufe() != null ? source.getFahrstufe() : decoderTyp.getFahrstufe());
            destination.setStatus(source.getStatus());
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
