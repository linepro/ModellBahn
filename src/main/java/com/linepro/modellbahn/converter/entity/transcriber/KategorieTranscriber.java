package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.UnterKategorieMapper;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "KategorieTranscriber")
public class KategorieTranscriber extends NamedTranscriber<Kategorie, KategorieModel> {

    private static final ArrayList<UnterKategorieModel> KEIN_UNTER_KATEGORIE = new ArrayList<>();

    private final UnterKategorieMapper unterKategorieMapper;

    @Override
    public KategorieModel apply(Kategorie source, KategorieModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination = super.apply(source, destination);

            destination.setUnterKategorien(isAvailable(source.getUnterKategorien()) ?
                            source.getUnterKategorien()
                                       .stream()
                                       .sorted()
                                       .map(u -> attempt(() -> unterKategorieMapper.convert(u)))
                                       .collect(success())
                                       .getValue()
                                       .orElse(KEIN_UNTER_KATEGORIE) :
                            KEIN_UNTER_KATEGORIE
                            );
        }

        return destination;
    }
}
