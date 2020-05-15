package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.DecoderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DecoderLookup {

    @Autowired
    private final DecoderRepository repository;

    public Decoder find(DecoderModel decoder) {
        return Optional.ofNullable(decoder)
                       .map(m -> repository.findByDecoderId(m.getDecoderId())
                                           .orElse(null))
                       .orElse(null);
    }
}
