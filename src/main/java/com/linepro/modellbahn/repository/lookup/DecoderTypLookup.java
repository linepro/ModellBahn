package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.DecoderTypRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DecoderTypLookup {
    
    @Autowired
    private final DecoderTypRepository repository;

    public DecoderTyp find(DecoderTypModel decoderTyp) {
        return Optional.ofNullable(decoderTyp)
                       .flatMap(t -> Optional.ofNullable(t.getHersteller()).map(h -> repository.findByBestellNr(h.getName(), t.getBestellNr())).orElse(null))
                       .orElse(null);
    }
}
