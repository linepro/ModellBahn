package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

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
@Component(PREFIX + "ProduktTranscriber")
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
            destination.setHerstellerBezeichnung(getBezeichnung(source.getHersteller()));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setLange(source.getLange());
            destination.setBahnverwaltung(getCode(source.getBahnverwaltung()));
            destination.setBahnverwaltungBezeichnung(getBezeichnung(source.getBahnverwaltung()));
            destination.setKategorie(unterKategorie.getKategorie());
            destination.setKategorieBezeichnung(unterKategorie.getKategorieBezeichnung());
            destination.setUnterKategorie(unterKategorie.getName());
            destination.setUnterKategorieBezeichnung(unterKategorie.getBezeichnung());
            destination.setMassstab(getCode(source.getMassstab()));
            destination.setMassstabBezeichnung(getBezeichnung(source.getMassstab()));
            destination.setSpurweite(getCode(source.getSpurweite()));
            destination.setSpurweiteBezeichnung(getBezeichnung(source.getSpurweite()));
            destination.setBetreibsnummer(source.getBetreibsnummer());
            destination.setEpoch(getCode(source.getEpoch()));
            destination.setEpochBezeichnung(getBezeichnung(source.getEpoch()));
            destination.setVorbild(getCode(source.getVorbild()));
            destination.setGattung(getCode(source.getGattung()));
            destination.setGattungBezeichnung(getBezeichnung(source.getGattung()));
            destination.setAchsfolg(getCode(source.getAchsfolg()));
            destination.setAchsfolgBezeichnung(getBezeichnung(source.getAchsfolg()));
            destination.setAufbau(getCode(source.getAufbau()));
            destination.setAufbauBezeichnung(getBezeichnung(source.getAufbau()));
            destination.setSondermodell(getCode(source.getSondermodell()));
            destination.setSondermodellBezeichnung(getBezeichnung(source.getSondermodell()));
            destination.setBauzeit(source.getBauzeit());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setLicht(getCode(source.getLicht()));
            destination.setLichtBezeichnung(getBezeichnung(source.getLicht()));
            destination.setKupplung(getCode(source.getKupplung()));
            destination.setKupplungBezeichnung(getBezeichnung(source.getKupplung()));
            destination.setSteuerung(getCode(source.getSteuerung()));
            destination.setSteuerungBezeichnung(getBezeichnung(source.getSteuerung()));
            if (isAvailable(source.getDecoderTyp())) {
                destination.setDecoderTypHersteller(getCode(source.getDecoderTyp().getHersteller()));
                destination.setDecoderTypHerstellerBezeichnung(getBezeichnung(source.getDecoderTyp().getHersteller()));
                destination.setDecoderTypBestellNr(source.getDecoderTyp().getBestellNr());
            }
            destination.setMotorTyp(getCode(source.getMotorTyp()));
            destination.setMotorTypBezeichnung(getBezeichnung(source.getMotorTyp()));
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
