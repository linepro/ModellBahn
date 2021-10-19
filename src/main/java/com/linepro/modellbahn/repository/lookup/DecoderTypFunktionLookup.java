package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTypFunktionLookup")
public class DecoderTypFunktionLookup implements Lookup<DecoderTypFunktion, DecoderTypFunktionModel> {

    private final DecoderTypFunktionRepository repository;

    public Optional<DecoderTypFunktion> find(String hersteller, String bestellNr, String funktion) {
        if (StringUtils.hasText(hersteller) && StringUtils.hasText(bestellNr) && StringUtils.hasText(funktion)) {
            return repository.findByFunktion(hersteller, bestellNr, funktion);
        }

        return Optional.empty();
    }

    public Optional<DecoderTypFunktion> find(DecoderTyp decoderTyp, String funktion) {
        if (decoderTyp != null && decoderTyp.getHersteller() != null) {
            return find(decoderTyp.getHersteller().getName(), decoderTyp.getBestellNr(), funktion);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTypFunktion> find(DecoderTypFunktion item) {
        if (item != null && item.getDecoderTyp() != null) {
            return find(item.getDecoderTyp(), item.getFunktion());
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderTypFunktion> find(DecoderTypFunktionModel model) {
        if (model != null) {
            return find(model.getHersteller(), model.getBestellNr(), model.getFunktion());
        }

        return Optional.empty();
    }

}
