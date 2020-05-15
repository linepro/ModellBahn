package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.VorbildRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class VorbildLookup {
    
    @Autowired
    private final VorbildRepository repository;

    public Vorbild find(VorbildModel vorbild) {
        return Optional.ofNullable(vorbild)
                       .flatMap(v -> repository.findByGattung(v.getGattung()))
                       .orElse(null);
    }
}
