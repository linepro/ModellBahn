package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.ProduktTeilMapper;
import com.linepro.modellbahn.converter.entity.UnterKategorieMapper;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.model.UnterKategorieModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktTranscriber implements Transcriber<Produkt, ProduktModel> {

    private static final ArrayList<ProduktTeilModel> KEIN_TEILEN = new ArrayList<>();

    private final UnterKategorieMapper unterKategorieMapper;

    private final ProduktTeilMapper teilMapper;

    private final PathMapper PathMapper;

    @Override
    public ProduktModel apply(Produkt source, ProduktModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final UnterKategorieModel unterKategorie = unterKategorieMapper.convert(source.getUnterKategorie());

            destination.setHersteller(getCode(source.getHersteller()));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setLange(source.getLange());
            destination.setBahnverwaltung(getCode(source.getBahnverwaltung()));
            destination.setKategorie(unterKategorie.getKategorie());
            destination.setUnterKategorie(unterKategorie.getName());
            destination.setMassstab(getCode(source.getMassstab()));
            destination.setSpurweite(getCode(source.getSpurweite()));
            destination.setBetreibsnummer(source.getBetreibsnummer());
            destination.setEpoch(getCode(source.getEpoch()));
            destination.setGattung(getCode(source.getGattung()));
            destination.setAchsfolg(getCode(source.getAchsfolg()));
            destination.setSondermodell(getCode(source.getSondermodell()));
            destination.setAufbau(getCode(source.getAufbau()));
            destination.setBauzeit(source.getBauzeit());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setLicht(getCode(source.getLicht()));
            destination.setKupplung(getCode(source.getKupplung()));
            destination.setSteuerung(getCode(source.getSteuerung()));
            if (isAvailable(source.getDecoderTyp())) {
                destination.setDecoderTypHersteller(getCode(source.getDecoderTyp().getHersteller()));
                destination.setDecoderTypBestellNr(source.getDecoderTyp().getBestellNr());
            }
            destination.setMotorTyp(getCode(source.getMotorTyp()));
            destination.setAnleitungen(PathMapper.convert(source.getAnleitungen()));
            destination.setExplosionszeichnung(PathMapper.convert(source.getExplosionszeichnung()));
            destination.setAbbildung(PathMapper.convert(source.getAbbildung()));
            destination.setGrossansicht(PathMapper.convert(source.getGrossansicht()));
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            destination.setTeilen(
                            isAvailable(source.getTeilen()) ?
                                source.getTeilen()
                                      .stream()
                                      .sorted()
                                      .map(t -> attempt(() -> teilMapper.convert(t)))
                                      .collect(success())
                                      .getValue()
                                      .orElse(KEIN_TEILEN) :
                                KEIN_TEILEN
                                );
        }

        return destination;
    }
}
