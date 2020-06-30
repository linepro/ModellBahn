package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.VorbildRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "VorbildLookup")
public class VorbildLookup {
    
    @Autowired
    private final VorbildRepository repository;

    public Vorbild find(String gattung) {
        return Optional.ofNullable(gattung)
                       .flatMap(v -> repository.findByGattung(gattung))
                       .orElse(null);
    }
}
