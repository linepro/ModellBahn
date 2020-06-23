package com.linepro.modellbahn.repository.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.repository.ProduktRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ProduktLookup {
    
    @Autowired
    private final ProduktRepository repository;

    public Produkt find(String hersteller, String bestellNr) {
        if (StringUtils.hasText(hersteller) && StringUtils.hasText(bestellNr)) {
            return repository.findByBestellNr(hersteller, bestellNr)
                             .orElse(null);
        }
        
        return null;
    }
}
