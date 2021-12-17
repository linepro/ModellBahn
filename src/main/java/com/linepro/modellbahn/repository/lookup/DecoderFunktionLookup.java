package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.repository.DecoderFunktionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderFunktionLookup")
public class DecoderFunktionLookup implements Lookup<DecoderFunktion, DecoderFunktionModel> {

    private final DecoderFunktionRepository repository;

    public Optional<DecoderFunktion> find(String decoderId, String funktion) {
        if (StringUtils.hasText(decoderId) && StringUtils.hasText(funktion)) {
            return repository.findByFunktion(decoderId, funktion);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderFunktion> find(DecoderFunktion item) {
        if (item != null && item.getDecoder() != null && item.getFunktion() != null) {
            return find(item.getDecoder().getDecoderId(), item.getFunktion().getFunktion());
        }

        return Optional.empty();
    }

    @Override
    public Optional<DecoderFunktion> find(DecoderFunktionModel model) {
        if (model != null) {
            return find(model.getDecoderId(), model.getFunktion());
        }

        return Optional.empty();
    }

}
