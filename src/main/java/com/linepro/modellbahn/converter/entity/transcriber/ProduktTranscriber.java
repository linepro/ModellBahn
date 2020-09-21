package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;
import java.util.Optional;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.entity.ProduktTeilMutator;
import com.linepro.modellbahn.converter.entity.UnterKategorieMutator;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.model.UnterKategorieModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktTranscriber implements Transcriber<Produkt, ProduktModel> {

    private static final ArrayList<ProduktTeilModel> KEIN_TEILEN = new ArrayList<>();

    private final UnterKategorieMutator unterKategorieMutator;

    private final ProduktTeilMutator teilMutator;

    private final PathMutator PathMutator;

    @Override
    public ProduktModel apply(Produkt source, ProduktModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final UnterKategorieModel unterKategorie = unterKategorieMutator.convert(source.getUnterKategorie());

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
            destination.setAnleitungen(PathMutator.convert(source.getAnleitungen()));
            destination.setExplosionszeichnung(PathMutator.convert(source.getExplosionszeichnung()));
            destination.setAbbildung(PathMutator.convert(source.getAbbildung()));
            destination.setGrossansicht(PathMutator.convert(source.getGrossansicht()));
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));

            if (isAvailable(source.getTeilen())) {
                destination.setTeilen(source.getTeilen()
                                            .stream()
                                            .sorted()
                                            .map(t -> attempt(() -> teilMutator.convert(t)))
                                            .collect(success())
                                            .getValue()
                                            .orElse(KEIN_TEILEN));
            }
        }

        return destination;
    }
}
