package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.repository.ProduktRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ProduktLookup {
    
    @Autowired
    private final ProduktRepository repository;

    public Produkt find(HerstellerModel hersteller, String bestellNr) {
        return Optional.ofNullable(hersteller)
                       .flatMap(h -> repository.findByBestellNr(h.getName(), bestellNr))
                       .orElse(null);
    }
}
