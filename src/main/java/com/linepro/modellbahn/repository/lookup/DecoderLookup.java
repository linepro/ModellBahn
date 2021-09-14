package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.DecoderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "DecoderLookup")
public class DecoderLookup implements Lookup<Decoder, DecoderModel> {

    @Autowired
    private final DecoderRepository repository;

    public Optional<Decoder> find(String decoderId) {
        return repository.findByDecoderId(decoderId);
    }

    @Override
    public Optional<Decoder> find(Decoder decoder) {
        if (decoder != null && decoder.getDecoderId() != null) {
            return find(decoder.getDecoderId());
        }
        
        return Optional.empty();
    }

    @Override
    public Optional<Decoder> find(DecoderModel decoder) {
        if (decoder != null && decoder.getDecoderId() != null) {
            return find(decoder.getDecoderId());
        }
        
        return Optional.empty();
    }
}
