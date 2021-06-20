package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.repository.DecoderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "DecoderLookup")
public class DecoderLookup {

    @Autowired
    private final DecoderRepository repository;

    public Decoder find(String decoderId) {
        return Optional.ofNullable(decoderId)
                       .map(m -> repository.findByDecoderId(m)
                                           .orElse(null))
                       .orElse(null);
    }
}
